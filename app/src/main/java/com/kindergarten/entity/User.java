package com.kindergarten.entity;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2017/2/15 0015
 */
public class User extends BmobUser {
    private String childName;
    private Boolean childSex;
    private Integer childAge;
    private Boolean isTeacher;
    private Boolean sex;

    public Integer getChildAge() {
        return childAge;
    }

    public void setChildAge(Integer childAge) {
        this.childAge = childAge;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public Boolean getChildSex() {
        return childSex;
    }

    public void setChildSex(Boolean childSex) {
        this.childSex = childSex;
    }

    public Boolean getTeacher() {
        return isTeacher;
    }

    public void setTeacher(Boolean teacher) {
        isTeacher = teacher;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }
}
