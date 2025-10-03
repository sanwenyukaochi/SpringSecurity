package com.springframework.security.repository;

import com.springframework.security.entity.TRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface RolePermissionRepository extends JpaRepository<TRolePermission, Integer> {
    List<TRolePermission> findByRoleIdIn(List<Long> roleIds);
}
