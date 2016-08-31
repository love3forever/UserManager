package com.eclipsesv.dao;

import com.eclipsesv.model.User;

/**
 * Created by eclipse on 16/8/30.
 */
public interface UserDAO {
    void saveUser(User user);

    void deleteUser(User user);
}
