package com.springframework.security.service.impl;

import com.springframework.security.entity.TRole;
import com.springframework.security.entity.TUser;
import com.springframework.security.repository.RoleRepository;
import com.springframework.security.repository.UserRepository;
import com.springframework.security.repository.UserRoleRepository;
import com.springframework.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TUser tUser = userRepository.findByLoginAct(username).orElseThrow(() -> new UsernameNotFoundException("登录账号不存在"));
        List<TRole> tRoles = roleRepository.findAllById(userRoleRepository.findByUserId(tUser.getId()).stream()
                .map(ur -> ur.getRole().getId())
                .toList());
        return UserDetailsImpl.build(tUser, tRoles);
    }
}
