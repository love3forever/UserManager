package com.eclipsesv.dao;

import com.eclipsesv.model.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public User findByName(String username) {
        //这里要注意，from tablename ＊＊＊ 这里边的tablename对应的是model里边的类名
        Query query= getSession().
                createQuery("from User where USER_NAME=:name");
        query.setParameter("name", username);
        User result = (User) query.uniqueResult();
        return result;
    }

    @Override
    public List<User> listUsers() {
        List<User> result = new ArrayList<>();
        List users = getSession().createQuery("from User").list();
        for (Object u:
             users) {
            result.add((User) u);
        }
        return result;
    }
}
