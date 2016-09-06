package com.eclipsesv.dao;

import com.eclipsesv.model.Groups;
import com.eclipsesv.model.User;
import com.eclipsesv.model.UserGroup;

import java.util.List;

/**
 * Created by eclipse on 16/9/6.
 */
public interface GroupDAO {
    void newGroup(Groups groups);

    void delGroup(String groupid);

    Groups findByID(String groupid);

    Groups findByCreator(String userid);

    User getGroupCreator(String groupid);
}
