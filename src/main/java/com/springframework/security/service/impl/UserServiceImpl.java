package com.springframework.security.service.impl;

import com.springframework.security.entity.Role;
import com.springframework.security.entity.User;
import com.springframework.security.entity.Permission;
import com.springframework.security.repository.RoleRepository;
import com.springframework.security.repository.UserRepository;
import com.springframework.security.repository.UserRoleRepository;
import com.springframework.security.repository.PermissionRepository;
import com.springframework.security.repository.RolePermissionRepository;
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
    private final PermissionRepository permissionRepository;
    private final RolePermissionRepository rolePermissionRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLoginAct(username).orElseThrow(() -> new UsernameNotFoundException("登录账号不存在"));
        List<Role> roles = roleRepository.findAllById(userRoleRepository.findByUserId(user.getId()).stream()
                .map(ur -> ur.getRole().getId())
                .toList());
        List<Permission> permissions = permissionRepository.findAllByIdIn(rolePermissionRepository.findByRoleIdIn(roles.stream().map(Role::getId).toList()).stream()
                .map(rp -> rp.getPermission().getId())
                .toList());
        return UserDetailsImpl.build(user, roles, permissions);
    }
}
