package com.secure.security.authentication.handler.auth.email;

import lombok.Getter;
import lombok.Setter;
import com.secure.security.authentication.handler.auth.UserLoginInfo;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Setter
@Getter
public class EmailAuthentication extends AbstractAuthenticationToken {

    private String email;
    private String password;
    private UserLoginInfo currentUser;

    public EmailAuthentication(String email, String password, Boolean authenticated) {
        this.email = email;
        this.password = password;
        super(null); // 权限，用不上，直接null
        super.setAuthenticated(authenticated);
    }

    public EmailAuthentication(UserLoginInfo currentUser, Boolean authenticated,
                               Collection<? extends GrantedAuthority> authorities) {
        this.currentUser = currentUser;
        super(authorities);
        super.setAuthenticated(authenticated);
    }

    @Override
    public Object getCredentials() {
        return isAuthenticated() ? null : password;
    }

    @Override
    public Object getPrincipal() {
        return isAuthenticated() ? currentUser : email;
    }
}