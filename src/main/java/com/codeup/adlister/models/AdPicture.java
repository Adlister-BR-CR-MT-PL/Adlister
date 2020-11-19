package com.codeup.adlister.models;

public class AdPicture {
    private long id;
    private String adImgUrl;
    private String alt_text;
    private long adID;
    private String create_time;


    public AdPicture(){}

    public AdPicture( String url, String alt_text,long adID,String create_time) {
        this.adImgUrl = url;
        this.alt_text = alt_text;
        this.adID = adID;
        this.create_time = create_time;
    }
    public AdPicture( long id, String url, String alt_text,long adID,String create_time) {
        this.id= id ;
        this.adImgUrl = url;
        this.alt_text = alt_text;
        this.adID = adID;
        this.create_time = create_time;
    }

    public AdPicture(long id, long userID, String url) {
        this.id = id;
        this.adID = userID;
        this.adImgUrl = url;
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
        return alt_text;
    }

    public void setAltText(String altText) {
        this.alt_text = altText;
    }

    public long getAdId() {
        return adID;
    }

    public void setAdId(long adId) {
        this.adID = adId;
    }

    public String getCreateTime() {
        return create_time;
    }

    public void setCreateTime(String createTime) {
        this.create_time = createTime;
    }
}
