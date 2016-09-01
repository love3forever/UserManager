package com.eclipsesv.dao;

import com.eclipsesv.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by eclipse on 16/8/30.
 */
public abstract class AbstractDAO {
    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public void persist(Object Entity){
        getSession().persist(Entity);
    }

    public void delete(Object Entity){
        getSession().delete(Entity);
    }

    public User getRolesName(String username){
        return null;
    }
}
