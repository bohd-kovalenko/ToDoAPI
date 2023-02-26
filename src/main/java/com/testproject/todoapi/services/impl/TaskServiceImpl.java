package com.testproject.todoapi.services.impl;

import com.testproject.todoapi.dtos.TaskDTO;
import com.testproject.todoapi.enums.FilterType;
import com.testproject.todoapi.enums.SortType;
import com.testproject.todoapi.exceptions.*;
import com.testproject.todoapi.mappers.TaskMapper;
import com.testproject.todoapi.models.Task;
import com.testproject.todoapi.models.User;
import com.testproject.todoapi.repositories.TaskRepository;
import com.testproject.todoapi.services.JwtService;
import com.testproject.todoapi.services.TaskService;
import com.testproject.todoapi.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;
    private final JwtService jwtService;

    @Override
    public List<TaskDTO> getAllTasksForUserByJWT(String filterType, String sortType, String token) {
        List<Task> tasks = null;
        Sort sort = null;
        SortType enumSortType;
        FilterType enumFilterType;
        User user = userService.getUserByUsername(jwtService.extractUsernameFromJWT(token));
        try {
            enumSortType = SortType.valueOf(sortType);
        } catch (IllegalArgumentException e) {
            throw new WrongSortTypeException("Wrong sort type was given");
        } catch (NullPointerException e) {
            enumSortType = SortType.CREATION_DATE_TIME; // DEFAULT SORT TYPE
        }
        try {
            enumFilterType = FilterType.valueOf(filterType);
        } catch (IllegalArgumentException e) {
            throw new WrongFilterTypeException("Wrong filter was given");
        } catch (NullPointerException e) {
            enumFilterType = FilterType.ALL; // DEFAULT FILTER TYPE
        }
        switch (enumSortType) {
            case CREATION_DATE_TIME -> sort = Sort.by("creationDate").descending();
            case ALPHABETICAL -> sort = Sort.by("name").ascending();
        }
        switch (enumFilterType) {
            case ALL -> tasks = taskRepository.findAllSortedByUserId(sort, String.valueOf(user.getId()));
            case TO_DO ->
                    tasks = taskRepository.findAllSortedAndFilteredByUserId(false, sort, String.valueOf(user.getId()));
            case DONE ->
                    tasks = taskRepository.findAllSortedAndFilteredByUserId(true, sort, String.valueOf(user.getId()));
        }
        return tasks.stream().map(TaskMapper.INSTANCE::taskToTaskDTO).collect(Collectors.toList());
    }


    @Override
    public void createNewTaskWithJWT(TaskDTO taskDTO, String jwt) {
        User user = userService.getUserByUsername(jwtService.extractUsernameFromJWT(jwt));
        Task task = TaskMapper.INSTANCE.taskDTOToTask(taskDTO);
        task.setUser(user);
        try {
            taskRepository.save(task);
        } catch (Exception e) {
            throw new WrongTaskFieldsException("Something wrong in task`s fields");
        }
    }

    @Override
    public void deleteTaskByIdWithJWT(String taskId, String jwt) {
        checkIfTaskBelongsToJWT(taskId, jwt);
        deleteTaskById(taskId);
    }

    @Override
    public void updateTaskWithJWT(TaskDTO taskDTO, String taskId, String jwt) {
        checkIfTaskBelongsToJWT(taskId, jwt);
        updateTask(taskDTO, taskId);
    }

    @Override
    public TaskDTO getTaskByIdWithJWT(String taskId, String jwt) {
        checkIfTaskBelongsToJWT(taskId, jwt);
        return getTaskById(taskId);
    }

    private void checkIfTaskBelongsToJWT(String taskId, String jwt) {
        Task task = taskRepository.findById(Long.parseLong(taskId))
                .orElseThrow(() -> new WrongIdException("Task with this id does not exist"));
        User user = userService.getUserByUsername(jwtService.extractUsernameFromJWT(jwt));
        if (task.getUser().getId() != user.getId()) {
            throw new WrongJWTToken("Task owner not match one, granted in JWT");
        }
    }

    private TaskDTO getTaskById(String taskId) {
        Task task = taskRepository.findById(Long.parseLong(taskId))
                .orElseThrow(() -> new WrongIdException("No task with such id in db"));
        return TaskMapper.INSTANCE.taskToTaskDTO(task);
    }

    private void updateTask(TaskDTO taskDTO, String taskId) {
        Task task = taskRepository.findById(Long.parseLong(taskId)).orElseThrow(() -> new WrongIdException("No task with such id in db"));
        task.setName(taskDTO.getName());
        task.setDone(taskDTO.isDone());
        try {
            taskRepository.save(task);
        } catch (Exception e) {
            throw new WrongTaskFieldsException("Something wrong in task`s fields");
        }
    }

    private void deleteTaskById(String id) {
        try {
            taskRepository.deleteById(Long.parseLong(id));
        } catch (Exception e) {
            throw new WrongIdException("Can not delete task under unexisting id");
        }
    }
}
