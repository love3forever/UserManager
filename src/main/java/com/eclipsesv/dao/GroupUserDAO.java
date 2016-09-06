package com.eclipsesv.dao;

import com.eclipsesv.model.User;
import com.eclipsesv.model.UserGroup;

import java.util.List;

/**
 * Created by eclipse on 16/9/6.
 */
public interface GroupUserDAO {
    void addMember(UserGroup userGroup);

    void removeMember(String userid, String groupid);

    void modifyMember(String userid, String groupid, String roleid);

    List<User> listMember(String groupid);
}
