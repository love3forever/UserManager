package com.eclipsesv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by eclipse on 16/9/18.
 */
@Entity
@Table(name = "AuthorityRoleLink")
public class AuthorityRoleLink {
    @Id
    @Column(name = "AR_ID", nullable = false)
    private int ar_ID;

    @Column(name = "ROLE_ID")
    private int role_ID;

    @Column(name = "Authority_ID")
    private int authority_ID;

    public AuthorityRoleLink() {

    }

    public int getAr_ID() {
        return ar_ID;
    }

    public void setAr_ID(int ar_ID) {
        this.ar_ID = ar_ID;
    }

    public int getRole_ID() {
        return role_ID;
    }

    public void setRole_ID(int role_ID) {
        this.role_ID = role_ID;
    }

    public int getAuthority_ID() {
        return authority_ID;
    }

    public void setAuthority_ID(int authority_ID) {
        this.authority_ID = authority_ID;
    }
}
