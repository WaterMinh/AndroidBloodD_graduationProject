package com.example.projectblooddonation.entity;

import java.sql.Date;

public class Users {
    private int userId;
    private String fullName;
    private String password;
    private String avatar;
    private Date birthday;
    private Boolean gender;
    private String telephone;
    private String address;
    private String email;
    private String identityCard;
    private String job;
    private String bloodType;
    private int permission;
    private Date createDate;

    public Users() {
    }

    public Users(String email, String password) {
        this.password = password;
        this.email = email;
    }

    public Users(int userId, String fullName, String password, String avatar, Date birthday, Boolean gender, String telephone, String address, String email, String identityCard, String job, String bloodType, int permission, Date createDate) {
        this.userId = userId;
        this.fullName = fullName;
        this.password = password;
        this.avatar = avatar;
        this.birthday = birthday;
        this.gender = gender;
        this.telephone = telephone;
        this.address = address;
        this.email = email;
        this.identityCard = identityCard;
        this.job = job;
        this.bloodType = bloodType;
        this.permission = permission;
        this.createDate = createDate;
    }

    public Users(String fullName, String password, Date birthday, Boolean gender, String telephone, String address, String email, String identityCard, String job, Date createDate) {
        this.fullName = fullName;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
        this.telephone = telephone;
        this.address = address;
        this.email = email;
        this.identityCard = identityCard;
        this.job = job;
        this.createDate = createDate;
    }

    public Users(String fullName, String password, Date birthday, Boolean gender, String telephone, String address, String email, String identityCard, String job) {
        this.fullName = fullName;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
        this.telephone = telephone;
        this.address = address;
        this.email = email;
        this.identityCard = identityCard;
        this.job = job;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
