package org.secure.security.authentication.handler.login.sms;

import lombok.Getter;
import lombok.Setter;
import org.secure.security.authentication.handler.login.UserLoginInfo;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Setter
@Getter
public class SmsAuthentication extends AbstractAuthenticationToken {

    private String phone;
    private String smsCode;
    private UserLoginInfo currentUser;

    public SmsAuthentication(String phone, String smsCode, Boolean authenticated) {
        this.phone = phone;
        this.smsCode = smsCode;
        super(null); // 权限，用不上，直接null
        super.setAuthenticated(authenticated);
    }

    public SmsAuthentication(UserLoginInfo currentUser, Boolean authenticated,
                             Collection<? extends GrantedAuthority> authorities) {
        this.currentUser = currentUser;
        super(authorities);
        super.setAuthenticated(authenticated);
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