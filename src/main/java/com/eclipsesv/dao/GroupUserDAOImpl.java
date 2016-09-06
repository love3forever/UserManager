package com.eclipsesv.dao;

import com.eclipsesv.model.User;
import com.eclipsesv.model.UserGroup;
import org.hibernate.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eclipse on 16/9/6.
 */
public class GroupUserDAOImpl extends AbstractPublicDAO implements GroupUserDAO {
    @Override
    public void addMember(UserGroup userGroup) {
        getSession().persist(userGroup);
    }

    @Override
    public void removeMember(String userid, String groupid) {
        Query query = getSession().createQuery("DELETE FROM UserGroup WHERE GROUP_ID=:id and USER_ID=:uid");
        query.setParameter("id", groupid);
        query.setParameter("uid", groupid);
        query.executeUpdate();
    }

    @Override
    public void modifyMember(String userid, String groupid, String roleid) {
        String hql = "UPDATE UserGroup set ROLE = :roleid "  +
                "WHERE GROUP_ID=:id and USER_ID=:uid";
        Query query = getSession().createQuery(hql);
        query.setParameter("roleid", roleid);
        query.setParameter("id", groupid);
        query.setParameter("uid", userid);
        int result = query.executeUpdate();
    }

    @Override
    public List<User> listMember(String groupid) {
        Query query = getSession().createQuery("FROM UserGroup WHERE GROUP_ID =: id");
        List userid = query.list();
        List<User> users = new ArrayList<>();
        for (Object uid:
             userid) {
            Query uquery = getSession().createQuery("FROM User WHERE USER_ID=:uid");
            uquery.setParameter("uid", (String) uid);
            User result = (User) query.uniqueResult();
            users.add(result);
        }
        return users;
    }
}
