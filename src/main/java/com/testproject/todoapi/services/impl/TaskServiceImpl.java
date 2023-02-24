package com.testproject.todoapi.services.impl;

import com.testproject.todoapi.dtos.TaskDTO;
import com.testproject.todoapi.enums.FilterType;
import com.testproject.todoapi.enums.SortType;
import com.testproject.todoapi.exceptions.WrongFilterTypeException;
import com.testproject.todoapi.exceptions.WrongIdException;
import com.testproject.todoapi.exceptions.WrongSortTypeException;
import com.testproject.todoapi.exceptions.WrongTaskFieldsException;
import com.testproject.todoapi.mappers.TaskMapper;
import com.testproject.todoapi.models.Task;
import com.testproject.todoapi.repositories.TaskRepository;
import com.testproject.todoapi.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public List<TaskDTO> getAllTasks(String filterType, String sortType) {
        List<Task> tasks = null;
        Sort sort = null;
        SortType enumSortType;
        FilterType enumFilterType;
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
            case ALL -> tasks = taskRepository.findAllSorted(sort);
            case TO_DO -> tasks = taskRepository.findAllSortedAndFiltered(false, sort);
            case DONE -> tasks = taskRepository.findAllSortedAndFiltered(true, sort);
        }
        return tasks.stream().map(TaskMapper.INSTANCE::taskToTaskDTO).collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTaskById(String id) {
        Task task = taskRepository.findById(Long.parseLong(id)).orElseThrow(() -> new WrongIdException("No task with such id in db"));
        return TaskMapper.INSTANCE.taskToTaskDTO(task);
    }

    @Override
    public void createNewTask(TaskDTO taskDTO) {
        Task task = TaskMapper.INSTANCE.taskDTOToTask(taskDTO);
        try {
            taskRepository.save(task);
        } catch (Exception e) {
            throw new WrongTaskFieldsException("Something wrong in task`s fields");
        }
    }

    @Override
    public void updateTask(TaskDTO taskDTO, String taskId) {
        Task task = taskRepository.findById(Long.parseLong(taskId)).orElseThrow(() -> new WrongIdException("No task with such id in db"));
        task.setName(taskDTO.getName());
        task.setDone(taskDTO.isDone());
        try {
            taskRepository.save(task);
        } catch (Exception e) {
            throw new WrongTaskFieldsException("Something wrong in task`s fields");
        }
    }

    @Override
    public void deleteTaskById(String id) {
        try {
            taskRepository.deleteById(Long.parseLong(id));
        } catch (Exception e) {
            throw new WrongIdException("Can not delete task under unexisting id");
        }
    }
}
