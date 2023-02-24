package com.testproject.todoapi.services;

import com.testproject.todoapi.dtos.TaskDTO;

import java.util.List;

public interface TaskService {
    TaskDTO getTaskById(String id);

    void deleteTaskById(String id);

    void updateTask(TaskDTO taskDTO, String taskId);

    void createNewTask(TaskDTO taskDTO);

    List<TaskDTO> getAllTasks(String filterType, String sortType);
}
