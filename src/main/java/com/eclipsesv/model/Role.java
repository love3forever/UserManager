package com.eclipsesv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by eclipse on 16/9/6.
 */
@Entity
@Table(name = "ROLE_TB")
public class Role {
    @Id
    @Column(name = "ROLE_ID")
    private String id;

    @Column(name = "ROLE_NAME")
    private String name;

    @Column(name = "DESCIBRE")
    private String describe;

    @Column(name = "RIGHT_ID")
    private String rightID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getRightID() {
        return rightID;
    }

    public void setRightID(String rightID) {
        this.rightID = rightID;
    }

    public Role(String id, String name, String desc, String rightID) {
        this.id = id;
        this.name = name;
        this.describe = desc;
        this.rightID = rightID;
    }

    public Role() {

    }
}
