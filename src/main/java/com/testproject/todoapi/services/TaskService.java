package com.testproject.todoapi.services;

import com.testproject.todoapi.dtos.TaskDTO;

import java.util.List;

public interface TaskService {

    void createNewTaskWithJWT(TaskDTO taskDTO, String jwt);

    List<TaskDTO> getAllTasksForUserByJWT(String filterType, String sortType, String token);

    void deleteTaskByIdWithJWT(String taskId, String jwt);

    void updateTaskWithJWT(TaskDTO taskDTO, String taskId, String jwt);

    TaskDTO getTaskByIdWithJWT(String taskId, String jwt);
}
