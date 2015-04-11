package com.zujia.android.zujia.model;

/**
 * Created by sergeantg_local on 2015/4/5.
 */
public class PersonalInfo extends Base{
    private int phone;
    private String username;
    private String avater;
    private String mail;

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
