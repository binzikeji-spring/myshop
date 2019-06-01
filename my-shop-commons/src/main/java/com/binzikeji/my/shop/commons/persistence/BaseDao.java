package com.binzikeji.my.shop.commons.persistence;

import java.util.List;
import java.util.Map;

public interface BaseDao<T extends BaseEntity> {
    /**
     * 查询全部信息
     * @return
     */
    List<T> selectAll();

    /**
     * 插入
     * @param entity
     */
    void insert(T entity);

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
     * 批量删除
     * @param ids
     */
    void deleteMulti(String[] ids);

    /**
     * 分页查询
     * @param map 需要两个参数start 记录数开始位置，length 每页数量
     * @return
     */
    List<T> page(Map<String, Object> map);

    /**
     * 查询总笔数
     * @return
     */
    int count(T entity);
}
