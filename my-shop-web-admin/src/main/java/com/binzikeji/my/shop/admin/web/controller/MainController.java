package com.binzikeji.my.shop.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: Chundekepa
 * @create: 2019-03-02 22:25
 **/
@Controller
public class MainController {

    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String main(){
        return "main";
    }
}
