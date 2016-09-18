package com.eclipsesv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by eclipse on 16/9/6.
 */
@Entity
@Table(name = "RIGHT_TB")
public class Authority {
    @Id
    @Column(name = "RIGHT_ID")
    private String RightID;

    @Column(name = "READ")
    private int read;

    @Column(name = "WRITE")
    private int write;

    @Column(name = "EXCUTE")
    private int excute;


    public String getRightID() {
        return RightID;
    }

    public void setRightID(String rightID) {
        RightID = rightID;
    }

    public int isRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public int isWrite() {
        return write;
    }

    public void setWrite(int write) {
        this.write = write;
    }

    public int isExcute() {
        return excute;
    }

    public void setExcute(int excute) {
        this.excute = excute;
    }


    public Authority(String id,int read,int write,int excute) {
        this.RightID = id;
        this.read = read;
        this.write = write;
        this.excute = excute;
    }

    public Authority() {

    }
}
