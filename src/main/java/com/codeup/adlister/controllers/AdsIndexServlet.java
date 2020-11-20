package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.Ads;
import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "controllers.AdsIndexServlet", urlPatterns = "/ads")
public class AdsIndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("ads", DaoFactory.getAdsDao().all());
        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ad_ID = request.getParameter("ad-ID");
        Long adLong = Long.valueOf(ad_ID);
        Ad ad = DaoFactory.getAdsDao().findByAdID(adLong);

        String user_ID = request.getParameter("userID");
        Long UserLong = Long.valueOf(user_ID);
        User user = DaoFactory.getUsersDao().findByUserID(UserLong);
        request.getSession().setAttribute("ad", ad);
        response.sendRedirect("/ads/detail");
        request.getSession().setAttribute("user", user);
    }
//        String ad_search = request.getParameter("ad-search");
//        request.getRequestDispatcher("/ads").forward(request, response);
}
