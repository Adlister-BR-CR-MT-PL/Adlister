package com.codeup.adlister.dao;

import com.codeup.adlister.models.BusinessAd;

import java.util.List;

public interface BusinessAds {
    List<BusinessAd> all();
    List<BusinessAd> allBizAdsByBizId(long businessId);
    Long insert(BusinessAd businessAd);
    BusinessAd findByBusinessAdID(Long businessId);
}
