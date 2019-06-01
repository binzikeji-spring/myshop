package com.binzikeji.my.shop.admin.web.controller;

import com.binzikeji.my.shop.admin.abstracts.AbstractBaseController;
import com.binzikeji.my.shop.admin.service.TbContentCategoryService;
import com.binzikeji.my.shop.admin.service.TbContentService;
import com.binzikeji.my.shop.commons.dto.BaseResult;
import com.binzikeji.my.shop.commons.dto.PageInfo;
import com.binzikeji.my.shop.domain.TbContent;
import com.binzikeji.my.shop.domain.TbContentCategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 用户管理
 * @Author Bin
 * @Date 2019/3/4 16:01
 **/
@Controller
@RequestMapping(value = "content")
public class TbContentController extends AbstractBaseController<TbContent, TbContentService> {

    /**
     *
     * @param id
     * @return
     */
    @ModelAttribute
    public TbContent getTbContent(Long id){
        TbContent tbContent;
        //id不为空
        if (id != null){
            tbContent = service.getById(id);
        }
        //id为空
        else {
            tbContent = new TbContent();
        }
        return tbContent;
    }

    /**
     * 展示用户列表
     * @return
     */
    @Override
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(){
        return "content_list";
    }

    /**
     * 用户表单
     * @return
     */
    @Override
    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(){
        return "content_form";
    }

    /**
     *
     * @param tbContent
     * @param redirectAttributes
     * @return
     */
    @Override
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbContent tbContent,Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult = service.save(tbContent);
        //成功
        if (baseResult.getStatus() == 200){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/content/list";
        }
        //失败
        else {
            model.addAttribute("tbContent",tbContent);
            model.addAttribute("baseResult",baseResult);
            return "content_form";
        }
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public BaseResult delete(String ids){
        BaseResult baseResult;
        if (StringUtils.isNotBlank(ids)){
            String[] idArray = ids.split(",");
            service.deleteMulti(idArray);
            baseResult = BaseResult.success("删除数据成功...");
        }
        else {
            baseResult = BaseResult.fail("删除失败...");
        }
        return baseResult;
    }

    @Override
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(){
        return "content_detail";
    }
}
