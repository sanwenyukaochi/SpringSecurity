package com.sanwenyukaochi.security.repository;

import com.sanwenyukaochi.security.entity.relation.ClipTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClipTagRepository extends JpaRepository<ClipTag, Long> {
    
}