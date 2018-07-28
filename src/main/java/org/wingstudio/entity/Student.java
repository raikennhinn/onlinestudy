package org.wingstudio.entity;

import java.util.Date;

public class Student {
    private Integer id;

    private Integer stuNum;

    private String name;

    private Integer status;

    private Date createTime;

    public Student(Integer id, Integer stuNum, String name, Integer status, Date createTime) {
        this.id = id;
        this.stuNum = stuNum;
        this.name = name;
        this.status = status;
        this.createTime = createTime;
    }

    public Student() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStuNum() {
        return stuNum;
    }

    public void setStuNum(Integer stuNum) {
        this.stuNum = stuNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}