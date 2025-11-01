package com.secure.security.authentication.handler.auth.github;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import com.secure.security.authentication.handler.auth.UserLoginInfo;
import com.secure.security.authentication.handler.auth.github.dto.GitHubAccessTokenResponse;
import com.secure.security.authentication.handler.auth.github.dto.GitHubUserProfile;
import com.secure.security.authentication.handler.auth.github.service.GitHubOAuthService;
import com.secure.security.authentication.service.UserIdentityService;
import com.secure.security.authentication.service.UserService;
import com.secure.security.domain.model.entity.User;
import com.secure.security.domain.model.entity.UserIdentity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GitHubAuthenticationProvider implements AuthenticationProvider {

    private final GitHubOAuthService githubOAuthService;

    private final UserService userService;

    private final UserIdentityService userIdentityService;

    private final ObjectMapper objectMapper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String code = (String) authentication.getPrincipal();
        try {
            GitHubAccessTokenResponse response = githubOAuthService.exchangeCodeForToken(code);
            GitHubUserProfile profile = githubOAuthService.fetchUserProfile(response.accessToken());

            UserIdentity userIdentity = userIdentityService.getUserIdentityByProviderUserId(profile.id(), UserIdentity.AuthProvider.GITHUB);
            User user = userService.findById(userIdentity.getUserId());

            UserLoginInfo currentUser = objectMapper.convertValue(user, UserLoginInfo.class);
            GitHubAuthentication token = new GitHubAuthentication(currentUser, true, List.of());
            // 构造认证对象
            return token;
        } catch (Exception e) {
            throw new BadCredentialsException("GitHub OAuth2 登录失败: " + e.getMessage());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return GitHubAuthentication.class.isAssignableFrom(authentication);
    }
}