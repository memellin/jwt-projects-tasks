package com.example.memelli.prod.crud.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.example.memelli.prod.crud.entities.Project;
import com.example.memelli.prod.crud.entities.Task;
import com.example.memelli.prod.crud.entities.User;
import com.example.memelli.prod.crud.entities.enums.ProjectStatus;

public class ProjectDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @Size(min = 5, max = 60, message = "O nome deve ter entre 5 e 60 caracteres")
    @NotBlank(message = "Campo obrigatório")
    private String name;
    private String description;
    @NotBlank(message = "Campo obrigatório")
    private LocalDateTime startDate; // o projeto pode se iniciar a qualquer momento
    @PastOrPresent(message = "A data não pode ser futura")
    @NotBlank(message = "Campo obrigatório")
    private LocalDateTime endDate;
    private Integer status;

    private List<TaskDTO> tasks = new ArrayList<>();

    private List<UserDTO> users = new ArrayList<>();

    public ProjectDTO() {
    }

    public ProjectDTO(Long id, String name, String description, LocalDateTime startDate, LocalDateTime endDate,
            ProjectStatus status) {
        super();
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

    public ProjectDTO(Project entity, Set<User> users, Set<Task> tasks) { // salvo as tasks em um task por segurança
        this(entity);
        users.forEach(u -> this.users.add(new UserDTO(u)));
        tasks.forEach(t -> this.tasks.add(new TaskDTO(t)));
    }

    public ProjectDTO(Project entity, List<UserDTO> users, List<TaskDTO> tasks) { // listo os tasks em um taskDTO
        this(entity);
        users.forEach(u -> this.users.add((u)));
        tasks.forEach(t -> this.tasks.add((t)));

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

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public List<UserDTO> getUsers() {
        return users;
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
