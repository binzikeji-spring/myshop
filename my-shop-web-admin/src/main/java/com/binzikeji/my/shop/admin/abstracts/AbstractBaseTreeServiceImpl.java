package com.binzikeji.my.shop.admin.abstracts;

import com.binzikeji.my.shop.commons.persistence.BaseEntity;
import com.binzikeji.my.shop.commons.persistence.BaseTreeDao;
import com.binzikeji.my.shop.commons.persistence.BaseTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Chundekepa
 * @create: 2019-03-12 20:10
 **/
public abstract class AbstractBaseTreeServiceImpl<T extends BaseEntity, D extends BaseTreeDao<T>> implements BaseTreeService<T> {

    @Autowired
    protected D dao;

    @Override
    public List<T> selectByPid(Long pid) {
        return dao.selectByPid(pid);
    }

    @Override
    public List<T> selectAll() {
        return dao.selectAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public T getById(Long id) {
        return dao.getById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(T entity) {
        dao.update(entity);
    }

    @Transactional(readOnly = false)
    public void insert(T entity){
        dao.insert(entity);
    }
}
