package com.codeup.adlister.dao;

import com.codeup.adlister.models.AdPicture;

public interface AdPictures {

    AdPicture findByURL(String URL);
    AdPicture findByAdID(long adID);
    AdPicture findByPicID(long picID);
    Long insertPic(AdPicture id);
    void updatePicURL(String newPicURL, long userID);
}
