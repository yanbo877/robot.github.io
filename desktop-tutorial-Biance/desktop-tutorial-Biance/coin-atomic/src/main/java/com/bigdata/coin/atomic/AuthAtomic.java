package com.bigdata.coin.atomic;

import com.bigdata.coin.dao.po.User;

public interface AuthAtomic {

    User selectByDomainAndPasswd(String userName,String passwd);

}
