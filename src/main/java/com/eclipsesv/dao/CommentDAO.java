package com.eclipsesv.dao;

import com.eclipsesv.model.Comments;

/**
 * Created by eclipse on 16/9/18.
 */
public interface CommentDAO {
    void newComment(Comments comments);

    void deleteComment(String commentID);
}
