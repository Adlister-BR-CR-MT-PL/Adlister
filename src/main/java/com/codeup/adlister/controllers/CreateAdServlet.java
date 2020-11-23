package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.CreateAdServlet", urlPatterns = "/ads/create")
public class CreateAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        /*Todo:AAAAAAAAAAAAAAAAAA*/
        request.setAttribute("categories", DaoFactory.getCategoriesDao().all());
        request.getRequestDispatcher("/WEB-INF/ads/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        // Todo: need input validation and error messages with empty title here
        Ad ad = new Ad(
                user.getId(),
                request.getParameter("title"),
                request.getParameter("description")
        );

        Long adId = DaoFactory.getAdsDao().insert(ad);

        // get category array from checkbox result
        String[] categories = request.getParameterValues("category");

        boolean CreateAdError = request.getParameter("title").isEmpty()
                || (request.getParameter("title") == null)
                || (categories == null);

        if(CreateAdError){
            response.sendRedirect("/ads/create");
            return;
        }

        for (String cat : categories) {
            Category category = DaoFactory.getCategoriesDao().getCategoryByName(cat);
            DaoFactory.getCategoriesDao().insertToAdCategoryJoinTable(adId,category.getId());
        }

        response.sendRedirect("/ads");
    }
}
