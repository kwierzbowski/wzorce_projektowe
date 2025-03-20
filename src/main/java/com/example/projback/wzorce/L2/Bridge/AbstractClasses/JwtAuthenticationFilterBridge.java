package com.example.projback.wzorce.L2.Bridge.AbstractClasses;

import com.example.projback.service.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//###   start L2 Bridge -> JwtAuthenticationFilter -> Klasa abstrakcyjna
public abstract class JwtAuthenticationFilterBridge extends OncePerRequestFilter {

    protected AuthService authService;

    public JwtAuthenticationFilterBridge(AuthService authService) {
        this.authService = authService;
    }


    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

    }
}
//###   end L2 Bridge -> JwtAuthenticationFilter -> Klasa abstrakcyjna