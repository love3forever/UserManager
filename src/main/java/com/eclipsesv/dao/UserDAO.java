package com.eclipsesv.dao;

import com.eclipsesv.model.User;

/**
 * Created by eclipse on 16/8/30.
 */
public interface UserDAO {
    public void save(User user);
    public void delete(User user);

    public User findByuserName(String usernmae);

    public User findByuserId(int userid);

}
