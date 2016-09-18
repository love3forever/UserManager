package com.eclipsesv.dao;

import com.eclipsesv.model.DiscussionGroup;
import com.eclipsesv.model.User;

import java.util.List;

/**
 * Created by eclipse on 16/9/18.
 */
public interface DiscussionDAO {
    void newDiscussion(DiscussionGroup discussionGroup);

    void deleteDiscussion(String discussionID);

    List<User> listUsers(String discussionID);
}
