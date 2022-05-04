package com.gini.security.filter;

import com.gini.security.authentification.CentralUnitAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        String authorizationKey = request.getHeader("X-API-Authorization-Key");


        Authentication auth = new CentralUnitAuthentication(null, authorizationKey);

        Authentication fullyAuthenticated = authenticationManager.authenticate(auth);

        SecurityContextHolder.getContext().setAuthentication(fullyAuthenticated);






        filterChain.doFilter(request, response);

    }
}
