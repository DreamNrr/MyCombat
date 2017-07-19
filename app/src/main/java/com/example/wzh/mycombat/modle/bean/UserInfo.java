package com.example.wzh.mycombat.modle.bean;

/**
 * Created by WZH on 2017/7/1.
 */

public class UserInfo {
    private String name;    // 用户名称
    private String password;    // 昵称

    public UserInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserInfo(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
