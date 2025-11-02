package com.secure.security.authentication.handler.auth.github;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import com.secure.security.authentication.handler.auth.UserLoginInfo;
import com.secure.security.authentication.handler.auth.github.service.GitHubOAuth2Service;
import com.secure.security.authentication.service.UserIdentityService;
import com.secure.security.authentication.service.UserService;
import com.secure.security.domain.model.entity.User;
import com.secure.security.domain.model.entity.UserIdentity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GitHubAuthenticationProvider implements AuthenticationProvider {

    private final GitHubOAuth2Service githubOAuth2Service;

    private final UserService userService;

    private final UserIdentityService userIdentityService;

    private final ObjectMapper objectMapper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String code = (String) authentication.getPrincipal();
        try {
            OAuth2User oAuth2User = githubOAuth2Service.authenticateByCode(code);
            Long providerUserId = Optional.ofNullable(oAuth2User.getAttribute("id"))
                    .filter(Number.class::isInstance)
                    .map(Number.class::cast)
                    .map(Number::longValue)
                    .orElseThrow(() -> new BadCredentialsException("GitHub 用户 ID 无效或为空"));

            UserIdentity userIdentity = userIdentityService.getUserIdentityByProviderUserId(providerUserId, UserIdentity.AuthProvider.GITHUB);
            User user = userService.findById(userIdentity.getUserId());

            UserLoginInfo currentUser = objectMapper.convertValue(user, UserLoginInfo.class);//TODO 权限
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