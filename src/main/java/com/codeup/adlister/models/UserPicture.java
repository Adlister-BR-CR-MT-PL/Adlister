package com.codeup.adlister.models;

import java.util.Date;

public class UserPicture {
    private long id;
    private String imgURL;
    private String altText;
    private long userID;
    private String createTime;

    public UserPicture() {}

    public UserPicture(String imgURL, long userID) {
        this.imgURL = imgURL;
        this.userID = userID;
    }

    public UserPicture(long id, String imgURL) {
        this.id = id;
        this.imgURL = imgURL;
    }

    public UserPicture(long id, String imgURL, String altText, long userID) {
        this.id = id;
        this.imgURL = imgURL;
        this.altText = altText;
        this.userID = userID;
    }

    public UserPicture(long id, String imgURL, String altText, long userID, String createTime) {
        this.id = id;
        this.imgURL = imgURL;
        this.altText = altText;
        this.userID = userID;
        this.createTime = createTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
