package com.bigdata.coin.atomic.impl;

import com.bigdata.coin.atomic.AuthAtomic;
import com.bigdata.coin.dao.mapper.AuthMapper;
import com.bigdata.coin.dao.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class AuthAtomicImpl implements AuthAtomic {

    @Autowired
    private AuthMapper authMapper;

    @Override
    public User selectByDomainAndPasswd(String domain, String passwd) {
        return authMapper.selectByDomainAndPasswd(domain, passwd);
    }
}
