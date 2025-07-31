package com.sanwenyukaochi.security.ao;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskQueryAO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long taskId;
    private String status;
    private String type;
    private Integer currentPage = 0;
    private Integer size = 6;
}