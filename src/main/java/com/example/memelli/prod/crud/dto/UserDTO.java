package com.example.memelli.prod.crud.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.example.memelli.prod.crud.entities.Task;
import com.example.memelli.prod.crud.entities.User;

public class UserDTO implements Serializable{ // CLASSE PARA TRANSITAR NO JSON OS DADOS DO USUARIO E SUAS PERMISSOES

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message= "Campo obrigatório")
    private String name;
    private String surname;
    
    @Email(message= "Email deve ser um valor válido")
    private String email;

    private Set<RoleDTO> roles = new HashSet<>();

    private List<TaskDTO> tasks = new ArrayList<>();

    private Long projectId;


    public UserDTO() {
    }

    public UserDTO(Long id, String name, String surname, String email, Long projectId) {
        super();
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.projectId = projectId;
    }

    public UserDTO(User entity) {
        id = entity.getId();
        name = entity.getName();
        surname = entity.getSurname();
        email = entity.getEmail();
        entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
        projectId = entity.getProject().getId();
    }

    public UserDTO(User entity, Set<Task> tasks) { //salvo as tasks em um task por segurança
        this(entity);
        tasks.forEach(t -> this.tasks.add(new TaskDTO(t))); 
    }

    public UserDTO(User entity, List<TaskDTO> tasks) { //salvo as tasks em um task por segurança
        this(entity);
        tasks.forEach(t -> this.tasks.add(t)); 
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    
}
