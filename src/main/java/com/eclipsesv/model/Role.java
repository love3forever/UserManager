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
    private int id;

    @Column(name = "ROLE_NAME")
    private String name;

    @Column(name = "DESCIBRE")
    private String describe;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Role(int id, String name, String desc, String rightID) {
        this.id = id;
        this.name = name;
        this.describe = desc;
    }

    public Role() {

    }
}
