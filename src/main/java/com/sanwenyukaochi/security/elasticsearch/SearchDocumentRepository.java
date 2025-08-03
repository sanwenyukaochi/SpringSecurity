package com.sanwenyukaochi.security.elasticsearch;

import com.sanwenyukaochi.security.document.SearchDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchDocumentRepository extends ElasticsearchRepository<SearchDocument, Long> {
    Page<SearchDocument> findByTitleContaining(String number, Pageable pageable);

    Page<SearchDocument> findByTitleContainingOrContentContaining(String keyword, String keyword1, Pageable pageable);
}