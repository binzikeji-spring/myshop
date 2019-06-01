package com.binzikeji.my.shop.web.api.service.impl;

import com.binzikeji.my.shop.domain.TbUser;
import com.binzikeji.my.shop.web.api.dao.TbUserDao;
import com.binzikeji.my.shop.web.api.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @Description
 * @Author Bin
 * @Date 2019/3/15 10:37
 **/
@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserDao tbUserDao;

    /**
     * 登录验证
     * @param tbUser
     * @return
     */
    @Override
    public TbUser login(TbUser tbUser) {
        TbUser user = tbUserDao.login(tbUser);
        if (user != null){
            String password = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
            if (password.equals(user.getPassword())){
                return user;
            }
        }
        return null;
    }
}
