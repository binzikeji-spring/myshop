package com.binzikeji.my.shop.web.ui.controller;

import com.binzikeji.my.shop.commons.contant.ConstantUtils;
import com.binzikeji.my.shop.commons.dto.BaseResult;
import com.binzikeji.my.shop.commons.utils.EmailSendUtils;
import com.binzikeji.my.shop.web.ui.api.UsersApi;
import com.binzikeji.my.shop.web.ui.constant.SystemContants;
import com.binzikeji.my.shop.web.ui.dto.TbUser;
import com.google.code.kaptcha.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author Bin
 * @Date 2019/3/15 10:15
 **/
@Controller
public class LoginController {

    @Autowired
    private EmailSendUtils emailSendUtils;

    /**
     * 登录页面
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(TbUser tbUser, Model model, HttpServletRequest request) throws Exception {
        if (!cheheckVerification(tbUser, request)) {
            model.addAttribute("baseResult",BaseResult.fail("验证码输入错误，请重新输入"));
            return "login";
        }

        TbUser user = UsersApi.login(tbUser);

        // 登录失败
        if (user == null){
            model.addAttribute("baseResult", BaseResult.fail("用户名或密码错误，请重新输入！"));
            return "login";
        }

        // 登陆成功
        else {
            emailSendUtils.send("欢迎登陆", String.format("欢迎【%s】登陆商城", user.getUsername()), "1127057739@qq.com");
            request.getSession().setAttribute(SystemContants.SESSION_USER_KEY, user);
            return "redirect:/index";
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/index";
    }

    /**
     * 注册
     * @return
     */
    @RequestMapping(value = "zhuce", method = RequestMethod.GET)
    public String zhuce(){
        return "zhuce";
    }

    /**
     * 验证验证码
     * @param tbUser
     * @param request
     * @return
     */
    private Boolean cheheckVerification(TbUser tbUser, HttpServletRequest request){
        String verification = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.equals(verification,tbUser.getVerification())){
            return true;
        }
        return false;
    }

}
