package com.sanwenyukaochi.security.repository;

import com.sanwenyukaochi.security.entity.Clip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClipRepository extends JpaRepository<Clip, Long> {
    int countByClipGroupId(Long id);
    Page<Clip> findAllByClipGroupId(Long clipGroupId, Pageable pageable);
}