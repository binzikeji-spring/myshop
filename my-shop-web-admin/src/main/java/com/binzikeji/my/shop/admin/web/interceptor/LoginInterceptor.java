package com.binzikeji.my.shop.admin.web.interceptor;

import com.binzikeji.my.shop.commons.centext.utils.CookieUtils;
import com.binzikeji.my.shop.commons.contant.ConstantUtils;
import com.binzikeji.my.shop.domain.TbUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 * @author: Chundekepa
 * @create: 2019-03-02 18:00
 **/
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        TbUser tbUser = (TbUser) httpServletRequest.getSession().getAttribute(ConstantUtils.SESSION_USER);

        //未登录
        if (tbUser == null){
            httpServletResponse.sendRedirect("/login");
        }

        //放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
