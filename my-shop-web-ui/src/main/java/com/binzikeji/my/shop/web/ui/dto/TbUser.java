package com.binzikeji.my.shop.web.ui.dto;

import lombok.Data;

/**
 * @Description
 * @Author Bin
 * @Date 2019/3/15 11:08
 **/
@Data
public class TbUser {

    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String verification;
}
