package com.sanwenyukaochi.security.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.sanwenyukaochi.security.enums.TaskStatus;
import com.sanwenyukaochi.security.enums.TaskType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private TaskType type;
    private TaskStatus status;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createdAt;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long updatedAt;
}
