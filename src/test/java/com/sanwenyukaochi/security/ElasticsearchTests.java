package com.sanwenyukaochi.security;

import com.sanwenyukaochi.security.document.SearchDocument;
import com.sanwenyukaochi.security.elasticsearch.SearchDocumentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ElasticsearchTests {

    @Autowired
    private SearchDocumentRepository searchDocumentRepository;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Test
    void add1() {
        for (long i = 2; i < 11; i++) {
            SearchDocument doc = new SearchDocument();
            doc.setId(i);
            doc.setFileName("文件_" + i + ".mp4");
            doc.setTitle("标题示例 " + i);
            doc.setContent("这是文档 " + i + " 的内容描述，包含一些关键字，例如电影、音乐、旅游等。");

            SearchDocument saved = searchDocumentRepository.save(doc);
            System.out.println("saved = " + saved);
        }
    }

    @Test
    void add2() {
        List<SearchDocument> docs = List.of(
                new SearchDocument(1L, "电影推荐.mp4", "经典电影推荐", "这是一部关于爱情和冒险的经典电影，故事情节紧凑，演员表演精彩。"),
                new SearchDocument(2L, "旅游攻略.pdf", "日本旅游攻略", "详细介绍了日本东京、大阪和京都的旅游景点、美食推荐和交通指南。"),
                new SearchDocument(3L, "音乐专辑.mp3", "流行音乐精选", "收录了近年来最受欢迎的流行音乐，节奏欢快，旋律动听。"),
                new SearchDocument(4L, "美食视频.mp4", "美食制作教程", "介绍了多款家常美食的制作方法，包括川菜、粤菜和西餐。"),
                new SearchDocument(5L, "电影幕后花絮.mov", "电影幕后揭秘", "揭示电影拍摄过程中有趣的故事和幕后制作细节。"),
                new SearchDocument(6L, "旅游日记.docx", "法国旅游体验", "记录了法国巴黎、尼斯等地的旅游体验和文化感受。"),
                new SearchDocument(7L, "音乐会现场.flac", "古典音乐会", "录制了著名交响乐团的现场演出，包含贝多芬和莫扎特的经典作品。"),
                new SearchDocument(8L, "美食探店.mp4", "北京美食探店", "带你走进北京最有特色的小吃店，品尝地道美味。"),
                new SearchDocument(9L, "电影评论.txt", "新片电影评论", "对近期上映的新片进行详细点评，包括剧情、演技和特效。"),
                new SearchDocument(10L, "旅游指南.pdf", "东南亚旅游指南", "介绍东南亚多个国家的旅游推荐路线和注意事项。")
        );

        docs.forEach(doc -> {
            SearchDocument saved = searchDocumentRepository.save(doc);
            System.out.println("saved = " + saved);
        });
    }
    
    @Test
    void delete() {
        Long id = 1L;
        searchDocumentRepository.deleteById(id);
    }

    @Test
    void update() {
        SearchDocument updateDoc = new SearchDocument();
        updateDoc.setId(2L);
        updateDoc.setFileName("更新后的文件_2.mp4");
        updateDoc.setTitle("更新后的标题 2");
        updateDoc.setContent("这是更新后的文档 2 内容，包含电影、音乐和旅游的相关信息。");

        SearchDocument saved = searchDocumentRepository.save(updateDoc);
        System.out.println("saved = " + saved);
    }

    @Test
    void findAll() {
        Iterable<SearchDocument> all = searchDocumentRepository.findAll();
        List<SearchDocument> list = new ArrayList<>();
        for (SearchDocument doc : all) {
            System.out.println("doc = " + doc);
            list.add(doc);
        }
        System.out.println("==================");
    }

    @Test
    void findById() {
        Long id = 4L;
        Optional<SearchDocument> byId = searchDocumentRepository.findById(id);
        System.out.println("byId = " + byId);
    }

    @Test
    void findByIdAll() {
        List<Long> ids = List.of(3L, 5L, 4L, 66L);
        Iterable<SearchDocument> docs = searchDocumentRepository.findAllById(ids);
        for (SearchDocument doc : docs) {
            System.out.println("doc = " + doc);
        }
    }

    @Test
    void findByTitleLike() {
        Page<SearchDocument> page = searchDocumentRepository.findByTitleContaining("示例", PageRequest.of(0, 100));
        System.out.println("findByTitleContaining = " + page.getContent());
    }

    @Test
    void semanticSearchUsingRepository() {
        String keyword = "电影";

        Page<SearchDocument> results = searchDocumentRepository.findByTitleContainingOrContentContaining(keyword, keyword, PageRequest.of(0, 10));

        System.out.println("语义搜索结果（基于 Repository）:");
        results.forEach(System.out::println);
    }
}
