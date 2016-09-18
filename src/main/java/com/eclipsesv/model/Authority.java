package com.eclipsesv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by eclipse on 16/9/6.
 */
@Entity
@Table(name = "Authority_TB")
public class Authority {
    @Id
    @Column(name = "Authority_ID")
    private int authority_ID;

    @Column(name = "Authority_Name")
    private String authority_Name;

    @Column(name = "Describe")
    private String desc;




    public Authority() {

    }

    public int getAuthority_ID() {
        return authority_ID;
    }

    public void setAuthority_ID(int authority_ID) {
        this.authority_ID = authority_ID;
    }

    public String getAuthority_Name() {
        return authority_Name;
    }

    public void setAuthority_Name(String authority_Name) {
        this.authority_Name = authority_Name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
