package com.bigdata.coin.service;

import com.bigdata.coin.service.dto.UserResponse;


public interface AuthService {

    /**
     * login.
     */
    UserResponse login(String userName,String passwd);

}
