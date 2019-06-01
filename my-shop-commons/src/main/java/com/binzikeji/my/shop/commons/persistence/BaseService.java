package com.binzikeji.my.shop.commons.persistence;

import com.binzikeji.my.shop.commons.dto.BaseResult;
import com.binzikeji.my.shop.commons.dto.PageInfo;

import java.util.List;

/**
 * @Description
 * @Author Bin
 * @Date 2019/3/12 15:49
 **/
public interface BaseService<T extends BaseEntity> {
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
     * 批量删除
     * @param ids
     */
    void deleteMulti(String[] ids);

    /**
     * 分页查询
     * @param start 记录数开始位置
     * @param length 每页数量
     * @return
     */
    PageInfo<T> page(int start, int length, int draw, T entity);

    /**
     * 查询总笔数
     * @return
     */
    int count(T entity);

    /**
     * 插入
     * @param entity
     */
    void insert(T entity);
}
