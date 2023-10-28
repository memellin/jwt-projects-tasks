package com.example.memelli.prod.crud.dto;

import java.io.Serializable;

import com.example.memelli.prod.crud.entities.Task;
import com.example.memelli.prod.crud.entities.enums.TaskStatus;

public class TaskDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String description;
    private Integer status;

    public TaskDTO() {
    }

    public TaskDTO(Long id, String description, TaskStatus status) {
        this.id = id;
        this.description = description;
        setStatus(status);
    }

    public TaskDTO(Task entity) {
        this.id = entity.getId();
        this.description = entity.getDescription();
        setStatus(entity.getStatus());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        if (status == null) {
            return TaskStatus.valueofStatus(1);
        }
        return TaskStatus.valueofStatus(status);
    }

    public void setStatus(TaskStatus status) {
        this.status = status.getCode();
    }

}
