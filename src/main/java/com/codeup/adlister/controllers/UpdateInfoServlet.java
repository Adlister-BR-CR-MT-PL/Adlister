package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "UpdateInfoServlet", urlPatterns = "/updateinfo")
public class UpdateInfoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") == null) {
			request.getSession().setAttribute("url", request.getRequestURI());
			response.sendRedirect("/login");
			return;
		}
		request.getRequestDispatcher("/WEB-INF/user/profile.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long userID = Long.parseLong(request.getParameter("userId"));
		String updateEmail = request.getParameter("email");
		String updatePassword = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm-password");

		ArrayList<String> errors = new ArrayList<>();
		boolean inputHasErrors = false;

		if (updateEmail.isEmpty() || !updateEmail.contains(".") || !updateEmail.contains("@")) {
				inputHasErrors = true;
				String invalidEmail = "Please enter a valid email";
				errors.add(invalidEmail);
		}

		User user = DaoFactory.getUsersDao().findByUserEmail(updateEmail);
		if (user != null) {
			if (user.getId() == userID) {
				DaoFactory.getUsersDao().updateUserEmail(updateEmail, userID);
			} else {
				inputHasErrors = true;
				String emailExists = "Email is already in use";
				errors.add(emailExists);
			}
		} else {
			DaoFactory.getUsersDao().updateUserEmail(updateEmail, userID);
		}

		if ((!updatePassword.equals(confirmPassword))){
			inputHasErrors = true;
			String passwordMismatch = "Password inputs must match";
			errors.add(passwordMismatch);
		} else if (!updatePassword.isEmpty()) {
			DaoFactory.getUsersDao().updateUserPassword(updatePassword, userID);
		}

		if (inputHasErrors) {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/WEB-INF/user/profile.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("/logout");

	}




}