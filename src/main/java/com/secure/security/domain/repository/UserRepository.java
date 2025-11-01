package com.secure.security.domain.repository;

import com.secure.security.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String user);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phone);
}
