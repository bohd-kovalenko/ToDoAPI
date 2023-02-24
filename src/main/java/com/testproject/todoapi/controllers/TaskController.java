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
            , @RequestParam(value = "sort_type", required = false) String sortType) {
        List<TaskDTO> allTasks = taskService.getAllTasks(filterType, sortType);
        return ResponseEntity.ok(allTasks);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable("id") String taskId) {
        TaskDTO taskDTO = taskService.getTaskById(taskId);
        return ResponseEntity.ok(taskDTO);
    }

    @PostMapping(value = "", consumes = "application/json")
    public ResponseEntity<Void> addTask(@RequestBody TaskDTO taskDTO) {
        taskService.createNewTask(taskDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<Void> updateTask(@PathVariable("id") String taskId, @RequestBody TaskDTO taskDTO) {
        taskService.updateTask(taskDTO, taskId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") String taskId) {
        taskService.deleteTaskById(taskId);
        return ResponseEntity.ok().build();
    }
}
