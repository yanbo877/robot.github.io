package com.bigdata.coin.result;

import com.bigdata.coin.exception.ErrorCode;
import com.github.pagehelper.Page;

/**
 * 返参工具类
 *
 *
 */
public class ResultUtils {

    /**
     * 成功处理数据封装.
     */
    public static Result success() {
        return new PlatformResult();
    }

    public static Result success(Object data) {
        if (data instanceof Page) {
            Page page = (Page) data;
            data = new PageResponse<>(data, page.getTotal(), page.getPages());
        }
        return new PlatformResult(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.toString(), data);
    }

    public static Result success(Object data, String message) {
        return new PlatformResult(ErrorCode.SUCCESS.getCode(), message, data);
    }

    public static Result success(ErrorCode errorCode, String message, Object data) {
        return success(errorCode.getCode(), message, data);
    }

    public static Result success(String code, String message, Object data) {
        return new PlatformResult(code, message, data);
    }

    public static Result error(ErrorCode errorCode, String message, Object data) {
        return success(errorCode.getCode(), message, data);
    }

    /**
     * 失败处理数据封装.
     */
    public static Result exception(Throwable throwable) {
        return exception(ErrorCode.GENERAL.getCode(), throwable.getMessage(), throwable);
    }

    public static Result exception(String errorCode, String message, Throwable throwable) {
        return exception(errorCode, message, throwable, null);
    }

    public static Result exception(String errorCode,
        String message,
        Throwable throwable,
        Object[] params) {
        return new DefaultErrorResult(errorCode, message, throwable, params);
    }
}
