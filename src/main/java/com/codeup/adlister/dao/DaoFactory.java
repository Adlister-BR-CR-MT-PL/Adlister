package com.codeup.adlister.dao;

import com.codeup.adlister.models.BusinessAdPicture;

public class DaoFactory {
    private static AdCategories adCategoriesDao;
    private static Ads adsDao;
    private static BusinessAdCategories businessAdCategoriesDao;
    private static BusinessAdPictures businessAdPicturesDao;
    private static BusinessAds businessAdsDao;
    private static BusinessCategories businessCategoriesDao;
    private static Businesses businessesDao;
    private static BusinessPictures businessPicturesDao;
    private static Categories categoriesDao;
    private static UserPictures getUserPicDao;
    private static Users usersDao;
    private static AdPictures getGetAdsPicDao;

    private static Config config = new Config();

    public static Ads getAdsDao() {
        if (adsDao == null) {
            adsDao = new MySQLAdsDao(config);
        }
        return adsDao;
    }

    public static Users getUsersDao() {
        if (usersDao == null) {
            usersDao = new MySQLUsersDao(config);
        }
        return usersDao;
    }

    public static Businesses getBusinessesDao() {
        if (businessesDao == null) {
            businessesDao = new MySQLBusinessesDao(config);
        }
        return businessesDao;
    }

    public static BusinessPictures getBusinessPicturesDao() {
        if (businessPicturesDao == null) {
            businessPicturesDao = new MySQLBusinessPicDao(config);
        }
        return businessPicturesDao;
    }


    public static Categories getCategoriesDao() {
        if (categoriesDao == null) {
            categoriesDao = new MySQLCategoriesDao(config);
        }
        return categoriesDao;
    }


    public static UserPictures getGetUserPicDao() {
        if (getUserPicDao == null) {
            getUserPicDao = new MySQLusrPicDao(config);
        }
        return getUserPicDao;

    }

    public static AdCategories getAdCategoriesDao() {
        if (adCategoriesDao == null) {
            adCategoriesDao = new MySQLAdCategoriesDao(config);
        }
        return adCategoriesDao;
    }

    public static BusinessAdCategories getBusinessAdCategoriesDao() {
        if (businessAdCategoriesDao == null) {
            businessAdCategoriesDao = new MySQLBusinessAdCategoriesDao(config);
        }
        return businessAdCategoriesDao;
    }

    public static BusinessAds getBusinessAdsDao(){
        if (businessAdsDao == null) {
            businessAdsDao = new MySQLBusinessAdsDao(config);
        }
        return businessAdsDao;
    }

    public static BusinessCategories getBusinessCategoriesDao() {
        if (businessCategoriesDao == null) {
            businessCategoriesDao = new MySQLBusinessCategoriesDao(config);
        }
        return businessCategoriesDao;
    }

    public static BusinessAdPictures getBusinessAdPicturesDao(){
        if (businessAdPicturesDao == null) {
            businessAdPicturesDao = new MySQLBusinessAdPicturesDao(config);
        }
        return businessAdPicturesDao;
    }

    public static AdPictures getGetAdsPicDao() {
        if (getGetAdsPicDao == null) {
            getGetAdsPicDao = new MySQLadPicDao(config);
        }
        return getGetAdsPicDao;
    }
}
