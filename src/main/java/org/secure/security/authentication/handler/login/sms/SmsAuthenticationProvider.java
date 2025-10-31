package org.secure.security.authentication.handler.login.sms;

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
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SmsAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 用户提交的手机号 + 验证码：
        String phoneNumber = (String) authentication.getPrincipal();
        String smsCode = (String) authentication.getCredentials();

        User user = userService.getUserByPhone(phoneNumber);
        if (user == null) {
            // 密码错误，直接抛异常。
            // 根据SpringSecurity框架的代码逻辑，认证失败时，应该抛这个异常：org.springframework.security.core.AuthenticationException
            // BadCredentialsException就是这个异常的子类
            throw new UsernameNotFoundException("${user.not.found:找不到用户!}");
        }

        // 验证验证码是否正确
        if (!validateSmsCode(smsCode)) {
            throw new BadCredentialsException("${verify.sms.code.fail:验证码不正确！}");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        UserLoginInfo currentUser = objectMapper.convertValue(user, UserLoginInfo.class);
        SmsAuthentication token = new SmsAuthentication();
        token.setCurrentUser(currentUser);
        token.setAuthenticated(true); // 认证通过，一定要设成true
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsAuthentication.class.isAssignableFrom(authentication);
    }

    private boolean validateSmsCode(String smsCode) {
        // todo
        return smsCode.equals("000000");
    }
}
