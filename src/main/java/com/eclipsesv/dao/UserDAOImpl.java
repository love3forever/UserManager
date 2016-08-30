package com.eclipsesv.dao;

import com.eclipsesv.model.User;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
