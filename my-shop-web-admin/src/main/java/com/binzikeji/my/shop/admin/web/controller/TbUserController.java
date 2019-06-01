package com.binzikeji.my.shop.admin.web.controller;

import com.binzikeji.my.shop.admin.abstracts.AbstractBaseController;
import com.binzikeji.my.shop.admin.service.TbUserService;
import com.binzikeji.my.shop.commons.dto.BaseResult;
import com.binzikeji.my.shop.commons.dto.PageInfo;
import com.binzikeji.my.shop.domain.TbUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 用户管理
 * @Author Bin
 * @Date 2019/3/4 16:01
 **/
@Controller
@RequestMapping(value = "user")
public class TbUserController extends AbstractBaseController<TbUser, TbUserService> {

    /**
     *
     * @param id
     * @return
     */
    @ModelAttribute
    public TbUser getTbUser(Long id){
        //id不为空
        if (id != null){
            return service.getById(id);
        }
        //id为空
        else {
            return new TbUser();
        }
    }

    /**
     * 展示用户列表
     * @return
     */
    @Override
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(){
        return "user_list";
    }

    /**
     * 用户表单
     * @return
     */
    @Override
    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(){
        return "user_form";
    }

    /**
     *
     * @param tbUser
     * @param redirectAttributes
     * @return
     */
    @Override
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbUser tbUser,Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult = service.save(tbUser);
        //成功
        if (baseResult.getStatus() == 200){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/user/list";
        }
        //失败
        else {
            model.addAttribute("tbUser",tbUser);
            model.addAttribute("baseResult",baseResult);
            return "user_form";
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
        return "user_detail";
    }
}
