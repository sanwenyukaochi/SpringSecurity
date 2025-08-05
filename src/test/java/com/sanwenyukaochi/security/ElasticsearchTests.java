package com.sanwenyukaochi.security;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.KnnSearch;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanwenyukaochi.security.document.SearchDocument;
import com.sanwenyukaochi.security.elasticsearch.SearchDocumentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
public class ElasticsearchTests {

    @Autowired
    private SearchDocumentRepository searchDocumentRepository;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;
    
    @Autowired
    private ElasticsearchClient esClient;

    // DashScope API配置
    private static final String DASHSCOPE_API_URL = "https://dashscope.aliyuncs.com/compatible-mode/v1/embeddings";
    private static final String DASHSCOPE_API_KEY = "sk-a8358f9902d64f29a6890fb0bb29c6e9";
    private static final String EMBEDDING_MODEL = "text-embedding-v4";
    
    private final WebClient webClient = WebClient.builder()
            .baseUrl(DASHSCOPE_API_URL)
            .defaultHeader("Authorization", "Bearer " + DASHSCOPE_API_KEY)
            .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .build();
    
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 调用DashScope API生成向量
     */
    private float[] generateEmbedding(String text) {
        try {
            Map<String, Object> body = new HashMap<>();
            body.put("model", EMBEDDING_MODEL);
            body.put("input", Collections.singletonList(text));
            body.put("encoding_format", "float");

            Mono<String> responseMono = webClient.post()
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(String.class);

            String response = responseMono.block();
            System.out.println("DashScope API响应: " + response);
            
            // 解析响应获取向量
            JsonNode jsonNode = objectMapper.readTree(response);
            JsonNode embeddingArray = jsonNode.path("data").get(0).path("embedding");
            
            float[] embedding = new float[embeddingArray.size()];
            for (int i = 0; i < embeddingArray.size(); i++) {
                embedding[i] = (float) embeddingArray.get(i).asDouble();
            }
            
            System.out.println("生成向量维度: " + embedding.length);
            return embedding;
            
        } catch (Exception e) {
            System.err.println("生成向量失败: " + e.getMessage());
            e.printStackTrace();
            return new float[1536]; // 返回空向量
        }
    }

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
    
    /**
     * 添加带向量的文档测试
     */
    @Test
    void addDocumentsWithEmbeddings() {
        List<SearchDocument> docs = List.of(
                new SearchDocument(11L, "AI电影推荐.mp4", "人工智能电影推荐", "这是一部关于人工智能和机器学习的科幻电影，探讨了AI对人类社会的影响。"),
                new SearchDocument(12L, "机器学习教程.pdf", "深度学习入门", "详细介绍了神经网络、深度学习的基础概念和实际应用案例。"),
                new SearchDocument(13L, "AI音乐创作.mp3", "AI生成音乐", "使用人工智能技术创作的音乐作品，展现了AI在艺术创作领域的潜力。"),
                new SearchDocument(14L, "智能旅游.pdf", "智能旅游规划", "利用AI技术为用户提供个性化的旅游路线推荐和景点介绍。"),
                new SearchDocument(15L, "AI美食.mp4", "AI美食推荐", "基于用户喜好和营养需求，AI推荐最适合的美食和餐厅。")
        );

        docs.forEach(doc -> {
            try {
                // 生成标题和内容的组合文本向量
                String combinedText = doc.getTitle() + " " + doc.getContent();
                float[] embedding = generateEmbedding(combinedText);
                doc.setEmbedding(embedding);
                
                SearchDocument saved = searchDocumentRepository.save(doc);
                System.out.println("保存带向量的文档: " + saved.getId() + " - " + saved.getTitle());
                System.out.println("向量维度: " + (saved.getEmbedding() != null ? saved.getEmbedding().length : "null"));
            } catch (Exception e) {
                System.err.println("保存文档失败: " + e.getMessage());
            }
        });
    }
    
    /**
     * 为现有文档生成向量
     */
    @Test
    void generateEmbeddingsForExistingDocuments() {
        Iterable<SearchDocument> allDocs = searchDocumentRepository.findAll();
        
        for (SearchDocument doc : allDocs) {
            try {
                if (doc.getEmbedding() == null || doc.getEmbedding().length == 0) {
                    String combinedText = doc.getTitle() + " " + doc.getContent();
                    float[] embedding = generateEmbedding(combinedText);
                    doc.setEmbedding(embedding);
                    
                    searchDocumentRepository.save(doc);
                    System.out.println("为文档生成向量: " + doc.getId() + " - " + doc.getTitle());
                }
            } catch (Exception e) {
                System.err.println("为文档 " + doc.getId() + " 生成向量失败: " + e.getMessage());
            }
        }
    }
    
    /**
     * KNN向量搜索测试
     */
    @Test
    void knnVectorSearch() throws IOException {
        String queryText = "人工智能和机器学习";
        
        // 生成查询向量
        float[] queryVectorArray = generateEmbedding(queryText);
        
        // 将 float[] 转换为 List<Float>
        List<Float> queryVector = new ArrayList<>();
        for (float f : queryVectorArray) {
            queryVector.add(f);
        }
        
        System.out.println("查询文本: " + queryText);
        System.out.println("生成的查询向量维度: " + queryVector.size());
        
        // 构建 KNN 搜索
        KnnSearch knnSearch = new KnnSearch.Builder()
                .field("embedding")
                .queryVector(queryVector)
                .k(5)
                .numCandidates(100)
                .build();
        
        // 执行搜索
        SearchRequest searchRequest = new SearchRequest.Builder()
                .index("search_index")
                .knn(knnSearch)
                .build();
        
        SearchResponse<SearchDocument> response = esClient.search(searchRequest, SearchDocument.class);
        
        System.out.println("KNN搜索结果:");
        response.hits().hits().forEach(hit -> {
            SearchDocument doc = hit.source();
            System.out.println("相似度分数: " + hit.score() + ", 文档: " + doc.getTitle() + " - " + doc.getContent());
        });
    }
    
    /**
     * 混合搜索：结合传统文本搜索和向量搜索
     */
    @Test
    void hybridSearch() throws IOException {
        String queryText = "电影推荐";
        
        // 生成查询向量
        float[] queryVectorArray = generateEmbedding(queryText);
        
        // 将 float[] 转换为 List<Float>
        List<Float> queryVector = new ArrayList<>();
        for (float f : queryVectorArray) {
            queryVector.add(f);
        }
        
        // KNN 向量搜索
        KnnSearch knnSearch = new KnnSearch.Builder()
                .field("embedding")
                .queryVector(queryVector)
                .k(10)
                .numCandidates(100)
                .build();
        
        // 构建混合搜索请求
        SearchRequest searchRequest = new SearchRequest.Builder()
                .index("search_index")
                .knn(knnSearch)
                .query(q -> q
                    .multiMatch(m -> m
                        .query(queryText)
                        .fields("title^2", "content")
                    )
                )
                .build();
        
        SearchResponse<SearchDocument> response = esClient.search(searchRequest, SearchDocument.class);
        
        System.out.println("混合搜索结果 (文本: " + queryText + "):");
        response.hits().hits().forEach(hit -> {
            SearchDocument doc = hit.source();
            System.out.println("综合分数: " + hit.score() + ", 文档: " + doc.getTitle() + " - " + doc.getContent());
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
