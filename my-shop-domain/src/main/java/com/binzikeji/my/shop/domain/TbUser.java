package com.binzikeji.my.shop.domain;

import com.binzikeji.my.shop.commons.persistence.BaseEntity;
import com.binzikeji.my.shop.commons.utils.RegexpUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.security.sasl.SaslServer;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: Chundekepa
 * @create: 2019-03-03 13:07
 **/
@Data
public class TbUser extends BaseEntity {

    @Length(min = 6, max = 20, message = "用户名长度应为6-20个字符")
    private String username;
    @JsonIgnore
    @Length(min = 6, max = 20, message = "密码长度应为6-20个字符")
    private String password;
    @Pattern(regexp = RegexpUtils.PHONE, message = "请输入正确的手机号")
    private String phone;
    @Pattern(regexp = RegexpUtils.EMAIL, message = "请输入正确的邮箱")
    private String email;
}
