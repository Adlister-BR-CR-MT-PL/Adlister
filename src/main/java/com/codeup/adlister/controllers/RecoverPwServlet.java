package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "controllers.RecoverPwServlet", urlPatterns = "/recoverpassword")
public class RecoverPwServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/recoverpw.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
/*		String securityQuestionQ = request.getParameter("securityQuestionQ");
		String securityQuestionA = request.getParameter("securityQuestionA");*/
		String newPassword = request.getParameter("new_password");
		String newPasswordConfirmation = request.getParameter("confirm_new_password");

/*		PrintWriter out = response.getWriter();
		out.println("Retrieved values:");
		out.println(username);
		out.println(email);
		out.println(securityQuestionQ);
		out.println(securityQuestionA);
		out.println(newPassword);
		out.println(newPasswordConfirmation);*/


		User user = DaoFactory.getUsersDao().findByUsername(username);
		boolean inputError = email.equals(user.getEmail())
/*		                     || securityQuestionQ.equals(user.getSecurityQuestionQ())
		                       || securityQuestionA.equals(user.getSecurityQuestionA())*/
						|| newPassword.equals(newPasswordConfirmation);
		boolean duplicatePw = Password.check(newPassword, user.getPassword());

		if (duplicatePw || inputError) {
			response.sendRedirect("/recoverpassword");
			return;
		}

		user.setPassword(newPassword);
		response.sendRedirect("/login");
	}
}