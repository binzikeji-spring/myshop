package com.binzikeji.my.shop.domain;

import com.binzikeji.my.shop.commons.persistence.BaseEntity;
import com.binzikeji.my.shop.commons.persistence.BaseTreeEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author: Chundekepa
 * @create: 2019-03-10 13:02
 **/
@Data
public class TbContentCategory extends BaseTreeEntity<TbContentCategory> {

    @Length(min = 1, max = 20, message = "分类名应为1-20个字符")
    private String name;
    private Integer status;
    @NotNull(message = "排序不能为空")
    private Integer sortOrder;
    private TbContentCategory parent;
}