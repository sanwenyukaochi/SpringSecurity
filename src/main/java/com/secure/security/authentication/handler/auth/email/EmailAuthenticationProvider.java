package com.secure.security.authentication.handler.auth.email;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import com.secure.security.authentication.handler.auth.UserLoginInfo;
import com.secure.security.authentication.service.UserService;
import com.secure.security.domain.model.entity.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmailAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        User user = userService.getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("邮箱不存在");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("邮箱或密码不正确");
        }

        UserLoginInfo currentUser = objectMapper.convertValue(user, UserLoginInfo.class);//TODO 权限
        EmailAuthentication token = new EmailAuthentication(currentUser, true, List.of());
        // 认证通过，这里一定要设成true
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return EmailAuthentication.class.isAssignableFrom(authentication);
    }
}
