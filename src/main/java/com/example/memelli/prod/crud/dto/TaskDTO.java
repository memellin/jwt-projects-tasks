package com.example.memelli.prod.crud.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.example.memelli.prod.crud.entities.Task;
import com.example.memelli.prod.crud.entities.enums.TaskStatus;

public class TaskDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotBlank(message = "Campo obrigatório")
    private String description;
    private Integer status;

    private Long projectId;

    private Long userId;

    public TaskDTO() {
    }

    public TaskDTO(Long id, String description, TaskStatus status, Long projectId, Long userId) {
        super();
        this.id = id;
        this.description = description;
        setStatus(status);
        this.projectId = projectId;
        this.userId = userId;
    }

    public TaskDTO(Task entity) {
        super();
        this.id = entity.getId();
        this.description = entity.getDescription();
        setStatus(entity.getStatus());
        this.projectId = entity.getProject().getId();
        this.userId = entity.getUser().getId();
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

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getUserId() {
        if(userId != null) {
            return userId;
        }
        return null;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

   
}
