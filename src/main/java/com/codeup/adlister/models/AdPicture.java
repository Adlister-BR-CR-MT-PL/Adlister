package com.codeup.adlister.models;

public class AdPicture {
    private long id;
    private long adID;
    private String url;

    public AdPicture(){}

    public AdPicture(long adID, String url) {
        this.adID = adID;
        this.url = url;
    }

    public AdPicture(long id, long userID, String url) {
        this.id = id;
        this.adID = userID;
        this.url = url;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAdImgUrl() {
        return adImgUrl;
    }

    public void setAdImgUrl(String adImgUrl) {
        this.adImgUrl = adImgUrl;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public long getAdId() {
        return adID;
    }

    public void setAdId(long adId) {
        this.adID = adId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
