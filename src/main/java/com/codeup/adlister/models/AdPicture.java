package com.codeup.adlister.models;

public class AdPicture {
    private long id;
    private String url;
    private String alt_text;
    private long adID;
    private String createTime;


    public AdPicture() {}

    public AdPicture(String url, long adID) {
        this.url = url;
        this.adID = adID;
    }

    public AdPicture(long id, String url) {
        this.id = id;
        this.url = url;
    }

    public AdPicture(String url, String alt_text, long adID) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt_text() {
        return alt_text;
    }

    public void setAlt_text(String alt_text) {
        this.alt_text = alt_text;
    }

    public long getAdID() {
        return adID;
    }

    public void setAdID(long adID) {
        this.adID = adID;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
