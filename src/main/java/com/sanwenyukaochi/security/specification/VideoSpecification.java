package com.sanwenyukaochi.security.specification;

import com.sanwenyukaochi.security.entity.Video;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


public class VideoSpecification {
    public static Specification<Video> buildSpecification(Boolean hasClips, String videoName) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (hasClips != null) predicates.add(cb.equal(root.get("hasClips"), hasClips));
            if (StringUtils.hasText(videoName)) predicates.add(cb.like(cb.lower(root.get("fileName")), "%" + videoName.trim().toLowerCase() + "%"));
            return predicates.isEmpty() ? cb.conjunction() : cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}