package com.codeup.adlister.models;

public class AdPicture {
    private long id;
    private String url;
    private String alt_text;
    private long adID;
    private String createTime;


    public AdPicture(){}

    public AdPicture(String url, long adID) {
        this.url = url;
        this.adID = adID;
    }

    public AdPicture(long id, String url) {
        this.adID = id;
        this.url = url;
    }

    public AdPicture(String url, String alt_text, long adID) {
        this.adID = adID;
        this.url = url;
        this.alt_text = alt_text;
        this.adID = adID;

    }

    public AdPicture(long id, String ad_img_url, String alt_text, long ad_id) {
        this.adID = id;
        this.url = ad_img_url;
        this.alt_text = alt_text;
        this.adID = ad_id;
    }


    public long getAdID() {
        return adID;
    }

    public void setAdID(long id) {
        this.adID = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String adImgUrl) {
        this.url = adImgUrl;
    }

    public String getAlt_Text() {
        return alt_text;
    }

    public void setAlt_Text(String altText) {
        this.alt_text = altText;
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