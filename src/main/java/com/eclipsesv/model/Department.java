package com.eclipsesv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by eclipse on 16/9/6.
 */
@Entity
@Table(name = "DEPARTMENT")
public class Department {
    @Id
    @Column(name = "DEPARTMENT_ID",nullable = false)
    private String departmentID;

    @Column(name = "DEPARTMENT_NAME",nullable = false)
    private String departmentName;

    @Column(name = "ParentID")
    private String parentID;

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public Department(String id, String name, String parentID) {
        this.departmentID = id;
        this.departmentName = name;
        this.parentID = parentID;
    }
}
