package com.eclipsesv.controller;

import com.eclipsesv.dao.GroupDAOImpl;
import com.eclipsesv.dao.GroupUserDAOImpl;
import com.eclipsesv.dao.UserDAOImpl;
import com.eclipsesv.model.Groups;
import com.eclipsesv.model.User;
import com.eclipsesv.model.UserGroup;
import com.eclipsesv.shiro.MyShiroCasReaml;
import com.eclipsesv.shiro.ShiroConfiguration;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
            List<Groups> glist = groupUserDAOImpl.listGroup(loginUser.getUserId());
            model.addAttribute("groups", glist);
            Groups newgroup = new Groups();
            model.addAttribute("newgroup", newgroup);
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


    @RequestMapping(value = "/group/{groupid}",method = RequestMethod.GET)
    public String group(@PathVariable String groupid,Model model){
        System.out.println(groupid);
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
            List<User> gusers = groupUserDAOImpl.listMember(groupid);
            for (User u:
                 gusers) {
                System.out.println(u.getUserName());
            }
            System.out.println(gusers.size());
            model.addAttribute("users", gusers);

            List<User> allUsers = new ArrayList<>();
            allUsers = userDAOImpl.listUsers();
            model.addAttribute("userlist", allUsers);

            Groups group = groupDAOImpl.findByID(groupid);
            model.addAttribute("groupid", group);

            return "group";
        }
        return "redirect:" + ShiroConfiguration.loginUrl;
    }

    @RequestMapping(value = "/group/adduser",method = RequestMethod.POST)
    @ResponseBody
    public String addmember(@RequestBody String[] userArray){
        if (userArray != null) {

            String gid = userArray[0];
            for (int i = 1; i < userArray.length; i++) {
                UUID uuid = UUID.randomUUID();
                UserGroup newug = new UserGroup();
                newug.setMemID(uuid.toString());
                newug.setGroupID(gid);
                newug.setUserName(userArray[i]);
                newug.setJoinDate(new Date());
                newug.setRole("member");
                groupUserDAOImpl.addMember(newug);
                System.out.println("向"+gid+"添加用户"+userArray[i]);
            }

            System.out.println("OK");
            return "redirect:/group/"+gid;
        }
        else {
            System.out.println("No");
            return "redirect:/";
        }
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String casLogout() {
        Subject current_user = SecurityUtils.getSubject();
        current_user.logout();
        System.out.println("Logout");
        return "redirect:" + ShiroConfiguration.casLogoutUrl;
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


    @RequestMapping(value = "/newgroup",method = RequestMethod.POST)
    public String newGroup(ModelMap model,Groups groups) {
        Subject currentUser = SecurityUtils.getSubject();
        PrincipalCollection principals = currentUser.getPrincipals();
        String username = (String) principals.getPrimaryPrincipal();
        User loginUser = userDAOImpl.findByName(username);
        Groups newgroup = new Groups();
        newgroup.setGroupName(groups.getGroupName());
        UUID uuid = UUID.randomUUID();
        newgroup.setGroupID(uuid.toString());
        newgroup.setCreatorID(loginUser.getUserId());
        newgroup.setCreatedTime(new Date());
        newgroup.setDescribe(groups.getDescribe());
        groupDAOImpl.newGroup(newgroup);
        System.out.println("创建分组:"+newgroup.getGroupName());

        UserGroup userGroup = new UserGroup();
        UUID mid = UUID.randomUUID();
        userGroup.setMemID(mid.toString());
        userGroup.setUserName(loginUser.getUserId());
        userGroup.setGroupID(uuid.toString());
        userGroup.setJoinDate(new Date());
        userGroup.setRole("admin");
        groupUserDAOImpl.addMember(userGroup);
        System.out.println("创建分组用户:"+loginUser.getUserName());
        return "redirect:/";
    }

    @Autowired
    private UserDAOImpl userDAOImpl;

    @Autowired
    private GroupUserDAOImpl groupUserDAOImpl;

    @Autowired
    private GroupDAOImpl groupDAOImpl;

}
