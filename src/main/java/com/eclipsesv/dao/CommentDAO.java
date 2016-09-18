package com.eclipsesv.dao;

import com.eclipsesv.model.Comments;

import java.util.List;

/**
 * Created by eclipse on 16/9/18.
 */
public interface CommentDAO {
    void newComment(Comments comments);

    void deleteComment(String commentID);

    List<Comments> findByID(String id);
}
