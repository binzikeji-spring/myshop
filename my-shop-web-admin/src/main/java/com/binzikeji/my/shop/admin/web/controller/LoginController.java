package com.binzikeji.my.shop.admin.web.controller;

import com.binzikeji.my.shop.admin.service.TbUserService;
import com.binzikeji.my.shop.commons.centext.utils.CookieUtils;
import com.binzikeji.my.shop.commons.contant.ConstantUtils;
import com.binzikeji.my.shop.domain.TbUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author: Chundekepa
 * @create: 2019-03-02 21:56
 **/
@Controller
public class LoginController {

    @Autowired
    private TbUserService tbUserService;

    /**
     * 登录页面
     * @return
     */
    @RequestMapping(value = {"", "login"}, method = RequestMethod.GET)
    public String login(HttpServletRequest request){
        //查看 Cookie
        String userInfo = CookieUtils.getCookieValue(request, request, ConstantUtils.COOKIE_USER);
        //Cookie 有值自动填写
        if (!StringUtils.isBlank(userInfo)){
            String[] split = userInfo.split(":");
            String email = split[0];
            String password = split[1];
            request.setAttribute("email",email);
            request.setAttribute("password",password);
            request.setAttribute("isRmemeber",true);
        }
        return "login";
    }

    /**
     * 登录逻辑
     * @param email 邮箱
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(String email, String password, HttpServletRequest request, HttpServletResponse response){
        TbUser tbUser = tbUserService.login(email,password);
        //登录失败
        if (tbUser == null){
            request.setAttribute("msg","账号或密码错误");
            return login(request);
        }
        //登陆成功
        else {
            //记住我
            String isRemember = request.getParameter("isRmemeber");
            if (isRemember != null){
                CookieUtils.setCookie(request,response,ConstantUtils.COOKIE_USER,String.format("%s:%s",email,password),7*24*60*60);
            } else {
                CookieUtils.deleteCookie(request,response,ConstantUtils.COOKIE_USER);
            }
            request.getSession().setAttribute(ConstantUtils.SESSION_USER, tbUser);
            return "redirect:/main";
        }
    }

    @RequestMapping(value = "loginout",method = RequestMethod.GET)
    public String loginOut(HttpServletRequest request){
        request.getSession().removeAttribute(ConstantUtils.SESSION_USER);
        return "redirect:/login";
    }
}
