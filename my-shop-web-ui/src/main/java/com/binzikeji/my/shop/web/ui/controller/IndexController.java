package com.binzikeji.my.shop.web.ui.controller;

import com.binzikeji.my.shop.commons.utils.HttpClientUtils;
import com.binzikeji.my.shop.commons.utils.MapperUtils;
import com.binzikeji.my.shop.web.ui.api.ContentsApi;
import com.binzikeji.my.shop.web.ui.dto.TbContent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author: Chundekepa
 * @create: 2019-03-14 21:34
 **/
@Controller
public class IndexController {

    /**
     * 首页
     * @param model
     * @return
     */
    @RequestMapping(value = {"", "index"}, method = RequestMethod.GET)
    public String index(Model model){
        requestContentsPPT(model);
        return "index";
    }

    /**
     * 请求幻灯片
     * @param model
     */
    private void requestContentsPPT(Model model){
        List<TbContent> tbContents = ContentsApi.ppt();
        model.addAttribute("ppt", tbContents);
    }
}
