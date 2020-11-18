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
        // sending to JSP
        request.setAttribute("userPic", DaoFactory.getGetUserPicDao().findByPicUserID(currentUser.getId()).getImgURL());

        request.setAttribute("ads", DaoFactory.getAdsDao().all());
        request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User) request.getSession().getAttribute("user");
        String imgURL = request.getParameter("userPicture");
        Boolean noImgURL = imgURL.isEmpty();

        UserPicture userPic = new UserPicture(imgURL, currentUser.getId());
        UserPicture userPicDao = DaoFactory.getGetUserPicDao().findByPicUserID(currentUser.getId());


        if (noImgURL) {
            request.getSession().setAttribute("PictureError", "error");
            return;
        }

        if (userPicDao.getImgURL() == null) {
            DaoFactory.getGetUserPicDao().insertPic(userPic);
            request.getSession().setAttribute("userPic", userPic);
            response.sendRedirect("/profile");
        } else {
            DaoFactory.getGetUserPicDao().updatePicURL(imgURL, currentUser.getId());
            request.getSession().setAttribute("userPic", userPic);
            response.sendRedirect("/profile");
        }
    }
}

