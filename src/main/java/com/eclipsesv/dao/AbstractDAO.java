package com.eclipsesv.dao;

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
}
