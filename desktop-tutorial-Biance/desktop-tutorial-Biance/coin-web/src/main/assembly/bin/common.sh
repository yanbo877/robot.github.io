#!/bin/bash

if [[ -z "${APP_HOME}" ]];
then
    export APP_HOME="$(cd "`dirname "$0"`"/..; pwd)"
fi

if [[ -z "${APP_CONFIG_DIR}" ]];
then
    export APP_CONFIG_DIR="${APP_HOME}/conf"
fi

if [[ -z "${APP_LOG_DIR}" ]];
then
    export APP_LOG_DIR="${APP_HOME}/logs"
fi

if [[ -z "${APP_PID_DIR}" ]]; then
  export APP_PID_DIR="${APP_HOME}/pid"
fi

if [[ -f "${APP_HOME}/bin/env.sh" ]];
then
   . "${APP_HOME}/bin/env.sh"
fi


# 将jar包放入classpath中
function addEachJarInDir() {
    if [[ -d "${1}" ]];
    then
        for jar in $(find -L "${1}" -maxdepth 1 -name '*.jar');
        do
            APP_CLASSPATH="${APP_CLASSPATH}:${jar}"
        done
    fi
}

# 递归将jar包放入classpath中
function addEachJarInDirRecursive() {
    if [[ -d "${1}" ]];
    then
        for jar in $(find -L "${1}" -type f -name "*.jar");
        do
            APP_CLASSPATH="${APP_CLASSPATH}:${jar}"
        done
    fi
}


# 设置APP_ENCODING编码
if [[ -z "${APP_ENCODING}" ]];
then
    export APP_ENCODING="UTF-8"
fi

# 设置JAVA_MEM默认内存大小
if [[ -z "${JAVA_MEM}" ]];
then
    export JAVA_MEM="-Xms2048M -Xmx2048M -XX:MaxPermSize=1024M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp -XX:MaxDirectMemorySize=4096M"
fi

JAVA_OPTS+=" ${JAVA_OPTS} -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 -Dfile.encodings=${APP_ENCODING} ${JAVA_MEM}"
export JAVA_OPTS

# 配置Java 环境
if [[ -n "${JAVA_HOME}" ]]
then
    APP_RUNNER="${JAVA_HOME}/bin/java"
else
    APP_RUNNER=java
fi
export APP_RUNNER
