package com.springframework.security.repository;

import com.springframework.security.entity.TRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<TRole, Long> {
}
