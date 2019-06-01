package com.binzikeji.my.shop.admin.abstracts;

import com.binzikeji.my.shop.commons.dto.BaseResult;
import com.binzikeji.my.shop.commons.dto.PageInfo;
import com.binzikeji.my.shop.commons.persistence.BaseEntity;
import com.binzikeji.my.shop.commons.persistence.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Chundekepa
 * @create: 2019-03-12 22:09
 **/
public abstract class AbstractBaseController<T extends BaseEntity, S extends BaseService<T>> {

    @Autowired
    protected S service;

    /**
     * 列表
     * @return
     */
    public abstract String list();

    /**
     * 表单
     * @return
     */
    public abstract String form();

    /**
     * 保存
     * @param entity
     * @param model
     * @param redirectAttributes
     * @return
     */
    public abstract String save(T entity, Model model, RedirectAttributes redirectAttributes);

    /**
     * 删除
     * @param ids
     * @return
     */
    public abstract BaseResult delete(String ids);

    /**
     * 分页查询
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public PageInfo<T> page(HttpServletRequest request, T entity){
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String setLength = request.getParameter("length");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = setLength == null ? 0 : Integer.parseInt(setLength);

        PageInfo<T> pageInfo = service.page(start,length,draw,entity);
        return pageInfo;
    }

    /**
     * 详情
     * @return
     */
    public abstract String detail();
}
