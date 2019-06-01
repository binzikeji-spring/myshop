package com.binzikeji.my.shop.admin.service.impl;

import com.binzikeji.my.shop.admin.abstracts.AbstractBaseTreeServiceImpl;
import com.binzikeji.my.shop.admin.dao.TbContentCategoryDao;
import com.binzikeji.my.shop.admin.service.TbContentCategoryService;
import com.binzikeji.my.shop.commons.dto.BaseResult;
import com.binzikeji.my.shop.commons.dto.PageInfo;
import com.binzikeji.my.shop.commons.validator.BeanValidator;
import com.binzikeji.my.shop.domain.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author: Chundekepa
 * @create: 2019-03-10 13:07
 **/
@Service
@Transactional(readOnly = true)
public class TbContentCategoryServiceImpl extends AbstractBaseTreeServiceImpl<TbContentCategory, TbContentCategoryDao> implements TbContentCategoryService {

    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbContentCategory entity) {
        String validator = BeanValidator.validator(entity);
        if (validator != null){
            return BaseResult.fail(validator);
        }
        else {
            Date date = new Date();
            TbContentCategory parent = entity.getParent();
            entity.setUpdated(date);
            //新增
            if (entity.getId() == null){
                entity.setCreated(date);
                // 如果不存在父级 将默认为根目录
                if (parent == null || parent.getId() == null){
                    parent.setId(0L);
                    entity.setParent(parent);
                    entity.setIsParent(true);
                }
                // 如果存在父级 将现在的节点设置成不是父级
                else {
                    TbContentCategory contentCategory = getById(parent.getId());
                    contentCategory.setIsParent(true);
                    update(contentCategory);
                    entity.setIsParent(false);
                }
                dao.insert(entity);
            }
            // 修改
            else {
                update(entity);
            }
            return BaseResult.success("保存信息成功");
        }
    }
}
