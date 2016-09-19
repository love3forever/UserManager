package com.eclipsesv.dao;

import com.eclipsesv.model.Comments;

import java.util.List;

/**
 * Created by eclipse on 16/9/18.
 */
public interface CommentDAO {
    void newComment(Comments comments);

    void deleteComment(String commentID);

    //通过讨论组的id 获取它全部的评论内容
    List<Comments> findByGroupID(String id);

    //通过评论id获取到评论
    Comments findByID(String id);
}
