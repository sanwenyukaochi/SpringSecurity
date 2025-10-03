package com.springframework.security.repository;

import com.springframework.security.entity.TPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<TPermission, Integer> {
    List<TPermission> findAllByIdIn(Collection<Long> ids);
}
