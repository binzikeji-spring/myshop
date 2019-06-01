package com.binzikeji.my.shop.admin.service.impl;

import com.binzikeji.my.shop.admin.abstracts.AbstractBaseServiceImpl;
import com.binzikeji.my.shop.admin.abstracts.AbstractBaseTreeServiceImpl;
import com.binzikeji.my.shop.admin.dao.TbUserDao;
import com.binzikeji.my.shop.admin.service.TbUserService;
import com.binzikeji.my.shop.commons.dto.BaseResult;
import com.binzikeji.my.shop.commons.dto.PageInfo;
import com.binzikeji.my.shop.commons.utils.RegexpUtils;
import com.binzikeji.my.shop.commons.validator.BeanValidator;
import com.binzikeji.my.shop.domain.TbUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Chundekepa
 * @create: 2019-03-03 13:14
 **/
@Service
public class TbUserServiceImpl extends AbstractBaseServiceImpl<TbUser, TbUserDao> implements TbUserService {

    public TbUser login(String email, String password) {
        TbUser tbUser = dao.getByEmail(email);
        if (tbUser != null){
            //明文加密
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            //密码匹配允许登录
            if (md5Password.equals(tbUser.getPassword())){
                return tbUser;
            }
        }
        return null;
    }

    @Override
    public BaseResult save(TbUser tbUser) {
        String validator = BeanValidator.validator(tbUser);
        //验证不通过
        if (validator != null){
            return BaseResult.fail(validator);
        }
        // 验证通过
        else {
            Date date = new Date();
            tbUser.setUpdated(date);
            if (tbUser.getId() == null){
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                tbUser.setCreated(date);
                dao.insert(tbUser);
            }
            else {
                dao.update(tbUser);
            }
            return BaseResult.success("保存用户信息成功");
        }
    }
}
