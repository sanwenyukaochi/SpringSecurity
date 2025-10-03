package com.springframework.security.service.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springframework.security.entity.TUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private Long id;
    private String loginAct;
    @JsonIgnore
    private String loginPwd;
    private String name;
    private String phone;
    private String email;
    private Long accountNoExpired;
    private Long credentialsNoExpired;
    private Long accountNoLocked;
    private Long accountEnabled;
    private Date createTime;
    private Long createBy;
    private Date editTime;
    private Long editBy;
    private Date lastLoginTime;

    public static UserDetailsImpl build(TUser user) {
        return new UserDetailsImpl(
                user.getId(),
                user.getLoginAct(),
                user.getLoginPwd(),
                user.getName(),
                user.getPhone(),
                user.getEmail(),
                user.getAccountNoExpired(),
                user.getCredentialsNoExpired(),
                user.getAccountNoLocked(),
                user.getAccountEnabled(),
                user.getCreateTime(),
                user.getCreateBy(),
                user.getEditTime(),
                user.getEditBy(),
                user.getLastLoginTime()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return this.loginPwd;
    }

    @Override
    public String getUsername() {
        return this.loginAct;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNoExpired == 1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNoLocked == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNoExpired == 1;
    }

    @Override
    public boolean isEnabled() {
        return this.accountEnabled == 1;
    }
}
