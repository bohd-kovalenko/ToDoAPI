package com.testproject.todoapi.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TaskDTO {
    private String name;
    private boolean isDone;

    @JsonCreator
    public TaskDTO(@JsonProperty(value = "name") String name, @JsonProperty(value = "is_done") boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }
}
