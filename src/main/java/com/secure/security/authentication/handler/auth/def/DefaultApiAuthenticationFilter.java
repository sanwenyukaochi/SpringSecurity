package com.secure.security.authentication.handler.auth.def;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class DefaultApiAuthenticationFilter extends OncePerRequestFilter {

    @Value("${spring.security.user.name}")
    private String username;

    @Value("${spring.security.user.password}")
    private String password;

    public DefaultApiAuthenticationFilter() {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        System.out.println("这里是默认过滤链...");
        // 随便给个默认身份
        Authentication authentication = new TestingAuthenticationToken(username, password, "");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 放行
        filterChain.doFilter(request, response);
    }
}
