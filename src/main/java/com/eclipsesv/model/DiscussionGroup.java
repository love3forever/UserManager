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
@Table(name = "DISCUSSION")
public class DiscussionGroup {
    @Id
    @Column(name = "DISCUSSION_ID", nullable = false)
    private String discussionID;

    @Column(name = "GROUP_ID", nullable = false)
    private String groupID;

    @Column(name = "OWNER", nullable = false)
    private String ownerID;

    @Column(name = "DISCUSSION_NAME", nullable = false)
    private String discussionName;

    @Column(name = "CREATED_TIME", nullable = false)
    private Date createTime;

    @Column(name = "DESCIBE")
    private String desc;

    public String getDiscussionID() {
        return discussionID;
    }

    public void setDiscussionID(String discussionID) {
        this.discussionID = discussionID;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public String getDiscussionName() {
        return discussionName;
    }

    public void setDiscussionName(String discussionName) {
        this.discussionName = discussionName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public DiscussionGroup() {

    }
}
