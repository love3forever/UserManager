package com.eclipsesv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by eclipse on 16/9/18.
 */
@Entity
@Table(name = "COMMENT_TB")
public class Comments {
    @Id
    @Column(name = "COMMENT_ID", nullable = false)
    private String commentid;


    @Column(name = "OWNER", nullable = false)
    private String ownerID;

    @Column(name = "COMMENT_TIME", nullable = false)
    private Date commentTime;

    @Column(name = "DISCUSSION_ID", nullable = false)
    private String discussionID;

    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Column(name = "OWNER_NAME", nullable = false)
    private String ownerName;


    public String getCommentid() {
        return commentid;
    }

    public void setCommentid(String commentid) {
        this.commentid = commentid;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public String getDiscussionID() {
        return discussionID;
    }

    public void setDiscussionID(String discussionID) {
        this.discussionID = discussionID;
    }

    public Comments() {

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
