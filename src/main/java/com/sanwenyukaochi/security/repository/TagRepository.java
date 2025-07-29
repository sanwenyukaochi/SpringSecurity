package com.sanwenyukaochi.security.repository;

import com.sanwenyukaochi.security.entity.Tag;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByNameAndType(String name, String type);
    @EntityGraph(attributePaths = {"clipTags"})
    int countByClipTags_TagId(Long tagId);
}