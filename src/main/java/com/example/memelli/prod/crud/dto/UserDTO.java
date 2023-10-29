package com.example.memelli.prod.crud.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.example.memelli.prod.crud.entities.User;

public class UserDTO implements Serializable{ // TRANSITAR NO JSON OS DADOS DO USUARIO E SUAS PERMISSOES

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String surname;
    private String email;

    Set<RoleDTO> roles = new HashSet<>();

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String surname, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public UserDTO(User entity) {
        id = entity.getId();
        name = entity.getName();
        surname = entity.getSurname();
        email = entity.getEmail();
        entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
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

    
}
