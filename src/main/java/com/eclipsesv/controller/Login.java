package com.eclipsesv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by eclipse on 16/8/30.
 */
@Controller
public class Login {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String register(){
        return "";
    }
}
