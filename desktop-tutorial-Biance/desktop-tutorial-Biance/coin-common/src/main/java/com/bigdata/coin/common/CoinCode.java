package com.bigdata.coin.common;

import lombok.Getter;

@Getter
public enum CoinCode {

    //常用错误码
    INPUT_PARAMS("4200000"),
    GENERAL("4200001"),
    PARAM_VALIDATION("4200002"),
    DATA_EXISTED("4200003"),
    DATA_NOT_EXISTED("4200004"),


    //任务监控
    MONITOR_NOT_EXISTED("4211100"),
    MONITOR_EXISTED("4211101"),
    NO_MONITOR_PARAM("4211102");

    public final String code;

    CoinCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
