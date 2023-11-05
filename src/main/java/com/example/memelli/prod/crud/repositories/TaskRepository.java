package com.example.memelli.prod.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.memelli.prod.crud.entities.Project;
import com.example.memelli.prod.crud.entities.Task;
import com.example.memelli.prod.crud.entities.User;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT obj FROM Task obj WHERE obj.project = :project")
    java.util.List<Task> findByProjectId(Project project);
    
    @Query("SELECT obj FROM Task obj WHERE obj.user = :user")
    java.util.List<Task> findByUserId(User user);
}