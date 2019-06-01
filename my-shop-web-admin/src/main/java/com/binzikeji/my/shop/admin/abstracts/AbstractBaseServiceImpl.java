package com.binzikeji.my.shop.admin.abstracts;

import com.binzikeji.my.shop.commons.dto.PageInfo;
import com.binzikeji.my.shop.commons.persistence.*;
import com.binzikeji.my.shop.domain.TbContent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Chundekepa
 * @create: 2019-03-12 20:10
 **/
public abstract class AbstractBaseServiceImpl<T extends BaseEntity, D extends BaseDao<T>> implements BaseService<T> {

    @Autowired
    protected D dao;

    @Override
    public List<T> selectAll() {
        return dao.selectAll();
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public T getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public void update(T entity) {
        dao.update(entity);
    }

    @Override
    public void deleteMulti(String[] ids) {
        dao.deleteMulti(ids);
    }

    @Override
    public PageInfo<T> page(int start, int length, int draw, T entity) {
        int count = dao.count(entity);

        Map<String,Object> map = new HashMap<>();
        map.put("start",start);
        map.put("length",length);
        map.put("pageParams",entity);

        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(dao.page(map));
        return pageInfo;
    }

    @Override
    public int count(T entity) {
        return dao.count(entity);
    }

    @Override
    public void insert(T entity){
        dao.insert(entity);
    }
}
