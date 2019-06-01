package com.binzikeji.my.shop.web.api.service;

import com.binzikeji.my.shop.domain.TbContent;

import java.util.List;

public interface TbContentService {

    /**
     * 根据类别 ID 查询内容列表
     * @param categroyId
     * @return
     */
    List<TbContent> selectByCategoryId(Long categroyId);
}
