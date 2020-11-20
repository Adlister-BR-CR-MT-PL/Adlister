package com.codeup.adlister.models;

public class AdPicture {
    private long id;
    private String url;
<<<<<<< HEAD
    private String altText;
    private String createTime;
=======
    private String alt_text;
    private long adID;

>>>>>>> main

    public AdPicture(){}

    public AdPicture( String url, String alt_text, long adID) {
        this.url = url;
        this.alt_text = alt_text;
        this.adID = adID;
    }

    public AdPicture(long id, String url, String alt_text, long adID) {
        this.id = id;
        this.url = url;
        this.alt_text = alt_text;
        this.adID = adID;

    }

    public AdPicture(long adID, String url, String altText) {
        this.adID = adID;
        this.url = url;
        this.altText = altText;
    }

    public AdPicture(long adID, String url, String altText, String createTime) {
        this.adID = adID;
        this.url = url;
        this.altText = altText;
        this.createTime = createTime;
    }

    public AdPicture(long id, long adID, String url, String altText, String createTime) {
        this.id = id;
        this.adID = adID;
        this.url = url;
        this.altText = altText;
        this.createTime = createTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAdImgUrl() {
        return url;
    }

    public void setAdImgUrl(String adImgUrl) {
        this.url = adImgUrl;
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

    public String getAltText(){
        return this.alt_text;
    }
}
