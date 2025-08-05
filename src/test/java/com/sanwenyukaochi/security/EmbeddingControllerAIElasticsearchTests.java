package com.sanwenyukaochi.security;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import com.sanwenyukaochi.security.document.SearchDocument;
import com.sanwenyukaochi.security.elasticsearch.SearchDocumentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 基于智谱AI的Elasticsearch向量搜索测试类
 * 使用Spring AI集成智谱AI的embedding模型进行向量生成和KNN搜索
 */
@SpringBootTest
public class EmbeddingControllerAIElasticsearchTests {

    @Autowired
    private SearchDocumentRepository searchDocumentRepository;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;
    
    @Autowired
    private ElasticsearchClient esClient;
    
    @Autowired
    private EmbeddingModel embeddingModel;
    
    /**
     * 使用智谱AI生成文本向量
     */
    private float[] generateEmbedding(String text) {
        try {
            EmbeddingRequest request = new EmbeddingRequest(List.of(text), null);
            EmbeddingResponse response = embeddingModel.call(request);
            
            if (response.getResults().isEmpty()) {
                throw new RuntimeException("生成向量失败：响应为空");
            }
            
            float[] embeddingArray = response.getResults().getFirst().getOutput();
            List<Float> embedding = new ArrayList<>();
            for (float f : embeddingArray) {
                embedding.add(f);
            }
            float[] result = new float[embedding.size()];
            for (int i = 0; i < embedding.size(); i++) {
                result[i] = embedding.get(i).floatValue();
            }
            
            
            System.out.println("生成向量成功，文本: " + text + ", 向量维度: " + result.length);
            return result;
        } catch (Exception e) {
            System.err.println("生成向量失败: " + e.getMessage());
            throw new RuntimeException("向量生成失败", e);
        }
    }

    /**
     * 添加带智谱AI生成向量的文档
     */
    @Test
    void addDocumentsWithZhipuAIEmbeddings() {
        List<SearchDocument> docs = List.of(
                new SearchDocument(21L, "AI技术发展.pdf", "人工智能技术发展趋势", "深入分析人工智能、机器学习、深度学习等前沿技术的发展现状和未来趋势。"),
                new SearchDocument(22L, "智能编程.mp4", "智能编程助手应用", "介绍AI编程助手如何提高开发效率，包括代码生成、调试和优化等功能。"),
                new SearchDocument(23L, "自然语言处理.docx", "NLP技术应用", "探讨自然语言处理技术在智能客服、文本分析、机器翻译等领域的应用。"),
                new SearchDocument(24L, "计算机视觉.pdf", "计算机视觉算法", "详细介绍图像识别、目标检测、人脸识别等计算机视觉核心算法。"),
                new SearchDocument(25L, "大数据分析.xlsx", "大数据处理技术", "大数据存储、处理和分析技术，包括Hadoop、Spark等分布式计算框架。")
        );

        docs.forEach(doc -> {
            try {
                // 使用智谱AI生成向量
                String combinedText = doc.getTitle() + " " + doc.getContent();
                float[] embedding = generateEmbedding(combinedText);
                doc.setEmbedding(embedding);
                
                SearchDocument saved = searchDocumentRepository.save(doc);
                System.out.println("保存成功: " + saved.getTitle() + ", 向量维度: " + embedding.length);
            } catch (Exception e) {
                System.err.println("保存文档失败: " + doc.getTitle() + ", 错误: " + e.getMessage());
            }
        });
    }

    /**
     * 为现有文档生成智谱AI向量
     */
    @Test
    void generateZhipuAIEmbeddingsForExistingDocuments() {
        Iterable<SearchDocument> allDocs = searchDocumentRepository.findAll();
        
        for (SearchDocument doc : allDocs) {
            try {
                if (doc.getEmbedding() == null || doc.getEmbedding().length == 0) {
                    String combinedText = doc.getTitle() + " " + doc.getContent();
                    float[] embedding = generateEmbedding(combinedText);
                    doc.setEmbedding(embedding);
                    
                    SearchDocument updated = searchDocumentRepository.save(doc);
                    System.out.println("更新向量成功: " + updated.getTitle());
                } else {
                    System.out.println("文档已有向量，跳过: " + doc.getTitle());
                }
            } catch (Exception e) {
                System.err.println("更新文档向量失败: " + doc.getTitle() + ", 错误: " + e.getMessage());
            }
        }
    }

    /**
     * 基于智谱AI向量的KNN搜索
     */
    @Test
    void zhipuAIKnnVectorSearch() throws IOException {
        String queryText = "人工智能和机器学习技术";
        
        // 使用智谱AI生成查询向量
        float[] queryVectorArray = generateEmbedding(queryText);
        System.out.println("查询文本: " + queryText + ", 查询向量维度: " + queryVectorArray.length);
        
        // 转换为List<Float>以适配Elasticsearch Java客户端
        List<Float> queryVector = new ArrayList<>();
        for (float value : queryVectorArray) {
            queryVector.add(value);
        }
        
        // 构建KNN搜索请求
        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index("search_index")
                .knn(k -> k
                        .field("embedding")
                        .queryVector(queryVector)
                        .k(5)
                        .numCandidates(100)
                )
                .source(src -> src.filter(f -> f
                        .includes("id", "title", "content", "fileName")
                ))
        );
        
        SearchResponse<SearchDocument> response = esClient.search(searchRequest, SearchDocument.class);
        HitsMetadata<SearchDocument> hits = response.hits();
        
