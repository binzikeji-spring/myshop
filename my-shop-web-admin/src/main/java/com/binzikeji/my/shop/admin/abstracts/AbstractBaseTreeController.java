package com.binzikeji.my.shop.admin.abstracts;

import com.binzikeji.my.shop.commons.persistence.BaseTreeEntity;
import com.binzikeji.my.shop.commons.persistence.BaseTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author: Chundekepa
 * @create: 2019-03-12 22:22
 **/
public abstract class AbstractBaseTreeController<T extends BaseTreeEntity<T>, S extends BaseTreeService<T>>{

    @Autowired
    protected S service;

    /**
     * 列表
     * @param model
     * @return
     */
    public abstract String list(Model model);

    /**
     * 表单
     * @param entity
     * @return
     */
    public abstract String form(T entity);

    /**
     * 保存
     * @param entity
     * @param model
     * @param redirectAttributes
     * @return
     */
    public abstract String save(T entity, Model model, RedirectAttributes redirectAttributes);

    /**
     * 树形结构
     * @param id
     * @return
     */
    public abstract List<T> treeData(Long id);

    /**
     * 排序
     * @param sourceList 数据源集合
     * @param targetList 排序后的集合
     * @param parentId 父节点 ID
     */
    protected void sortList(List<T> sourceList, List<T> targetList, Long parentId){
        for (T sourceEntity : sourceList) {
            if (sourceEntity.getParent().getId().equals(parentId)){
                targetList.add(sourceEntity);

                // 判断有没有子节点，如果有则继续追加
                if (sourceEntity.getIsParent()){
                    for (T contenEntity : sourceList) {
                        if (contenEntity.getParent().getId().equals(sourceEntity.getId())){
                            sortList(sourceList, targetList, sourceEntity.getId());
                            break;
                        }
                    }
                }
            }
        }
    }
}
