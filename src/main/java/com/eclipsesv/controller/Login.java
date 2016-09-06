package com.eclipsesv.controller;

import com.eclipsesv.dao.UserDAOImpl;
import com.eclipsesv.model.User;
import com.eclipsesv.shiro.MyShiroCasReaml;
import com.eclipsesv.shiro.ShiroConfiguration;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
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
    public String index(Model model){
        model.addAttribute("user",new User());
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser != null) {
            PrincipalCollection principals = currentUser.getPrincipals();
            String username = (String) principals.getPrimaryPrincipal();
            User loginUser = userDAOImpl.findByName(username);
            if (loginUser != null) {
                //如果可以查询到该用户名对应的用户，返回用户，并查询该用户对应的用户组以及他在用户组中
                //的角色，从而赋予他相应的权限
                System.out.println(loginUser.getPassword());
                System.out.println(loginUser.getUserId());
            }
            model.addAttribute("user", loginUser);
            return "main";
        }
        else
            return "redirect:" + ShiroConfiguration.loginUrl;
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
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
        return "200";
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String newUser(ModelMap model){

        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }




    @RequestMapping(value = "/cas",method = RequestMethod.POST)
    public String casLogin(String username,ModelMap model){
        System.out.println(username);
        model.addAttribute("username", username);
        return "group";
    }


    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String noaut() {
        return "403";
    }

    @RequestMapping(value = "/200", method = RequestMethod.GET)
    public String success() {
        return "200";
    }

    @Autowired
    private UserDAOImpl userDAOImpl;

}
