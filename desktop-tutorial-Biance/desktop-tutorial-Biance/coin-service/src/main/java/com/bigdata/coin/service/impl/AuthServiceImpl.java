package com.bigdata.coin.service.impl;

import com.bigdata.coin.atomic.AuthAtomic;
import com.bigdata.coin.service.AuthService;
import com.bigdata.coin.service.dto.UserResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthAtomic authAtomic;

    @Override
    public UserResponse login(String domain, String passwd) {
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(authAtomic.selectByDomainAndPasswd(domain,passwd),userResponse);
        return userResponse;
    }
}
