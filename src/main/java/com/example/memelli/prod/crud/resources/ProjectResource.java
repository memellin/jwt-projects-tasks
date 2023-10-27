package com.example.memelli.prod.crud.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.memelli.prod.crud.entities.Project;
import com.example.memelli.prod.crud.services.ProjectService;

@RestController
@RequestMapping(value = "/projects")
public class ProjectResource {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<Project>> findAll() {
        List<Project> list = projectService.findAll();
        return ResponseEntity.ok().body(list);
    }
}
