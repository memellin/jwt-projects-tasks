package com.example.memelli.prod.crud.services;


import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.memelli.prod.crud.dto.ProjectDTO;
import com.example.memelli.prod.crud.dto.TaskDTO;
import com.example.memelli.prod.crud.entities.Project;
import com.example.memelli.prod.crud.repositories.ProjectRepository;
import com.example.memelli.prod.crud.services.exceptions.DataBaseException;
import com.example.memelli.prod.crud.services.exceptions.ResourceNotFoundException;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskService taskService;

   
    @Transactional(readOnly = true)
    public Page<ProjectDTO> findAllPaged(PageRequest pageable) {
        Page<Project> list = projectRepository.findAll(pageable);
        return list.map(x -> new ProjectDTO(x));
    }

    @Transactional(readOnly = true)
    public ProjectDTO findById(Long id) {
        Optional<Project> obj = projectRepository.findById(id);
        Project entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        List<TaskDTO> tasks = taskService.findbyProjectId(entity);
        return new ProjectDTO(entity, tasks);
    }

    @Transactional
    public ProjectDTO insert(ProjectDTO dto) {
        Project entity = new Project();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setStatus(dto.getStatus());
        entity = projectRepository.save(entity);
        return new ProjectDTO(entity);
    }

     @Transactional
    public ProjectDTO update(Long id, ProjectDTO dto) {
        try{
        Project entity = projectRepository.getById(id);
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setStatus(dto.getStatus());
        entity = projectRepository.save(entity);
        return new ProjectDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    public void delete(long id) {
        try{
        projectRepository.deleteById(id);
        } 
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
        catch (DataIntegrityViolationException e) {  // um projeto com varias tasksk nao pode ser deletado
            throw new DataBaseException("Integrity violation");
        }
    }
}
