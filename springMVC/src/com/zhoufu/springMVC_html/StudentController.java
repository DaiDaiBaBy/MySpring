package com.zhoufu.springMVC_html;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: zhoufu
 * @Date: 2021/1/29 9:39
 * @description:
 */
@Controller
public class StudentController{

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public ModelAndView student(){
        // public ModelAndView(View view, @Nullable Map<String, ?> model)
        return new ModelAndView("student", "command", new Student());
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute("SpringWeb") Student student, ModelMap model){
        model.addAttribute("name", student.getName());
        model.addAttribute("age", student.getAge());
        model.addAttribute("id", student.getId());
        return "result";
    }
}
