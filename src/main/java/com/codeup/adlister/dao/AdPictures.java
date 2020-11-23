package com.codeup.adlister.dao;

import com.codeup.adlister.models.AdPicture;

public interface AdPictures {

    AdPicture findByURL(String URL);
    AdPicture findAdPicByAdIDInAds(long adID);
    AdPicture findByAdPicID(long adID);
    Long insertPic(AdPicture id);
    void updatePicURL(String newPicURL, long userID);
    void deleteAdPicture(long adId);
}
