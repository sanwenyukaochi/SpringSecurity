package com.secure.security.authentication.handler.auth.email;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

@Slf4j
public class EmailAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper;

    public EmailAuthenticationFilter(PathPatternRequestMatcher pathRequestMatcher,
                                     AuthenticationManager authenticationManager,
                                     AuthenticationSuccessHandler authenticationSuccessHandler,
                                     AuthenticationFailureHandler authenticationFailureHandler,
                                     ObjectMapper objectMapper) {
        super(pathRequestMatcher);
        setAuthenticationManager(authenticationManager);
        setAuthenticationSuccessHandler(authenticationSuccessHandler);
        setAuthenticationFailureHandler(authenticationFailureHandler);
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        log.debug("use EmailAuthenticationFilter");

        EmailLoginRequest emailLoginRequest = objectMapper.readValue(request.getInputStream(), EmailLoginRequest.class);
        EmailAuthentication authentication = new EmailAuthentication(emailLoginRequest.email(), emailLoginRequest.password(), false);
        return getAuthenticationManager().authenticate(authentication);
    }
}