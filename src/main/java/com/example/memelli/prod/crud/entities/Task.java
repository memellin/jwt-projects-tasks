package com.example.memelli.prod.crud.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.memelli.prod.crud.entities.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tb_task")
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id")
    private User user;

    public Task() {
    }

    public Task(Long id, String description, TaskStatus status, Project project, User user) {
        this.id = id;
        this.description = description;
        setStatus(status);
        this.project = project;
        this.user = user;
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
    @JsonBackReference
    public User getUser() {
        if(user != null) {
            return user;
        }
        return null;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Task other = (Task) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
