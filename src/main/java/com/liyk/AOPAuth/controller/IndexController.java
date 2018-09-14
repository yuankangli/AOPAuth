package com.liyk.AOPAuth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 跳转到首页
 * @author lyk
 */
@Controller
public class IndexController {

    @RequestMapping("")
    public String index(){
        return "redirect:views/index.html";
    }
}
