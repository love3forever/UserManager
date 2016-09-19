package com.eclipsesv.dao;

import com.eclipsesv.controller.Discussion;
import com.eclipsesv.model.DiscussionGroup;
import com.eclipsesv.model.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by eclipse on 16/9/18.
 */
@Repository("discussionDAO")
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

    @Override
    public List<DiscussionGroup> listDiscussionByGroupID(String groupID) {
        Query listQuery = getSession().createQuery("from DiscussionGroup where groupID=:id");
        listQuery.setParameter("id", groupID);
        List<DiscussionGroup> result = (List<DiscussionGroup>)listQuery.list();
        return result;
    }

    @Override
    public DiscussionGroup findByID(String id) {
        Query groupQuery = getSession().createQuery("from DiscussionGroup where discussionID=:id");
        groupQuery.setParameter("id", id);
        DiscussionGroup result = (DiscussionGroup) groupQuery.uniqueResult();
        return result;
    }
}
