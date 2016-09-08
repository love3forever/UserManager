package com.eclipsesv.dao;

import com.eclipsesv.model.User;

import java.util.List;

/**
 * Created by eclipse on 16/8/30.
 */
public interface UserDAO {
    void saveUser(User user);

    void deleteUser(User user);

    User findByName(String username);

    List<User> listUsers();
}
