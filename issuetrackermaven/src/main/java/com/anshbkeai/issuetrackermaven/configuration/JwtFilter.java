package com.anshbkeai.issuetrackermaven.configuration;

import java.io.IOException;

import com.anshbkeai.issuetrackermaven.service.JwtService;

import io.jsonwebtoken.security.SecurityException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class JwtFilter implements Filter{
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        try {
            if(httpRequest.getRequestURI().contains("/login") || httpRequest.getRequestURI().contains("/signup")) {
                    chain.doFilter(request, response);
                    return;
            }
            String token = null;
            if (httpRequest.getCookies() != null) {
                for (var cookie : httpRequest.getCookies()) {
                    if (cookie.getName().equals("token")) {
                        token = cookie.getValue();
                        break;
                    }
                }
            }
            if (token == null || token.isEmpty()) {
                httpServletResponse.sendRedirect(httpRequest.getContextPath() + "/login");
                return;
            } 
            System.out.println("JwtFilter: token=" + token + " request uti = " + httpRequest.getRequestURI());

            JwtService jwtService = new JwtService();

            if( !jwtService.validate(token))  {
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
                return;
            }

            String username = jwtService.extractUsername(token);

            httpRequest.setAttribute("username", username);
            httpRequest.setAttribute("isAuthenticated", true);
        }
        catch(Exception e) {
             httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
        }
        
        chain.doFilter(request, response);
    }


}
