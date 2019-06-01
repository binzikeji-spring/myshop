package com.binzikeji.my.shop.web.api.web.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author Bin
 * @Date 2019/3/14 17:44
 **/
@Data
public class TbContentDTO implements Serializable {

    private Long id;
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
}
