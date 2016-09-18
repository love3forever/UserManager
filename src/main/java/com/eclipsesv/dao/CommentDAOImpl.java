package com.eclipsesv.dao;

import com.eclipsesv.model.Comments;
import org.hibernate.Query;

/**
 * Created by eclipse on 16/9/18.
 */
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
}
