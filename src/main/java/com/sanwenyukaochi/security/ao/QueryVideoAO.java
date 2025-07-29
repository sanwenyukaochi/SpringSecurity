package com.sanwenyukaochi.security.ao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryVideoAO {
    private VideoSortField sortType = VideoSortField.CREATED_AT;
    private Sort.Direction order = Sort.Direction.DESC;
    private Boolean hasClips;
    private String videoName;

    @Getter
    @AllArgsConstructor
    public enum VideoSortField {
        CREATED_AT("创建时间", "createdAt"),
        FILE_NAME("文件名称", "fileName"),
        FILE_SIZE("文件大小", "fileSize");
        private final String label;
        private final String fieldName;
    }
}
