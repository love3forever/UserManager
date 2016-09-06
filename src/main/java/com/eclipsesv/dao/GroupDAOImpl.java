package com.eclipsesv.dao;

import com.eclipsesv.model.Groups;
import com.eclipsesv.dao.AbstractPublicDAO;
import com.eclipsesv.model.User;
import org.hibernate.Query;

/**
 * Created by eclipse on 16/9/6.
 */
public class GroupDAOImpl extends AbstractPublicDAO implements GroupDAO {
    @Override
    public void newGroup(Groups groups) {
        getSession().persist(groups);
    }

    @Override
    public void delGroup(String groupid) {
        Query groupqury = getSession().createQuery("DELETE FROM UserGroup WHERE GROUP_ID=:id");
        groupqury.setParameter("id", groupid);
        groupqury.executeUpdate();
        Query query = getSession().createQuery("DELETE FROM Groups WHERE GROUP_ID=:id");
        query.setParameter("id", groupid);
        query.executeUpdate();
    }

    @Override
    public Groups findByID(String groupid) {
        Query query= getSession().
                createQuery("from Groups where GROUP_ID=:id");
        query.setParameter("id", groupid);
        Groups result = (Groups) query.uniqueResult();
        return result;
    }

    @Override
    public Groups findByCreator(String userid) {
        Query query= getSession().
                createQuery("from Groups where CREATOR=:id");
        query.setParameter("id", userid);
        Groups result = (Groups) query.uniqueResult();
        return result;
    }

    @Override
    public User getGroupCreator(String groupid) {
        Query query = getSession().
                createQuery("from Groups where GROUP_ID=:id");
        query.setParameter("id", groupid);
        Groups result = (Groups) query.uniqueResult();
        String uid = result.getCreatorID();

        Query uquery = getSession().createQuery("FROM User WHERE USER_ID=:uid");
        uquery.setParameter("uid", uid);
        User user = (User) query.uniqueResult();
        return user;
    }
}
