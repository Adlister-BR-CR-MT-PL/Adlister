package com.codeup.adlister.dao;

import com.codeup.adlister.models.AdPicture;

public interface AdPictures {

    AdPicture findByURL(String URL);
    AdPicture findByadIDAds(long adID);
    AdPicture findByAdID(long adID);
    Long insert(AdPicture id);
    void updatePicURL(String newPicURL, long userID);

}
