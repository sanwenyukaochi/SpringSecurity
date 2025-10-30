package org.secure.security.authentication.handler.login.sms;

import lombok.Getter;
import lombok.Setter;
import org.secure.security.authentication.handler.login.UserLoginInfo;
import org.springframework.security.authentication.AbstractAuthenticationToken;

@Setter
@Getter
public class SmsAuthentication extends AbstractAuthenticationToken {

    private String phone;
    private String smsCode;
    private UserLoginInfo currentUser;

    public SmsAuthentication() {
        super(null); // 权限，用不上，直接null
    }

    @Override
    public Object getCredentials() {
        return isAuthenticated() ? null : smsCode;
    }

    @Override
    public Object getPrincipal() {
        return isAuthenticated() ? currentUser : phone;
    }

}