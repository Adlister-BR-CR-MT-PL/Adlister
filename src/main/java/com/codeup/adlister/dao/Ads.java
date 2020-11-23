package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;

import java.util.List;

public interface Ads {
    /*=========================Search Ads===============================*/
    // Zero variables = All ads
    List<Ad> all();

    // Single variable search
    List<Ad> getAdsBySearchKeyword(String search);
    /*Todo: Category object*/
    List<Ad> getAdsBySearchCategory(Category category);
    /*List<Ad> getAdsBySearchSort(String sort);*/

    //List<Ad> getAdsBySearchKeywordAndCategory(String search, Category category);
    //List<Ad> getAdsBySearchKeywordAndSort(String search, Category category);
    //List<Ad> getAdsBySearchCategoryAndSort(String search, Category category);

    //findAd by ad ID
    Ad findByAdID(Long adID);

    /*=========================Create Ads===============================*/
    Long insert(Ad ad);
}
