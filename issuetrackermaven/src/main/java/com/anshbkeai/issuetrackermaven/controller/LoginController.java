package com.anshbkeai.issuetrackermaven.controller;

import java.io.IOException;

import com.anshbkeai.issuetrackermaven.service.AppUserAuthService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
        request.getRequestDispatcher("/WEB-INF/views/login.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Invalidate all cookies
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                resp.addCookie(cookie);
            }
        }
        
        AppUserAuthService appUserAuthService = new AppUserAuthService();
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println("LoginController: username=" + username + ", password=" + password);
        String loginResult = appUserAuthService.login(username, password);
        System.out.println("LoginController: loginResult=" + loginResult);
        Cookie cookie = new Cookie("token", loginResult.replace(" ", "_"));
        resp.addCookie(cookie);

        resp.sendRedirect("test/v1");
        
    }


}
