package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;

import java.util.List;

public interface Users {
    /*=================================================Find By===================================================*/
    User findByUsername(String username);
    User findByUserID(Long userID);
    User findByUserEmail(String email);

    /*=================================================Modify User===================================================*/
    Long insert(User user);
    void updateUser(User user);
    void updateUserEmail(String email, long id);
    void updateUserPassword(String password, long id);
    void deleteUser(User user);
}
