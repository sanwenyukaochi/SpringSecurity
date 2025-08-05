package com.sanwenyukaochi.security.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "search_index")
public class SearchDocument {
    
    @Id
    private Long id;
    
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String fileName;
    
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String title;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String content;
    
    // 添加向量字段，用于KNN搜索
    @Field(type = FieldType.Dense_Vector, dims = 1024)
    private float[] embedding;
    
    // 构造函数（不包含embedding）
    public SearchDocument(Long id, String fileName, String title, String content) {
        this.id = id;
        this.fileName = fileName;
        this.title = title;
        this.content = content;
    }
}