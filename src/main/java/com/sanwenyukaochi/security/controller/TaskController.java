package com.sanwenyukaochi.security.controller;

import com.sanwenyukaochi.security.ao.TaskQueryAO;
import com.sanwenyukaochi.security.dto.TaskDTO;
import com.sanwenyukaochi.security.entity.Task;
import com.sanwenyukaochi.security.service.TaskService;
import com.sanwenyukaochi.security.vo.TaskVO;
import com.sanwenyukaochi.security.vo.page.PageVO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    
    @MessageMapping("/task/query")
    @SendToUser("/queue/task/result")
    @Operation(summary = "查询任务列表")
    public PageVO<TaskVO> queryTasks(@Valid @Payload TaskQueryAO taskQueryAO) {
        Pageable pageable = PageRequest.of(taskQueryAO.getCurrentPage(), taskQueryAO.getSize());
        Page<TaskDTO> taskPage = taskService.findAllTasks(pageable);
        return PageVO.from(taskPage.map(task -> new TaskVO(
                task.getId(),
                task.getType(),
                task.getStatus(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        )));
    }
    
    @MessageMapping("/task/query/byId")
    @SendToUser("/queue/task/result")
    @Operation(summary = "根据ID查询任务")
    public Task queryTaskById(@Valid @Payload TaskQueryAO taskQueryAO) {
        return taskService.findTaskById(taskQueryAO.getTaskId())
                .orElse(null);
    }
}
