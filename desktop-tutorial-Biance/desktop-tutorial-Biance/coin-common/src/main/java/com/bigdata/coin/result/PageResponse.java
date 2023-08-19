package com.bigdata.coin.result;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class PageResponse<T> implements Serializable {
    private T result;
    /**
     * 记录总数.
     */
    private long total;
    /**
     * 总页数.
     */
    private long pages;

    /**
     * 分页结果.
     */
    public PageResponse(T result, long total, long pages) {
        this.result = result;
        this.total = total;
        this.pages = pages;
    }
}
