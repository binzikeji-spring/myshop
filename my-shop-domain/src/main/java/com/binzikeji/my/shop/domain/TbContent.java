package com.binzikeji.my.shop.domain;

import com.binzikeji.my.shop.commons.persistence.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author: Chundekepa
 * @create: 2019-03-10 23:14
 **/
@Data
public class TbContent extends BaseEntity {

    @Length(min = 1, max = 20, message = "标题应为1-20个字符")
    private String title;
    @Length(min = 1, max = 20, message = "子标题应为1-20个字符")
    private String subTitle;
    @Length(min = 1, max = 50, message = "标题描述应为1-50个字符")
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
    @Length(min = 1, message = "详情不能为空")
    private String content;

    @NotNull(message = "父类目不能为空")
    private TbContentCategory tbContentCategory;
}
