package com.eclipsesv.controller;

import com.eclipsesv.dao.*;
import com.eclipsesv.model.Groups;
import com.eclipsesv.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by eclipse on 16/9/18.
 */
public class ControllerHelper {
    public User getCurrentUser() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser != null) {
            PrincipalCollection principals = currentUser.getPrincipals();
            String username = (String) principals.getPrimaryPrincipal();
            User loginUser = userDAOImpl.findByName(username);
            if (loginUser != null) {
                return loginUser;
            }
            return null;
        }
        return null;
    }

    public ControllerHelper(UserDAOImpl userDAO, GroupUserDAOImpl groupUserDAOImpl, GroupDAOImpl groupDAOImpl) {
        this.userDAOImpl = userDAO;
        this.groupDAOImpl = groupDAOImpl;
        this.groupUserDAOImpl = groupUserDAOImpl;

    }


    private UserDAOImpl userDAOImpl;

    private GroupUserDAOImpl groupUserDAOImpl;

    private GroupDAOImpl groupDAOImpl;
}
