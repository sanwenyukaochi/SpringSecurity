package com.springframework.security.repository;

import com.springframework.security.entity.TUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<TUser, Long> {
    Optional<TUser> findByLoginAct(String loginAct);
}
