package com.sanwenyukaochi.security.repository;

import com.sanwenyukaochi.security.entity.ClipGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClipGroupRepository extends JpaRepository<ClipGroup, Long> {
}