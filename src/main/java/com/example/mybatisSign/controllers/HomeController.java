package com.example.mybatisSign.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

    @RequestMapping("/test")
    public ModelAndView Index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("views/home/index");

        return mav;
    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}
