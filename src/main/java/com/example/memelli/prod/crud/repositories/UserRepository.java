package com.example.memelli.prod.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.memelli.prod.crud.entities.Project;
import com.example.memelli.prod.crud.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query("SELECT obj FROM User obj WHERE obj.project = :project")
    java.util.List<User> findByProjectId(Project project);
}