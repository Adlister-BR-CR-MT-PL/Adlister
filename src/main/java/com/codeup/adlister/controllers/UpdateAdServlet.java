package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.AdPicture;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateAdServlet", urlPatterns = "/update-ad")
public class UpdateAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null){
            response.sendRedirect("/login");
            return;
        }

        request.getRequestDispatcher("/WEB-INF/ads/editAd.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null){
            response.sendRedirect("/login");
            return;
        }

        String adId = request.getParameter("update-AdID");
        Long adIdLong = Long.valueOf(adId);
        String adTitle = request.getParameter("title");
        String adDescription = request.getParameter("description");
        //long adId = Long.parseLong(request.getParameter("delete-AdID"));
        Ad currentAd = DaoFactory.getAdsDao().findByAdID(adIdLong);
        //AdPicture currentAdPicture = DaoFactory.getGetAdsPicDao().findAdPicByAdIDInAds(currentAd.getId());
        User currentUser = (User) request.getSession().getAttribute("user");


        //This is redirect the user if they are not the current AD creator, but its not working properly
        //Any user can still delete another users AD
        if (currentAd.getUserId() != currentUser.getId()){
            response.sendRedirect("/profile");
            return;
        }

        DaoFactory.getAdsDao().updateAd(adIdLong, adTitle, adDescription);
        response.sendRedirect("/ads/adsDetail");

    }

}
