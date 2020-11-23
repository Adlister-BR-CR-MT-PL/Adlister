package com.codeup.adlister.dao;

import com.codeup.adlister.models.AdPicture;

public interface AdPictures {
    AdPicture findByURL(String URL);
    AdPicture findByAdIDinAds(long adID);
    AdPicture findByAdPicID(long adID);
    Long insert(AdPicture id);
    void updatePicURL(String newPicURL, long userID);
    AdPicture findByPicID(long picID);
}
