package com.binzikeji.my.shop.web.api.dao;

import com.binzikeji.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Author Bin
 * @Date 2019/3/15 10:34
 **/
@Repository
public interface TbUserDao {

    /**
     * 登录
     * @param tbUser
     * @return
     */
    TbUser login(TbUser tbUser);
}
