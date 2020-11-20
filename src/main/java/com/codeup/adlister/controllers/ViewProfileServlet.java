package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import com.codeup.adlister.models.UserPicture;
import com.mysql.cj.api.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "controllers.ViewProfileServlet", urlPatterns = "/profile")
public class ViewProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }

        //get current User
        User currentUser = (User) request.getSession().getAttribute("user");

        // sending to JSP if there is a profile picture
        if ((DaoFactory.getGetUserPicDao().findPicByUserID(currentUser.getId())) != null) {
            request.setAttribute("userPic", DaoFactory.getGetUserPicDao().findPicByUserID(currentUser.getId()).getImgURL());
        }

        request.setAttribute("ads", DaoFactory.getAdsDao().all());
        request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
        request.getRequestDispatcher("/ads/detail.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ADD/UPDATE USER PROFILE PICTURE
        User currentUser = (User) request.getSession().getAttribute("user");
        String imgURL = request.getParameter("userPicture");

            //creating new userPic instance with the user_id column equal to current user user_id
            UserPicture userPic = new UserPicture(imgURL, currentUser.getId());
            UserPicture userPicDao = DaoFactory.getGetUserPicDao().findPicByUserID(currentUser.getId());

        if (imgURL.isEmpty()) {
            // makes sure userPic doesn't change, and gives an error
            request.getSession().setAttribute("PictureError", "error");
            request.getSession().setAttribute("userPic", userPic);
            response.sendRedirect("/profile");
            return;
        }

//            if (userPicDao.getImgURL() == null) {
//                // if there is no userPic for this user, insert one into the database
//                DaoFactory.getGetUserPicDao().insertPic(userPic);
//            } else {
//                //if there is a picture, update and replace current picture
//                DaoFactory.getGetUserPicDao().updatePicURL(imgURL, currentUser.getId());
//            }

        if (userPicDao == null) {
            // if there is no userPic for this user, insert one into the database
            DaoFactory.getGetUserPicDao().insertPic(userPic);
        } else {
            //if there is a picture, update and replace current picture
            DaoFactory.getGetUserPicDao().updatePicURL(imgURL, currentUser.getId());
        }
        request.getSession().setAttribute("userPic", userPic);
        request.getSession().setAttribute("PictureError", null);
        response.sendRedirect("/profile");

//        request.getSession().setAttribute("ad", );
        //UPDATE PROFILE INFORMATION
//        //Michael made the update jsp and functionality, so this is not needed
//        if(request.getParameter("pageName").equals("editProfile")){
//            response.sendRedirect("/editProfile");
//        }

    }
}

