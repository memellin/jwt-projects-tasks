package com.example.memelli.prod.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.memelli.prod.crud.entities.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
