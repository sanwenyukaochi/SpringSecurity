package com.sanwenyukaochi.security.dto;

import com.sanwenyukaochi.security.enums.TaskStatus;
import com.sanwenyukaochi.security.enums.TaskType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private Long id;
    private TaskType type;
    private TaskStatus status;
    private Long createdAt;
    private Long updatedAt;
}
