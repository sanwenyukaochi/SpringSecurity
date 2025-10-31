package org.secure.security.authentication.handler.login.username;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.secure.security.authentication.service.UserService;
import org.secure.security.common.web.model.User;
import org.secure.security.authentication.handler.login.UserLoginInfo;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 帐号密码登录认证
 */
@Component
@RequiredArgsConstructor
public class UsernameAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 用户提交的用户名 + 密码：
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        User user = userService.getUserFromDB(username);
        if (user == null) {
            // 根据SpringSecurity框架的代码逻辑，认证失败时，应该抛这个异常：org.springframework.security.core.AuthenticationException
            // UsernameNotFoundException就是这个异常的子类
            // 抛出异常后后，AuthenticationFailureHandler的实现类会处理这个异常。
            throw new UsernameNotFoundException("${user.not.found:找不到用户!}");
        }

        if (!validatePassword(password, user.getPassword())) {
            // 密码错误，直接抛异常。
            // BadCredentialsException就是这个异常的子类
            throw new BadCredentialsException("${invalid.username.or.pwd:用户名或密码不正确}");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        UserLoginInfo currentUser = objectMapper.convertValue(user, UserLoginInfo.class);
        UsernameAuthentication token = new UsernameAuthentication();
        token.setCurrentUser(currentUser);
        token.setAuthenticated(true); // 认证通过，这里一定要设成true
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernameAuthentication.class.isAssignableFrom(authentication);
    }

    private boolean validatePassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
