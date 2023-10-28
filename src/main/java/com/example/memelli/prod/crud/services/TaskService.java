package com.example.memelli.prod.crud.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.memelli.prod.crud.dto.TaskDTO;
import com.example.memelli.prod.crud.entities.Project;
import com.example.memelli.prod.crud.entities.Task;
import com.example.memelli.prod.crud.repositories.ProjectRepository;
import com.example.memelli.prod.crud.repositories.TaskRepository;
import com.example.memelli.prod.crud.resources.exceptions.DataBaseException;
import com.example.memelli.prod.crud.services.exceptions.ResourceNotFoundException;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;



    @Autowired
    private ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    public Page<TaskDTO> findAllPaged(PageRequest pageable) {
        Page<Task> list = taskRepository.findAll(pageable);
        return list.map(x -> new TaskDTO(x));
    }

    @Transactional(readOnly = true)
    public List<TaskDTO> findbyProjectId(Project project) {
        List<Task> list = taskRepository.findByProjectId(project);
        List<TaskDTO> listDTO = list.stream().map(x -> new TaskDTO(x)).collect(Collectors.toList());
        return listDTO;
    }


    @Transactional(readOnly = true)
    public TaskDTO findById(Long id) {
        // Task entity = taskRepository.findById(id).get();
        // return new TaskDTO(entity);
        Optional<Task> obj = taskRepository.findById(id);
        Task entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new TaskDTO(entity);
    }

        @Transactional
        public TaskDTO insert(@RequestBody TaskDTO dto) {
        // pega o id do projeto que vem no DTO
        Long projectId = dto.getProjectId();

        // Valida o id do projeto
        if (projectId == null) {
            throw new ResourceNotFoundException("Project ID is required");
        }

        // Cria nova entitidade task
        Task entity = new Task();
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());

        // pega o projeto project id do taskdto e valida no projectrepository
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        // Atribui o projeto a task
        entity.setProject(project);

        // salva a entidade task
        entity = taskRepository.save(entity);

        return new TaskDTO(entity);
    }

   
    @Transactional
    public TaskDTO update(Long id, TaskDTO dto) {
        try{
        Task entity = taskRepository.getReferenceById(id);
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        entity = taskRepository.save(entity);
        return new TaskDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    public void delete(Long id) {
     try{
        taskRepository.deleteById(id);
        } 
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
        catch (DataIntegrityViolationException e) {  // um projeto com varias tasksk nao pode ser deletado
            throw new DataBaseException("Integrity violation");
        }
    }

    

}
