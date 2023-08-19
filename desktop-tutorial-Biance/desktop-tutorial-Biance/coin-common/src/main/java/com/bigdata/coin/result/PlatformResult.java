package com.bigdata.coin.result;

import com.bigdata.coin.exception.ErrorCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 平台返回结果数据封装.
 *
 */
@Setter
@Getter
public class PlatformResult<T> implements Result<T> {

    private static final long serialVersionUID = -5345360483390888492L;

    private String code;
    private String message;
    private T data;

    public PlatformResult() {
        this.code = ErrorCode.SUCCESS.getCode();
    }

    /**
     * 快速构造返参.
     * @param data 返回数据
     */
    public PlatformResult(T data) {
        this.code = ErrorCode.SUCCESS.getCode();
        this.message = ErrorCode.SUCCESS.toString();
        this.data = data;
    }

    /**
     * 构造方法.
     *
     * @param errorCode 错误码
     * @param data      数据
     */
    public PlatformResult(ErrorCode errorCode, T data) {
        this.code = errorCode.getCode();
        this.message = errorCode.getCode() + ".solution";
        this.data = data;
    }

    /**
     * .
     *
     * @param code    Return Code
     * @param message Return Message
     * @param data    Return Data
     */
    public PlatformResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
