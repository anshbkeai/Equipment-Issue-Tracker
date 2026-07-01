package com.anshbkeai.issuetracker.core.configuration;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.anshbkeai.issuetracker.core.model.Role;
import com.anshbkeai.issuetracker.core.service.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter{

    private final JWTService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String token = null;
        if (request.getCookies() != null) {
                for (var cookie : request.getCookies()) {
                    if (cookie.getName().equals("token")) {
                        token = cookie.getValue();
                        break;
                    }
                }
        }
        if(token == null || token.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/login");
            return ; 
        }
         if(!jwtService.validate(token)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
            return;
        }
        String username = jwtService.extractUsername(token);
        Role role = jwtService.extractRole(token); 
        
        UsernamePasswordAuthenticationToken authenticationToken = new
                                                                    UsernamePasswordAuthenticationToken(username, null ,  List.of(new SimpleGrantedAuthority("ROLE_" + role.name())) );
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.startsWith("/auth") || path.startsWith("/test") || path.contains("login");    
    }

}
