package com.bigdata.coin.result;

import com.bigdata.coin.exception.ErrorCode;
import com.bigdata.coin.utils.ThrowableUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * 失败返参
 *
 */
@Setter
@Getter
public class DefaultErrorResult<T> implements Result<T> {

    private static final long serialVersionUID = 839456047538289714L;

    private String code;
    private String message;
    private ErrorResponse data;
    

    public DefaultErrorResult(Throwable throwable) {
        this(ErrorCode.GENERAL.getCode(), ErrorCode.GENERAL.name(), throwable);
    }

    public DefaultErrorResult(ErrorCode errorCode, Throwable throwable) {
        this(errorCode.getCode(), errorCode.name(), throwable);
    }

    public DefaultErrorResult(String errorCode, String message, Throwable throwable) {
        this(errorCode, message, throwable, null);
    }

    /**
     * 默认错误构造方法.
     *
     * @param errorCode 错误码
     * @param throwable 异常
     * @param params    参数
     */
    public DefaultErrorResult(String errorCode, Throwable throwable, Object[] params) {
        this(errorCode, throwable.getMessage(), throwable, params);
    }

    /**
     * 默认错误构造方法.
     *
     * @param errorCode 错误码
     * @param throwable 异常
     * @param params    参数
     */
    public DefaultErrorResult(String errorCode, String message, Throwable throwable, Object[] params) {
        this.code = errorCode;
        this.message = message;
        this.data = new ErrorResponse();
        String exception = ThrowableUtils.extractStackTrace(throwable);
        this.data.setException(exception);
        this.data.setParams(params);
    }
}
