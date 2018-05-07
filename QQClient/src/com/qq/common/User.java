package com.qq.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private String nickname;
    private String number;
    private String password;
    private List<User> friends;

    public User() {
    }

    public User(String nickname, String number, String password) {
        this.nickname = nickname;
        this.number = number;
        this.password = password;
        this.friends = new ArrayList<>();

    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

}