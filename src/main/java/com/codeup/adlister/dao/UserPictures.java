package com.codeup.adlister.dao;

import com.codeup.adlister.models.UserPicture;

public interface UserPictures {
    UserPicture findByPicID(long picID);
    Long insertPic(UserPicture userPicture);
    UserPicture findPicByUserID(long userID);
    void updatePicURL(String newPicURL, long userID);

}
