package com.eclipsesv.dao;

import com.eclipsesv.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Created by eclipse on 16/8/30.
 */
public class UserDAOImpl implements UserDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void save(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public User findByuserName(String usernmae) {
        return null;
    }

    @Override
    public User findByuserId(int userid) {
        return null;
    }
}
