package com.sanwenyukaochi.security.repository;

import com.sanwenyukaochi.security.entity.Clip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClipRepository extends JpaRepository<Clip, Long> {
    // 继承JpaRepository已经提供了saveAll方法用于批量保存
}