        System.out.println("\n=== 智谱AI KNN向量搜索结果 ===");
        System.out.println("总命中数: " + hits.total().value());
        
        for (Hit<SearchDocument> hit : hits.hits()) {
            SearchDocument doc = hit.source();
            System.out.println(String.format("相似度: %.4f, 标题: %s, 内容: %s", 
                    hit.score(), doc.getTitle(), doc.getContent().substring(0, Math.min(50, doc.getContent().length())) + "..."));
        }
    }

    /**
     * 混合搜索：结合传统文本搜索和智谱AI向量搜索
     */
    @Test
    void zhipuAIHybridSearch() throws IOException {
        String queryText = "编程开发技术";
        
        // 生成查询向量
        float[] queryVectorArray = generateEmbedding(queryText);
        List<Float> queryVector = new ArrayList<>();
        for (float value : queryVectorArray) {
            queryVector.add(value);
        }
        
        // 构建混合搜索：文本搜索 + 向量搜索
        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index("search_index")
                .query(q -> q
                        .bool(b -> b
                                .should(sh -> sh
                                        .multiMatch(mm -> mm
                                                .query(queryText)
                                                .fields("title^2", "content")
                                        )
                                )
                        )
                )
                .knn(k -> k
                        .field("embedding")
                        .queryVector(queryVector)
                        .k(3)
                        .numCandidates(50)
                        .boost(0.5f)  // 向量搜索权重
                )
                .source(src -> src.filter(f -> f
                        .includes("id", "title", "content", "fileName")
                ))
        );
        
        SearchResponse<SearchDocument> response = esClient.search(searchRequest, SearchDocument.class);
        HitsMetadata<SearchDocument> hits = response.hits();
        
        System.out.println("\n=== 智谱AI混合搜索结果 ===");
        System.out.println("查询文本: " + queryText);
        System.out.println("总命中数: " + hits.total().value());
        
        for (Hit<SearchDocument> hit : hits.hits()) {
            SearchDocument doc = hit.source();
            System.out.println(String.format("综合得分: %.4f, 标题: %s, 内容: %s", 
                    hit.score(), doc.getTitle(), doc.getContent().substring(0, Math.min(50, doc.getContent().length())) + "..."));
        }
    }

    /**
     * 测试智谱AI向量生成功能
     */
    @Test
    void testZhipuAIEmbeddingGeneration() {
        List<String> testTexts = List.of(
                "人工智能技术发展",
                "机器学习算法应用",
                "自然语言处理技术",
                "计算机视觉识别",
                "大数据分析处理"
        );
        
        System.out.println("=== 智谱AI向量生成测试 ===");
        for (String text : testTexts) {
            try {
                float[] embedding = generateEmbedding(text);
                System.out.println(String.format("文本: %s, 向量维度: %d, 前5个值: [%.4f, %.4f, %.4f, %.4f, %.4f]", 
                        text, embedding.length, 
                        embedding[0], embedding[1], embedding[2], embedding[3], embedding[4]));
            } catch (Exception e) {
                System.err.println("生成向量失败: " + text + ", 错误: " + e.getMessage());
            }
        }
    }

    /**
     * 查找相似文档
     */
    @Test
    void findSimilarDocuments() throws IOException {
        // 选择一个已存在的文档作为基准
        Optional<SearchDocument> baseDoc = searchDocumentRepository.findById(21L);
        if (baseDoc.isEmpty()) {
            System.out.println("基准文档不存在，请先运行 addDocumentsWithZhipuAIEmbeddings 测试");
            return;
        }
        
        SearchDocument doc = baseDoc.get();
        if (doc.getEmbedding() == null || doc.getEmbedding().length == 0) {
            System.out.println("基准文档没有向量，请先生成向量");
            return;
        }
        
        // 使用文档的向量进行相似搜索
        List<Float> queryVector = new ArrayList<>();
        for (float value : doc.getEmbedding()) {
            queryVector.add(value);
        }
        
        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index("search_index")
                .knn(k -> k
                        .field("embedding")
                        .queryVector(queryVector)
                        .k(5)
                        .numCandidates(100)
                )
                .query(q -> q
                        .bool(b -> b
                                .mustNot(mn -> mn
                                        .term(t -> t
                                                .field("id")
                                                .value(doc.getId())
                                        )
                                )
                        )
                )
        );
        
        SearchResponse<SearchDocument> response = esClient.search(searchRequest, SearchDocument.class);
        
        System.out.println("\n=== 相似文档搜索结果 ===");
        System.out.println("基准文档: " + doc.getTitle());
        System.out.println("找到 " + response.hits().hits().size() + " 个相似文档:");
        
        for (Hit<SearchDocument> hit : response.hits().hits()) {
            SearchDocument similarDoc = hit.source();
            System.out.println(String.format("相似度: %.4f, 标题: %s", 
                    hit.score(), similarDoc.getTitle()));
        }
    }

    /**
     * 清理测试数据
     */
    @Test
    void cleanupTestData() {
        List<Long> testIds = List.of(21L, 22L, 23L, 24L, 25L);
        for (Long id : testIds) {
            try {
                searchDocumentRepository.deleteById(id);
                System.out.println("删除文档 ID: " + id);
            } catch (Exception e) {
                System.err.println("删除文档失败 ID: " + id + ", 错误: " + e.getMessage());
            }
        }
    }
}