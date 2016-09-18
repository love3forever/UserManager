package com.eclipsesv.dao;

import com.eclipsesv.model.DiscussionGroup;
import com.eclipsesv.model.User;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by eclipse on 16/9/18.
 */
public class DiscussionDAOImpl extends AbstractPublicDAO implements DiscussionDAO {
    @Override
    public void newDiscussion(DiscussionGroup discussionGroup) {
        getSession().persist(discussionGroup);
    }

    @Override
    public void deleteDiscussion(String discussionID) {
        // 先删除该讨论组相关的讨论内容
        Query groupqury = getSession().createQuery("DELETE FROM Comments WHERE DISCUSSION_ID=:id");
        groupqury.setParameter("id", discussionID);
        groupqury.executeUpdate();
        // 完成对讨论组的删除
        Query discussqury = getSession().createQuery("DELETE FROM DiscussionGroup WHERE DISCUSSION_ID=:id");
        discussqury.setParameter("id", discussionID);
        discussqury.executeUpdate();
    }

    @Override
    public List<User> listUsers(String discussionID) {
        return null;
    }
}
