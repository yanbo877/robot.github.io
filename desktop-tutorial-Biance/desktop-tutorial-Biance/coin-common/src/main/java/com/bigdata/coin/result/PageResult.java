package com.bigdata.coin.result;

import com.bigdata.coin.exception.ErrorCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 分页查询的返参.
 */
@Setter
@Getter
public class PageResult<T> implements Result<T> {
    private String code;
    private String message;
    private PageResponse<T> data;

    /**
     * .
     */
    public PageResult(T result, long total, long pages) {
        data = new PageResponse<>(result, total, pages);
        this.code = ErrorCode.SUCCESS.getCode();
        this.message = ErrorCode.SUCCESS.name();
    }

    /**
     * .
     */
    public PageResult(ErrorCode errorCode, T result, long total, long pages) {
        data = new PageResponse<>(result, total, pages);
        this.code = errorCode.getCode();
        this.message = errorCode.name();
    }

    /**
     * .
     */
    public PageResult(String code, String message, T result, long total, long pages) {
        data = new PageResponse<>(result, total, pages);
        this.code = code;
        this.message = message;
    }

}
