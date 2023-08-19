package com.bigdata.coin.service.dto;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * 用户返回数据封装.
 */
@Getter
@Setter
public class UserResponse {

    @NonNull
    private String domain;
    private String name;
    private String sex;
}
