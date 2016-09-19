package com.eclipsesv.dao;

import com.eclipsesv.model.Comments;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by eclipse on 16/9/18.
 */
@Repository("commentDAO")
public class CommentDAOImpl extends AbstractPublicDAO implements CommentDAO {

    @Override
    public void newComment(Comments comments) {
        getSession().persist(comments);
    }

    @Override
    public void deleteComment(String commentID) {
        Query groupqury = getSession().createQuery("DELETE FROM Comments WHERE commentid=:id");
        groupqury.setParameter("id", commentID);
        groupqury.executeUpdate();
    }

    @Override
    public List<Comments> findByGroupID(String id) {
        Query commentsQuery = getSession().createQuery("from Comments where discussionID=:id");
        commentsQuery.setParameter("id", id);
        List<Comments> comments = (List<Comments>)commentsQuery.list();
        return comments;
    }

    @Override
    public Comments findByID(String id) {
        Query commentQuery = getSession().createQuery("from Comments where commentid=:id");
        commentQuery.setParameter("id", id);
        Comments comments = (Comments) commentQuery.uniqueResult();
        return comments;
    }
}
