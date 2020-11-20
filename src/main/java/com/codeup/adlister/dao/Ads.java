package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.BusinessAd;

import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();
    // get a list of all the ads
    /*List<Ad> search(String);*/
    List<Ad> allAdsByUserId(long userId);
    // insert a new ad and return the new ad's id
    Long insert(Ad ad);
    //findAd by ad ID
    Ad findByAdID(Long adID);
}
