package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.Ads;
import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.AdPicture;
import com.codeup.adlister.models.User;
import com.codeup.adlister.models.UserPicture;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "adsDetailsServlet.java", urlPatterns = "/ads/detail")
public class adsDetailsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        checks for logged in user
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        User currentUser = (User) request.getSession().getAttribute("user");
        Ad currentAd = (Ad) request.getSession().getAttribute("ad");
        request.setAttribute("user", currentUser.getUsername());
        request.setAttribute("ad", currentAd);
//        request.setAttribute("adPic", DaoFactory.getGetAdsPicDao().findByadIDAds(currentAd.getId()).getUrl());

        request.getRequestDispatcher("/WEB-INF/ads/adsDetail.jsp")
                .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        User currentUser = (User) request.getSession().getAttribute("user");
//        Ad currentAd = (Ad) request.getSession().getAttribute("ad");
//
//        String imgURL = request.getParameter("adsPicture");

//        AdPicture thisAdPic = new AdPicture(currentAd.getId(), imgURL);
//        AdPicture adPicDao = DaoFactory.getGetAdsPicDao().findByadIDAds(currentAd.getId());
//
//        System.out.println("currentAd.getUserId() = " + currentAd.getUserId());
//        System.out.println("current user id " + currentUser.getId());
//        if (currentUser.getId() == currentAd.getUserId()) {
//            if (imgURL.isEmpty()) {
////                // makes sure AdPic doesn't change, and gives an error
//                request.getSession().setAttribute("PictureError", "error");
//                request.getSession().setAttribute("adPic", thisAdPic);
//                response.sendRedirect("/ads/detail");
//            }
//                if (adPicDao.getUrl() == null) {
//                    // if there is no ad pic for this ad, insert one into the database
//                    DaoFactory.getGetAdsPicDao().insert(thisAdPic);
//                } else {
//                    //if there is a picture, update and replace current picture
//                    DaoFactory.getGetAdsPicDao().updatePicURL(imgURL, currentAd.getId());
//                }
//            request.getSession().setAttribute("adPic", thisAdPic);
//            request.getSession().setAttribute("PictureError", null);
//            response.sendRedirect("/ads/detail");
//        }
        }
    }

