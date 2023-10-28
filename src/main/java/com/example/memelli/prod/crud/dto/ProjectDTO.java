package com.example.memelli.prod.crud.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.example.memelli.prod.crud.entities.Project;
import com.example.memelli.prod.crud.entities.enums.ProjectStatus;

public class ProjectDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer status;

    public ProjectDTO() {
    }

    public ProjectDTO(Long id, String name, String description, LocalDateTime startDate, LocalDateTime endDate,
            ProjectStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        setStatus(status);
    }

    public ProjectDTO(Project entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        setStatus(entity.getStatus());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public ProjectStatus getStatus() {
        if (status == null) {
            return ProjectStatus.valueofStatus(1);
        }
        return ProjectStatus.valueofStatus(status);
    }

    public void setStatus(ProjectStatus status) {
        this.status = status.getCode();
    }

}