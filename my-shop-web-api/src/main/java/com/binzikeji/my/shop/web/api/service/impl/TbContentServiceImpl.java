package com.binzikeji.my.shop.web.api.service.impl;

import com.binzikeji.my.shop.domain.TbContent;
import com.binzikeji.my.shop.domain.TbContentCategory;
import com.binzikeji.my.shop.web.api.dao.TbContentDao;
import com.binzikeji.my.shop.web.api.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description
 * @Author Bin
 * @Date 2019/3/14 16:45
 **/
@Service
@Transactional(readOnly = true)
public class TbContentServiceImpl implements TbContentService {

    @Autowired
    private TbContentDao tbContentDao;

    @Override
    public List<TbContent> selectByCategoryId(Long categroyId) {
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setId(categroyId);

        TbContent tbContent = new TbContent();
        tbContent.setTbContentCategory(tbContentCategory);

        return tbContentDao.selectByCategoryId(tbContent);
    }
}
