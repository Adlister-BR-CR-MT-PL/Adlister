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
import java.util.ArrayList;

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
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String[] categories = request.getParameterValues("category");

        ArrayList<String> errors = new ArrayList<>();
        boolean inputHasErrors = false;

        if (title.isEmpty() || categories == null) {
            inputHasErrors = true;
            String invalidAd = "Please enter a valid ad";
            errors.add(invalidAd);
        }

        if (inputHasErrors) {
            request.setAttribute("errors", errors);
            try {
                request.getRequestDispatcher("/WEB-INF/user/profile.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
            return;
        }

        Ad ad = new Ad(
                user.getId(),
                request.getParameter("title"),
                request.getParameter("description")
            );

        Long adId = DaoFactory.getAdsDao().insert(ad);

        for (String cat : categories) {
            Category category = DaoFactory.getCategoriesDao().getCategoryByName(cat);
            DaoFactory.getCategoriesDao().insertToAdCategoryJoinTable(adId,category.getId());
        }
        response.sendRedirect("/ads");
    }
}
