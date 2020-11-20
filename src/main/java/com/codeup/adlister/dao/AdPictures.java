package com.codeup.adlister.dao;

import com.codeup.adlister.models.AdPicture;

public interface AdPictures {

    AdPicture findByURL(String URL);
<<<<<<< HEAD
    AdPicture findByAdID(long adID);
    AdPicture findByPicID(long picID);
    Long insertPic(AdPicture id);
=======
    AdPicture findByAdIDinAds(long adID);
    AdPicture findByAdPicID(long adID);
    Long insert(AdPicture id);
>>>>>>> main
    void updatePicURL(String newPicURL, long userID);
}
