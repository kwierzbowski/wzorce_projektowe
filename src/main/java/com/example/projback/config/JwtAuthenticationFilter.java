package com.example.projback.config;

import com.example.projback.service.AuthService;
import com.example.projback.wzorce.L2.Bridge.AbstractClasses.JwtAuthenticationFilterBridge;
import com.example.projback.wzorce.L7.OCP.TokenValidatorInterface;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

//###   start L7 Open-Closed Principle -> Part 3 - code opened for extension, closed for modification. token validator
//###   start L2 Bridge -> JwtAuthenticationFilter -> Usage
@Component
public class JwtAuthenticationFilter extends JwtAuthenticationFilterBridge {
    //###   end L2 Bridge -> JwtAuthenticationFilter -> Usage
    private final JwtUtil jwtUtil;
    private final List<TokenValidatorInterface> tokenValidators;

//    @Lazy
//    private final AuthService authService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, @Lazy AuthService authService, List<TokenValidatorInterface> tokenValidators) {
        super(authService);
        this.jwtUtil = jwtUtil;
        this.tokenValidators = tokenValidators;
//        this.authService = authService;
    }

    //###   start L9 c,d -> part 1
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        if (isInvalidAuthorizationHeader(authorizationHeader)) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = extractJwtFromHeader(authorizationHeader);
        if (!isJwtValid(jwt)) {
            filterChain.doFilter(request, response);
            return;
        }

        authenticateUser(jwt, request);
        filterChain.doFilter(request, response);
    }

    private boolean isInvalidAuthorizationHeader(String authorizationHeader) {
        return authorizationHeader == null || !authorizationHeader.startsWith("Bearer ");
    }

    private String extractJwtFromHeader(String authorizationHeader) {
        return authorizationHeader.substring(7);
    }

    private boolean isJwtValid(String jwt) {
        for (TokenValidatorInterface validator : tokenValidators) {
            if (!validator.validate(jwt)) {
                return false;
            }
        }
        return true;
    }

    private void authenticateUser(String jwt, HttpServletRequest request) {
        String username = jwtUtil.extractUsername(jwt);
        if (username == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            return;
        }

        UserDetails userDetails = authService.loadUserByUsername(username);
        if (!jwtUtil.validateToken(jwt, userDetails)) {
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
    //###   end L9 c,d -> part 1

//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//        final String authorizationHeader = request.getHeader("Authorization");
//
//        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        String jwt = authorizationHeader.substring(7);
//        for (TokenValidatorInterface validator : tokenValidators) {
//            if (!validator.validate(jwt)) {
//                filterChain.doFilter(request, response);
//                return;
//            }
//        }
//
//        String username = jwtUtil.extractUsername(jwt);
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = this.authService.loadUserByUsername(username);
//            if (jwtUtil.validateToken(jwt, userDetails)) {
//                UsernamePasswordAuthenticationToken authenticationToken =
//                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }

//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//        final String authorizationHeader = request.getHeader("Authorization");
//
//        String username = null;
//        String jwt = null;
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            jwt = authorizationHeader.substring(7);
//            username = jwtUtil.extractUsername(jwt);
//        }
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = this.authService.loadUserByUsername(username);
//
//            if (jwtUtil.validateToken(jwt, userDetails)) {
//                UsernamePasswordAuthenticationToken authenticationToken =
//                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
}
//###   end L7 Open-Closed Principle -> Part 3