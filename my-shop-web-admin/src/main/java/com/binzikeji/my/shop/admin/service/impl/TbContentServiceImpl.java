package com.binzikeji.my.shop.admin.service.impl;

import com.binzikeji.my.shop.admin.abstracts.AbstractBaseServiceImpl;
import com.binzikeji.my.shop.admin.dao.TbContentDao;
import com.binzikeji.my.shop.admin.service.TbContentService;
import com.binzikeji.my.shop.commons.dto.BaseResult;
import com.binzikeji.my.shop.commons.validator.BeanValidator;
import com.binzikeji.my.shop.domain.TbContent;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author: Chundekepa
 * @create: 2019-03-03 13:14
 **/
@Service
public class TbContentServiceImpl extends AbstractBaseServiceImpl<TbContent, TbContentDao> implements TbContentService {

    @Override
    public BaseResult save(TbContent tbContent) {
        String validator = BeanValidator.validator(tbContent);
        // 验证不通过
        if (validator != null){
            return BaseResult.fail(validator);
        }
        // 验证的通过
        else {
            Date date = new Date();
            tbContent.setUpdated(date);
            if (tbContent.getId() == null){
                tbContent.setCreated(date);
                dao.insert(tbContent);
            }
            else {
                dao.update(tbContent);
            }
            return BaseResult.success("保存用户信息成功");
        }
    }
}
