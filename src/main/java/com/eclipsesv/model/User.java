package com.eclipsesv.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by eclipse on 16/8/30.
 */
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "USER_NAME",nullable = false)
    private String userName;

    @Column(name = "PASSWORD",nullable = false)
    private String password;

    @Column(name = "EMAIL",nullable = false)
    private String email;

    @Column(name = "CREATEDTIME",nullable = false)
    private Date createdDate;

    @Column(name = "SEX")
    private int sex;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "TEL")
    private int tel;

    @Column(name = "DEPARTMENT")
    private int department;

    public User(){

    }

    public User(String userid, String username, String password, String email, Date createddate, int sex,
                String title, int tel, int department) {
        this.userId = userid;
        this.userName = username;
        this.password = password;
        this.email = email;
        this.createdDate = createddate;
        this.sex = sex;
        this.title = title;
        this.tel = tel;
        this.department = department;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }
}
