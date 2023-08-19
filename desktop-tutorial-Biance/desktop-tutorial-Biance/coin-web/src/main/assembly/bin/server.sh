#!/bin/bash
USAGE="-e Usage: server.sh {start|stop|restart|status}"

if [ -L ${BASH_SOURCE-$0} ]; then
    bin=$(dirname $(readlink "${BASH_SOURCE-$0}"))
else
    bin=$(dirname ${BASH_SOURCE-$0})
fi
bin=$(cd "${bin}">/dev/null; pwd)
. "${bin}/common.sh"
. "${bin}/functions.sh"
cd ..


HOSTNAME=$(hostname)
APP_PID="${APP_PID_DIR}/${APP_NAME}-${HOSTNAME}.pid"
APP_OUTPUTFILE="${APP_LOG_DIR}/stdout.log"

#JAVA_OPTS+=" -Dlogging.config=${LOGGING_CONFIG_FILE}"
#JAVA_OPTS+=" -Dspring.config.location=file:${BOOTSTRAP_FILE},file:${APPLICATION_FILE}"

# contruct classpath
addEachJarInDir "${APP_HOME}/lib"

APP_CLASSPATH="${CLASSPATH}:${APP_CONFIG_DIR}:${APP_CLASSPATH}"

if [[ "${APP_NICENESS}" = "" ]];
then
    export APP_NICENESS=0
fi

function initialize_default_directories() {
    if [[ ! -d "${APP_LOG_DIR}" ]];
    then
        echo "Log dir doesn't exist, create ${APP_LOG_DIR}"
        $(mkdir -p "${APP_LOG_DIR}")
    fi

    if [[ ! -d "${APP_PID_DIR}" ]];
    then
        echo "Pid dir doesn't exist, create ${APP_PID_DIR}"
        $(mkdir -p "${APP_PID_DIR}")
    fi
}

function wait_for_process_to_die() {
    local pid
    local count
    pid=${1}
    timeout=${2}
    count=0
    timeoutTime=$(date "+%s")
    let "timeoutTime+=${timeout}"
    currentTime=$(date "+%s")
    forceKill=1
    
    while [[ ${currentTime} -lt ${timeoutTime} ]];
    do
        $(kill ${pid} > /dev/null 2> /dev/null)
        if kill -0 ${pid} > /dev/null 2>&1;
        then
            sleep 3
        else
            forceKill=0
            break
        fi
        currentTime=$(date "+%s")
    done

    if [[ forceKill -ne 0 ]];
    then
        $(kill -9 ${pid} > /dev/null 2> /dev/null)
    fi
}

function check_if_process_is_alive() {
    local pid
    pid=$(cat ${APP_PID})
    if ! kill -0 ${pid} > /dev/null 2>&1;
    then
        action_msg "${APP_NAME} process died" "${SET_ERROR}"
    fi
}

function start() {
    local pid

    if [[ -f "${APP_PID}" ]];
    then
        pid=$(cat ${APP_PID})
        if kill -0 ${pid} > /dev/null 2>&1;
        then
            echo "${APP_NAME} is already running"
            return 0;
        fi
    fi
    
    initialize_default_directories

    nohup nice -n ${APP_NICENESS} ${APP_RUNNER} ${JAVA_OPTS} -cp ${APP_CLASSPATH} ${APP_MAIN_CLASS} >> "${APP_OUTPUTFILE}" 2>&1 < /dev/null &
    pid=$!
    if [[ -z "${pid}" ]];
    then
        action_msg "${APP_NAME} start" "${SET_ERROR}"
        return 1;
    else
        action_msg "${APP_NAME} start" "${SET_OK}"
        echo "${pid}" > "${APP_PID}"
    fi

    sleep 2
    check_if_process_is_alive
}

function stop() {
    local pid

    # ratel daemon kill
    if [[ ! -f "${APP_PID}" ]];
    then
        echo "${APP_NAME} is not running"
    else
        pid=$(cat ${APP_PID})
        if [[ -z "${pid}" ]];
        then
            echo "${APP_NAME} is not running"
        else
            wait_for_process_to_die ${pid} 40
            $(rm -f ${APP_PID})
            action_msg "${APP_NAME} stop" "${SET_OK}"
        fi
    fi
}

function find_app_process() {
    local pid

    if [[ -f "${APP_PID}" ]];
    then
        pid=$(cat ${APP_PID})
        if ! kill -0 ${pid} > /dev/null 2>&1;
        then
            action_msg "${APP_NAME} running but process is dead" "${SET_ERROR}"
            return 1;
        else
            action_msg "${APP_NAME} is running" "${SET_OK}"
        fi
    else
        action_msg "${APP_NAME} is not running" "${SET_ERROR}"
        return 1;
    fi
}

case "${1}" in
    start)
        start
        ;;
    stop)
        stop
        ;;
    restart)
        echo "${APP_NAME} is restarting" >> "${APP_OUTPUTFILE}"
        stop
        start
        ;;
    status)
        find_app_process
        ;;
    *)
        echo ${USAGE}
esac
