package com.binzikeji.my.shop.web.api.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author Bin
 * @Date 2019/3/15 11:30
 **/
@Data
public class TbUserDTO implements Serializable {

    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String phone;
    private String email;
}
