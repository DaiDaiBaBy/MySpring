package com.zhoufu;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: zhoufu
 * @Date: 2021/1/28 10:38
 * @description:
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping(method = RequestMethod.GET)
    public String printHello(ModelMap model){
        model.addAttribute("message", "Hello Spring MVC Framework!");
        return "hello";
    }
}
