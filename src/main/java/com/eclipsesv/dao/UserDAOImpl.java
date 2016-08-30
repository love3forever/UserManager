package com.eclipsesv.dao;

import com.eclipsesv.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created by eclipse on 16/8/30.
 */
@Repository("userDAO")
public class UserDAOImpl extends AbstractDAO implements UserDAO  {
    @Override
    public void saveUser(User user) {
        persist(user);
    }



    @Override
    public void deleteUser(User user) {

    }
}
