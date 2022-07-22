package com.unla.agroecologiaiot.filters;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.unla.agroecologiaiot.constants.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    public AuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(SecurityConstants.HEADER_NAME);

        if (header == null) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = authenticate(request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken authenticate(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        // 1. Check if format: 'Bearer token' is correct
        String[] headerComponents = authHeader.split(" ");

        if (!headerComponents[0].equals("Bearer")) {
            return null;
        }

        // 2. Check if token is empty
        if (headerComponents[1].trim().isEmpty()) {
            return null;
        }

        // 3. If validations are correct, create Claims
        Claims user = Jwts.parserBuilder()
                .setSigningKey(SecurityConstants.SECRET.getBytes())
                .build()
                .parseClaimsJws(headerComponents[1].toString())
                .getBody();

        if (user != null) {
            return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        } else {
            return null;
        }

    }

}
