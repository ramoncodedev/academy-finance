package com.finance.academia.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SpringFilter extends OncePerRequestFilter {

    private final AuthService authService;
    private final TokenService tokenService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            String token = authorizationHeader.substring(7);

            String username = tokenService.ExtractUserName(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication()== null){
                UserDetails userDetails = authService.loadUserByUsername(username);

                if (tokenService.validateToken(token, username)){
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }

        }

        filterChain.doFilter(request, response);
    }
}
