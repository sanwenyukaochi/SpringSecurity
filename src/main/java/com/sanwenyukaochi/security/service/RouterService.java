package com.sanwenyukaochi.security.service;

import com.sanwenyukaochi.security.dto.RouterDTO;
import com.sanwenyukaochi.security.entity.Permission;
import com.sanwenyukaochi.security.entity.Role;
import com.sanwenyukaochi.security.entity.relation.RolePermission;
import com.sanwenyukaochi.security.entity.relation.UserRole;
import com.sanwenyukaochi.security.repository.UserRoleRepository;
import com.sanwenyukaochi.security.repository.RolePermissionRepository;
import com.sanwenyukaochi.security.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouterService {
    
    private final UserRoleRepository userRoleRepository;
    private final RolePermissionRepository rolePermissionRepository;
    
    public List<RouterDTO> getUserRouters(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getId();
        
        // 1. 获取用户的所有角色
        List<UserRole> userRoles = userRoleRepository.findAllByUser_Id(userId);
        List<Role> roles = userRoles.stream()
                .map(UserRole::getRole)
                .collect(Collectors.toList());
        
        // 2. 获取角色对应的所有权限
        List<RolePermission> rolePermissions = rolePermissionRepository.findAllByRoleIn(roles);
        List<Permission> permissions = rolePermissions.stream()
                .map(RolePermission::getPermission)
                .filter(Permission::getVisible)
                .collect(Collectors.toList());
        
        // 3. 构建路由树结构
        return buildRouterTree(permissions);
    }
    
    private List<RouterDTO> buildRouterTree(List<Permission> permissions) {
        // 按parentId分组
        Map<Long, List<Permission>> permissionMap = permissions.stream()
                .collect(Collectors.groupingBy(Permission::getParentId));
        
        // 获取根节点（parentId为0的权限）
        List<Permission> rootPermissions = permissionMap.getOrDefault(0L, new ArrayList<>());
        
        return rootPermissions.stream()
                .sorted(Comparator.comparing(Permission::getSort))
                .map(permission -> buildRouterDTO(permission, permissionMap))
                .collect(Collectors.toList());
    }
    
    private RouterDTO buildRouterDTO(Permission permission, Map<Long, List<Permission>> permissionMap) {
        RouterDTO routerDTO = new RouterDTO();
        routerDTO.setId(permission.getId());
        routerDTO.setName(permission.getName());
        routerDTO.setCode(permission.getCode());
        routerDTO.setPath(permission.getPath());
        routerDTO.setType(permission.getType());
        routerDTO.setSort(permission.getSort());
        
        // 递归构建子节点
        List<Permission> children = permissionMap.getOrDefault(permission.getId(), new ArrayList<>());
        if (!children.isEmpty()) {
            List<RouterDTO> childRouters = children.stream()
                    .sorted(Comparator.comparing(Permission::getSort))
                    .map(child -> buildRouterDTO(child, permissionMap))
                    .collect(Collectors.toList());
            routerDTO.setChildren(childRouters);
        }
        
        return routerDTO;
    }
}