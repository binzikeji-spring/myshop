package com.binzikeji.my.shop.commons.persistence;

import com.binzikeji.my.shop.commons.dto.BaseResult;

import java.util.List;

public interface BaseTreeService<T extends BaseEntity> {
    /**
     * 查询全部信息
     * @return
     */
    List<T> selectAll();


    /**
     * 删除
     * @param id
     */
    void delete(Long id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 更新数据
     * @param entity
     */
    void update(T entity);

    /**
     * 新增或修改
     * @param entity
     */
    BaseResult save(T entity);

    /**
     * 根据父级节点 ID 查询所有子节点
     * @return
     */
    List<T> selectByPid(Long pid);
}
