package com.binzikeji.my.shop.web.api.web.controller.v1;

import com.binzikeji.my.shop.commons.dto.BaseResult;
import com.binzikeji.my.shop.domain.TbUser;
import com.binzikeji.my.shop.web.api.service.TbUserService;
import com.binzikeji.my.shop.web.api.web.dto.TbUserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author Bin
 * @Date 2019/3/15 10:44
 **/
@RestController
@RequestMapping(value = "${web.api.v1}/users")
public class TbUserController {

    @Autowired
    private TbUserService tbUserService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public BaseResult login(TbUser tbUser){
        TbUser user = tbUserService.login(tbUser);
        if (user == null){
            return BaseResult.fail("账号或密码错误");
        }
        else {
            TbUserDTO dto = new TbUserDTO();
            BeanUtils.copyProperties(user, dto);
            return BaseResult.success("成功", dto);
        }
    }
}
