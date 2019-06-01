package com.binzikeji.my.shop.admin.service;

import com.binzikeji.my.shop.commons.persistence.BaseService;
import com.binzikeji.my.shop.domain.TbUser;

public interface TbUserService extends BaseService<TbUser> {

    /**
     * 用户登录
     * @param email
     * @return
     */
    TbUser login(String email, String password);

}
