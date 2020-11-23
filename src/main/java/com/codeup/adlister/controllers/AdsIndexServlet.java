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
        String search = request.getParameter("search");
        String category = request.getParameter("category");
        String sort = request.getParameter("sort");

        //Todo:Figure out
        /*request.getSession().setAttribute("categoriesDao", DaoFactory.getCategoriesDao());*/
        /*request.setAttribute("categoriesDao", DaoFactory.getCategoriesDao());*/

        boolean searchBySearchKeywords = search != null && !search.equals("");
        boolean searchByCategory = category != null && !category.equals("");
        /*boolean searchBySort = sort != null && !sort.equals("");*/


        /*Zero Variable Searches = All Ads*/
        if(!searchBySearchKeywords && !searchByCategory) {
            request.setAttribute("ads", DaoFactory.getAdsDao().all());
            request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
        }

        /*One Variable Searches*/
        if(searchBySearchKeywords) {
            request.setAttribute("ads", DaoFactory.getAdsDao().getAdsBySearchKeyword(search));
            request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
        }

        /*Todo: Error Running*/
/*        if(searchByCategory) {
            request.setAttribute("ads", DaoFactory.getAdsDao().getAdsBySearchCategory(category));
            request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
        }*/

//        if(searchBySort) {
//            request.setAttribute("ads", DaoFactory.getAdsDao().getAdsBySort(sort));
//            request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
//        }

//        Two Variable Searches
//        Todo: IMPORTANTE LOOK INTO DUMBO
//        if(searchBySearchKeywords && searchByCategory) {
//            Category category = DaoFactory.getCategoriesDao().getCategoryByTitle(cat);
//            request.setAttribute("category", category);
//            request.setAttribute("ads", DaoFactory.getAdsDao().getAdsBySearchAndCategory(search, category));
//            request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
//        }
//
//        if(searchBySearchKeywords && searchBySort) {
//            Category category = DaoFactory.getCategoriesDao().getCategoryByTitle(cat);
//            request.setAttribute("category", category);
//            request.setAttribute("ads", DaoFactory.getAdsDao().getAdsBySearchAndCategory(search, category));
//            request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
//        }
//
//        if(searchByCategory && searchBySort) {
//            Category category = DaoFactory.getCategoriesDao().getCategoryByTitle(cat);
//            request.setAttribute("category", category);
//            request.setAttribute("ads", DaoFactory.getAdsDao().getAdsBySearchAndCategory(search, category));
//            request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
//        }
//
//        Ads Index: All Ads
//        TODO: IMPORTANTE FIGURE OUT X3
//        if(!searchBySearchKeywords && !searchByCategory && searchBySort) {
//            Category category = DaoFactory.getCategoriesDao().getCategoryByTitle(cat);
//            request.setAttribute("category", category);
//            request.setAttribute("ads", DaoFactory.getAdsDao().getAdsBySearchAndCategory(search, category));
//            request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
//        }
    }
    /*TODO: Figure out what she wants to do with do post*/
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ad_ID = request.getParameter("ad-ID");
        Long adLong = Long.valueOf(ad_ID);
        Ad ad = DaoFactory.getAdsDao().findByAdID(adLong);

        String user_ID = request.getParameter("userID");
        Long UserLong = Long.valueOf(user_ID);
        User user = DaoFactory.getUsersDao().findByUserID(UserLong);
        request.getSession().setAttribute("ad", ad);
        response.sendRedirect("/ads/detail");
        request.getSession().setAttribute("users", user);
    }
}