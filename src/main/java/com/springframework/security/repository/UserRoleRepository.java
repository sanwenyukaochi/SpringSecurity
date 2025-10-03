package com.springframework.security.repository;

import com.springframework.security.entity.TUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<TUserRole,Long> {
    List<TUserRole> findByUserId(Long id);
}
