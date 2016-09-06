package com.eclipsesv.dao;

import com.eclipsesv.model.User;
import com.eclipsesv.model.UserGroup;

import java.util.List;

/**
 * Created by eclipse on 16/9/6.
 */
public interface GroupDAO {
    void saveGroup(UserGroup group);

    void deleteGroup(String groupID);

    void deleteMember(String groupID,String userID);

    void addMember(String groupID,String userID);

    List<User> listMember(String groupID);

    List<UserGroup> listGroup(String userID);
}
