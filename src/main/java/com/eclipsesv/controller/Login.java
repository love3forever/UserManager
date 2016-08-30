package com.eclipsesv.controller;

import com.eclipsesv.dao.UserDAOImpl;
import com.eclipsesv.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.UUID;

/**
 * Created by eclipse on 16/8/30.
 */
@Controller
@Transactional
public class Login {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String register(ModelMap model, User user){
        User insertUser = new User();
        insertUser.setUserName(user.getUserName());
        insertUser.setEmail(user.getEmail());
        insertUser.setPassword(user.getPassword());
        insertUser.setCreatedDate(new Date());
        UUID uuid = UUID.randomUUID();
        insertUser.setUserId(uuid.toString());
        userDAOImpl.saveUser(insertUser);
        System.out.println(insertUser.getUserId());
        model.addAttribute("username",insertUser.getUserName());
        return "main";
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String newUser(ModelMap model){
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @Autowired
    private UserDAOImpl userDAOImpl;

}
