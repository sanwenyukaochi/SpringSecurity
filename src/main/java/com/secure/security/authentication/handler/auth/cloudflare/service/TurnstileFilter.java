package com.secure.security.authentication.handler.auth.cloudflare.service;

import io.github.sanwenyukaochi.cloudflare.turnstile.service.TurnstileValidationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class TurnstileFilter extends OncePerRequestFilter {

    private final TurnstileValidationService validationService;

    private static final String protectedPaths = "/auth/cloudflare-turnstile-protected";
    private static final String failureRedirectUrl = "/auth/cloudflare-turnstile-protected?error=captcha";
    private static final String turnstileTokenParameterName = "cf-turnstile-response";

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals(protectedPaths) && "POST".equalsIgnoreCase(request.getMethod())) {
            String token = request.getParameter(turnstileTokenParameterName);
            boolean valid = validationService.validateTurnstileResponse(token, getClientIp(request));
            if (valid) {
                log.info("Turnstile captcha validation succeeded for request: {}", request.getServletPath());
                filterChain.doFilter(request, response);
            } else {
                log.warn("Turnstile captcha validation failed for request: {}", request.getServletPath());
                response.sendRedirect(failureRedirectUrl);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private String getClientIp(HttpServletRequest request) {
        // Delegate to the service method or use a similar logic
        return validationService.getClientIpAddress(request);
    }
}