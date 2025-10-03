package com.springframework.security.service.impl;

import com.springframework.security.entity.TUser;
import com.springframework.security.repository.UserRepository;
import com.springframework.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TUser TUser = userRepository.findByLoginAct(username).orElseThrow(() -> new UsernameNotFoundException("登录账号不存在"));
        return UserDetailsImpl.build(TUser);
    }
}
