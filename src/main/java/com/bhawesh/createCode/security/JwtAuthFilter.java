package com.bhawesh.createCode.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final AuthUtil authUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String requestHeaderToken = request.getHeader("Authorization");
        if(requestHeaderToken==null || !requestHeaderToken.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }
        String accessToken = requestHeaderToken.split("Bearer ")[1];
        JwtUserPrincipal user = authUtil.getUserFromToken(accessToken);
        if(user!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,null,user.authorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request,response);
    }
}
