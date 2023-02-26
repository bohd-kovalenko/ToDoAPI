package com.testproject.todoapi.controllers;

import com.testproject.todoapi.dtos.TaskDTO;
import com.testproject.todoapi.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping(value = "")
    public ResponseEntity<List<TaskDTO>> getAllTasks(@RequestParam(value = "filter_type", required = false) String filterType
            , @RequestParam(value = "sort_type", required = false) String sortType
            , @RequestHeader(name = "Authorization") String jwt) {
        List<TaskDTO> allTasks = taskService.getAllTasksForUserByJWT(filterType, sortType, jwt.substring(7));
        return ResponseEntity.ok(allTasks);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable("id") String taskId
            , @RequestHeader(name = "Authorization") String jwt) {
        TaskDTO taskDTO = taskService.getTaskByIdWithJWT(taskId, jwt.substring(7));
        return ResponseEntity.ok(taskDTO);
    }

    @PostMapping(value = "", consumes = "application/json")
    public ResponseEntity<Void> addTask(@RequestBody TaskDTO taskDTO
            , @RequestHeader(name = "Authorization") String jwt) {
        taskService.createNewTaskWithJWT(taskDTO, jwt.substring(7));
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<Void> updateTask(@PathVariable("id") String taskId
            , @RequestBody TaskDTO taskDTO
            , @RequestHeader(name = "Authorization") String jwt) {
        taskService.updateTaskWithJWT(taskDTO, taskId, jwt.substring(7));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") String taskId
            , @RequestHeader("Authorization") String jwt) {
        taskService.deleteTaskByIdWithJWT(taskId, jwt.substring(7));
        return ResponseEntity.ok().build();
    }
}
