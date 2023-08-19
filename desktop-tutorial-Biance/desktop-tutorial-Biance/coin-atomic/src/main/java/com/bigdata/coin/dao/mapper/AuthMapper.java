package com.bigdata.coin.dao.mapper;

import com.bigdata.coin.dao.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户Mapper.
 */
@Mapper
public interface AuthMapper {
    User selectByDomainAndPasswd(@Param("domain") String domain, @Param("passwd") String passwd);
}
