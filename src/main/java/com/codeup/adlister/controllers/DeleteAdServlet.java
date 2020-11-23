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

@WebServlet(name = "DeleteAdServlet", urlPatterns = "/delete-ad")
public class DeleteAdServlet extends HttpServlet {

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if (request.getSession().getAttribute("user") == null){
//            response.sendRedirect("/login");
//            return;
//        }
//
//        long adId = Long.parseLong(request.getParameter("adId"));
//        DaoFactory.getAdsDao().deleteAd(adId);
//        response.sendRedirect("/ads");
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null){
            response.sendRedirect("/login");
            return;
        }
        String adId = request.getParameter("delete-AdID");
        Long adIdLong = Long.valueOf(adId);
        //long adId = Long.parseLong(request.getParameter("delete-AdID"));
        Ad currentAd = DaoFactory.getAdsDao().findByAdID(adIdLong);
        AdPicture currentAdPicture = DaoFactory.getGetAdsPicDao().findAdPicByAdIDInAds(currentAd.getAdID());
        User currentUser = (User) request.getSession().getAttribute("user");


        //This is redirect the user if they are not the current AD creator, but its not working properly
        //Any user can still delete another users AD
        if (currentAd.getUserId() != currentUser.getId()){
            response.sendRedirect("/profile");
            return;
        }

        if (currentAdPicture == null) {
            DaoFactory.getAdsDao().deleteAd(adIdLong);
            response.sendRedirect("/ads");
            return;
        }
        DaoFactory.getGetAdsPicDao().deleteAdPicture(currentAdPicture.getAdID());
        DaoFactory.getAdsDao().deleteAd(adIdLong);

        response.sendRedirect("/ads");

    }
}