package com.binzikeji.my.shop.admin.web.controller;

import com.binzikeji.my.shop.admin.abstracts.AbstractBaseTreeController;
import com.binzikeji.my.shop.admin.service.TbContentCategoryService;
import com.binzikeji.my.shop.commons.dto.BaseResult;
import com.binzikeji.my.shop.domain.TbContent;
import com.binzikeji.my.shop.domain.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Chundekepa
 * @create: 2019-03-10 13:08
 **/
@Controller
@RequestMapping(value = "content/category")
public class TbContentCategoryController extends AbstractBaseTreeController<TbContentCategory, TbContentCategoryService> {

    @ModelAttribute
    private TbContentCategory getTbContentCategory(Long id){
        TbContentCategory tbContentCategory;
        // id 不为空
        if (id != null){
            tbContentCategory = service.getById(id);
        }
        // id 为空
        else {
            tbContentCategory = new TbContentCategory();
        }
        return tbContentCategory;
    }

    @Override
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model){
        List<TbContentCategory> tbContenCategories = service.selectAll();
        List<TbContentCategory> targetList = new ArrayList<>();
        // 排序
        sortList(tbContenCategories, targetList, 0L);
        model.addAttribute("tbContentCategories", targetList);
            return "content_category_list";
    }

    /**
     * 用户表单
     * @return
     */
    @Override
    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(TbContentCategory tbContentCategory){
        return "content_category_form";
    }

    @Override
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbContentCategory tbContentCategory, Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult = service.save(tbContentCategory);
        if (baseResult.getStatus() == 200){
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/content/category/list";
        }
        else {
            model.addAttribute("baseResult", baseResult);
            return form(tbContentCategory);
        }
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "tree/data", method = RequestMethod.POST)
    public List<TbContentCategory> treeData(Long id){
        if (id == null) id = 0L;
        return service.selectByPid(id);
    }
}
