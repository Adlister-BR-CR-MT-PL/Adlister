package com.codeup.adlister.models;

import com.codeup.adlister.util.Password;

public class User {
    private long id;
    private boolean listerType;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String aboutMe;
    private String phoneNumber;
    private boolean viewPreference;
    private boolean isAdmin;
    private String createTime;

    public User() {
    }

    public User(String username, String email, String password, String first_name, String last_name) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = first_name;
        this.lastName = last_name;
        this.isAdmin = false;
    }

    public User(long id, boolean listerType, String username, String email, String password, String firstName, String lastName) {
        this.id = id;
        this.listerType = listerType;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(long id, boolean listerType, String username, String email, String password, String firstName, String lastName, String aboutMe, String phoneNumber, boolean viewPreference, boolean isAdmin, String createTime) {
        this.id = id;
        this.listerType = listerType;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.aboutMe = aboutMe;
        this.phoneNumber = phoneNumber;
        this.viewPreference = viewPreference;
        this.isAdmin = isAdmin;
        this.createTime = createTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isListerType() {
        return listerType;
    }

    public void setListerType(boolean listerType) {
        this.listerType = listerType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Password.hash(password);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isViewPreference() {
        return viewPreference;
    }

    public void setViewPreference(boolean viewPreference) {
        this.viewPreference = viewPreference;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
