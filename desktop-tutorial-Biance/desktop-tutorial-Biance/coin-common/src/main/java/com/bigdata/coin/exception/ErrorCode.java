package com.bigdata.coin.exception;

/**
 * 错误码
 */
public enum ErrorCode {

    //成功
    SUCCESS("1000000"),

    //系统错误
    GENERAL("1000001"),

    //入参校验失败
    PARAM_VALIDATION("1000002"),

    //数据已存在
    DATA_EXISTED("1000003"),

    //数据不存在
    DATA_NOT_EXISTED("1000004"),

    //配置信息不正确
    CONFIGURATION_ERROR("1000005"),

    //创建数据连接失败
    DB_CONN_FAILED("1000006"),

    //SQL注入检查失败
    SQL_INJECTION_CHECK_FAILED("1000007"),

    //上传文件检查失败
    UPLOAD_FILE_CHECK_FAILED("1000008"),

    //XSS检查失败
    XSS_CHECK_FAILED("1000009"),

    //超出限速
    RATELIMITER_CHECK_FAILED("1000010"),

    //端口占用
    PORT_IN_USED("1000012"),

    //数据已经被使用
    DATA_USED("1000013"),
    // 脏数据
    DIRTY_DATA("1000014"),
    // 无权限访问
    NO_PERMISSION("1000015"),
    //工作流边异常
    WORKFLOW_EDGE("1001000"),
    //工作流边异常
    WORKFLOW_NODE_PARAM("1001001");


    ErrorCode(String code) {
        this.code = code;
    }

    public final String code;

    public String getCode() {
        return code;
    }
}
