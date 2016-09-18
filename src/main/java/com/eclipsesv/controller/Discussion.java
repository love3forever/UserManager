package com.eclipsesv.controller;

import com.eclipsesv.dao.GroupDAOImpl;
import com.eclipsesv.dao.GroupUserDAOImpl;
import com.eclipsesv.dao.UserDAOImpl;
import com.eclipsesv.model.DiscussionGroup;
import com.eclipsesv.model.Groups;
import com.eclipsesv.model.User;
import com.eclipsesv.shiro.ShiroConfiguration;
import org.apache.shiro.SecurityUtils;
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
import java.util.List;
import java.util.UUID;

/**
 * Created by eclipse on 16/9/18.
 */
@Controller
@Transactional
public class Discussion {

    @RequestMapping(value = "/newDiscussion",method = RequestMethod.POST)
    public String newDiscussion(ModelMap model, DiscussionGroup user){
        UUID uuid = UUID.randomUUID();
        return "200";
    }


    @Autowired
    private UserDAOImpl userDAOImpl;

    @Autowired
    private GroupUserDAOImpl groupUserDAOImpl;

    @Autowired
    private GroupDAOImpl groupDAOImpl;

}
