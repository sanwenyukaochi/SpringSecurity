package org.secure.security.authentication.handler.login.github;

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
public class GithubAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public GithubAuthenticationFilter(PathPatternRequestMatcher pathRequestMatcher,
                                      AuthenticationManager authenticationManager,
                                      AuthenticationSuccessHandler authenticationSuccessHandler,
                                      AuthenticationFailureHandler authenticationFailureHandler) {
        super(pathRequestMatcher);
        setAuthenticationManager(authenticationManager);
        setAuthenticationSuccessHandler(authenticationSuccessHandler);
        setAuthenticationFailureHandler(authenticationFailureHandler);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        log.debug("use GithubAuthenticationFilter");

        ObjectMapper objectMapper = new ObjectMapper();
        GithubLoginRequest githubLoginRequest = objectMapper.readValue(request.getInputStream(), GithubLoginRequest.class);

        GithubAuthentication authentication = new GithubAuthentication(githubLoginRequest.code(), false);
        return getAuthenticationManager().authenticate(authentication);
    }
}