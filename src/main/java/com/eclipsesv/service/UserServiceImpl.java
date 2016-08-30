package com.eclipsesv.service;

import com.eclipsesv.dao.UserDAO;
import com.eclipsesv.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by eclipse on 16/8/30.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO dao;

    @Override
    public void addUser(User user) {
        dao.saveUser(user);
    }

    @Override
    public void delUser(User user) {
        dao.deleteUser(user);
    }
}
