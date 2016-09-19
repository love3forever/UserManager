package com.eclipsesv.controller;

import com.eclipsesv.dao.*;
import com.eclipsesv.model.Comments;
import com.eclipsesv.model.DiscussionGroup;
import com.eclipsesv.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by eclipse on 16/9/18.
 */
public class ControllerHelper {
    public User getCurrentUser() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser != null) {
            PrincipalCollection principals = currentUser.getPrincipals();
            String username = (String) principals.getPrimaryPrincipal();
            User loginUser = userDAOImpl.findByName(username);
            if (loginUser != null) {
                return loginUser;
            }
            return null;
        }
        return null;
    }

    public void newDiscussionGroup(DiscussionGroup discussionGroup,String userID,String groupID) {
        UUID uuid = UUID.randomUUID();
        DiscussionGroup newGroup = new DiscussionGroup();
        newGroup.setDiscussionID(uuid.toString());
        newGroup.setCreateTime(new Date());
        newGroup.setOwnerID(userID);
        newGroup.setDesc(discussionGroup.getDesc());
        newGroup.setGroupID(groupID);
        newGroup.setDiscussionName(discussionGroup.getDiscussionName());
        discussionDAOImpl.newDiscussion(newGroup);
    }

    public String delDiscussionGroup(String id) {
        String groupid = discussionDAOImpl.findByID(id).getGroupID();
        discussionDAOImpl.deleteDiscussion(id);
        return groupid;
    }

    public void newComment(Comments comments,String userID,String userName,String discussionID) {
        UUID uuid = UUID.randomUUID();
        Comments addComment = new Comments();
        addComment.setCommentid(uuid.toString());
        addComment.setDiscussionID(discussionID);
        addComment.setOwnerID(userID);
        addComment.setCommentTime(new Date());
        addComment.setContent(comments.getContent());
        addComment.setOwnerName(userName);
        commentDAOImpl.newComment(addComment);
    }


    public String delComment(String commentid){
        String groupid = commentDAOImpl.findByID(commentid).getDiscussionID();
        commentDAOImpl.deleteComment(commentid);
        return groupid;
    }

    public DiscussionGroup getDiscussionGroup(String id) {
         return discussionDAOImpl.findByID(id);
    }

    public List<Comments> getComments(String id) {
        return commentDAOImpl.findByGroupID(id);
    }

    public ControllerHelper(UserDAOImpl userDAO, GroupUserDAOImpl groupUserDAOImpl, GroupDAOImpl groupDAOImpl) {
        this.userDAOImpl = userDAO;
        this.groupDAOImpl = groupDAOImpl;
        this.groupUserDAOImpl = groupUserDAOImpl;
    }

    public ControllerHelper(UserDAOImpl userDAO, GroupUserDAOImpl groupUserDAOImpl, GroupDAOImpl groupDAOImpl,
                            DiscussionDAOImpl discussionDAOImpl,CommentDAOImpl commentDAOImpl) {
        this.userDAOImpl = userDAO;
        this.groupDAOImpl = groupDAOImpl;
        this.groupUserDAOImpl = groupUserDAOImpl;
        this.discussionDAOImpl = discussionDAOImpl;
        this.commentDAOImpl = commentDAOImpl;
    }




    private UserDAOImpl userDAOImpl;

    private GroupUserDAOImpl groupUserDAOImpl;

    private GroupDAOImpl groupDAOImpl;

    private DiscussionDAOImpl discussionDAOImpl;

    private CommentDAOImpl commentDAOImpl;
}
