package com.binzikeji.my.shop.web.ui.api;

import com.binzikeji.my.shop.commons.dto.BaseResult;
import com.binzikeji.my.shop.commons.utils.HttpClientUtils;
import com.binzikeji.my.shop.commons.utils.MapperUtils;
import com.binzikeji.my.shop.web.ui.dto.TbUser;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author Bin
 * @Date 2019/3/15 11:07
 **/
public class UsersApi {

    /**
     * 登录
     * @return
     */
    public static TbUser login(TbUser tbUser) throws Exception {
        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", tbUser.getUsername()));
        params.add(new BasicNameValuePair("password", tbUser.getPassword()));
        String json = HttpClientUtils.doPost(API.API_USERS_LOGIN, params.toArray(new BasicNameValuePair[params.size()]));
        TbUser user = MapperUtils.json2pojoByTree(json, "data", TbUser.class);
        return user;
    }
}
