package com.sanwenyukaochi.security.service;

import com.sanwenyukaochi.security.annotation.DataScope;
import com.sanwenyukaochi.security.dto.TaskDTO;
import com.sanwenyukaochi.security.entity.Task;
import com.sanwenyukaochi.security.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    
    @DataScope
    public Page<TaskDTO> findAllTasks(Pageable pageable) {
        Page<Task> all = taskRepository.findAll(pageable);
        return all.map(task -> new TaskDTO(
                task.getId(),
                task.getType(),
                task.getStatus(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        ));
    }
    
    public Optional<Task> findTaskById(Long id) {
        return taskRepository.findById(id);
    }
}