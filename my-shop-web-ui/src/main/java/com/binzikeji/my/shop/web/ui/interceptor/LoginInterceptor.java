package com.binzikeji.my.shop.web.ui.interceptor;

import com.binzikeji.my.shop.web.ui.constant.SystemContants;
import com.binzikeji.my.shop.web.ui.dto.TbUser;
import org.aopalliance.intercept.Interceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description 登录拦截器
 * @Author Bin
 * @Date 2019/3/15 15:12
 **/
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        TbUser tbUser = (TbUser) httpServletRequest.getSession().getAttribute(SystemContants.SESSION_USER_KEY);
        // 未登录
        if (tbUser == null){
            return true;
        }
        // 已登录
        else {
            httpServletResponse.sendRedirect("/index");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
