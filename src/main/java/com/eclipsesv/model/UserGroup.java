package com.eclipsesv.model;

import org.springframework.context.annotation.ComponentScan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by eclipse on 16/9/5.
 */
@Entity
@Table(name = "USER_GROUP")
public class UserGroup {
    @Id
    @Column(name = "MEM_ID", nullable = false)
    private String memID;

    @Column(name = "GROUP_ID", nullable = false)
    private String groupID;

    @Column(name = "USER_ID", nullable = false)
    private String userName;

    @Column(name = "JOIN_DATE", nullable = false)
    private Date joinDate;

    @Column(name = "Role", nullable = false)
    private int role;


    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }


    public String getUserID() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }


    public UserGroup() {

    }

    public UserGroup(String mid, String gid, String gname, String uid, Date date, int roleid, String creatorID) {
        this.memID = mid;
        this.groupID = gid;
        this.userName = uid;
        this.joinDate = date;
        this.role = roleid;
    }

    public String getMemID() {
        return memID;
    }

    public void setMemID(String memID) {
        this.memID = memID;
    }
}
