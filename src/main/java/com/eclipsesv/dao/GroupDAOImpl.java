package com.eclipsesv.dao;

import com.eclipsesv.model.User;
import com.eclipsesv.model.UserGroup;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by eclipse on 16/9/6.
 */
@Repository("groupuserDAO")
public class GroupDAOImpl extends AbstractGroupDAO implements GroupDAO{
    @Override
    public void saveGroup(UserGroup group) {
        getSession().persist(group);
    }

    @Override
    public void deleteGroup(String groupID) {
        Query query= getSession().
                createQuery("from UserGroup where GROUP_ID=:id");
        query.setParameter("id", groupID);
        UserGroup result = (UserGroup) query.uniqueResult();
        getSession().delete(result);
    }

    @Override
    public void deleteMember(String groupID, String userID) {
        Query query = getSession().
                createQuery("DELETE FROM UserGroup where Group_ID=:id and USER_ID=:userid");
        query.setParameter("id", groupID);
        query.setParameter("userid", userID);
        query.executeUpdate();
    }

    @Override
    public void addMember(String groupID, String userID) {

    }

    @Override
    public List<User> listMember(String groupID) {
        return null;
    }

    @Override
    public List<UserGroup> listGroup(String userID) {
        return null;
    }
}
