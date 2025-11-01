package org.secure.security.authentication.handler.login.github;

import lombok.Getter;
import lombok.Setter;
import org.secure.security.authentication.handler.login.UserLoginInfo;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Setter
@Getter
public class GithubAuthentication extends AbstractAuthenticationToken {

    private String code;
    private UserLoginInfo currentUser;

    public GithubAuthentication(String code, Boolean authenticated) {
        super(null);
        this.code = code;
        super.setAuthenticated(authenticated);
    }

    public GithubAuthentication(UserLoginInfo currentUser, Boolean authenticated,
                                Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.currentUser = currentUser;
        super.setAuthenticated(authenticated);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return isAuthenticated() ? currentUser : code;
    }
}