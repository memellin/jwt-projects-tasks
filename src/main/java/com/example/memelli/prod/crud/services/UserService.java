package com.example.memelli.prod.crud.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.memelli.prod.crud.dto.RoleDTO;
import com.example.memelli.prod.crud.dto.TaskDTO;
import com.example.memelli.prod.crud.dto.UserDTO;
import com.example.memelli.prod.crud.dto.UserInsertDTO;
import com.example.memelli.prod.crud.dto.UserUpdateDTO;
import com.example.memelli.prod.crud.entities.Role;
import com.example.memelli.prod.crud.entities.User;
import com.example.memelli.prod.crud.repositories.RoleRepository;
import com.example.memelli.prod.crud.repositories.UserRepository;
import com.example.memelli.prod.crud.services.exceptions.DataBaseException;
import com.example.memelli.prod.crud.services.exceptions.ResourceNotFoundException;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TaskService taskService;

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                if (username == null) {
                    logger.error("User not found: " + username);
                    throw new UsernameNotFoundException("Unimplemented method 'loadUserByUsername'");
                } else {
                    return UserRepository.findByEmail(username);

                }
            }
        };
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> findAllPaged(Pageable pageable) {
        Page<User> list = UserRepository.findAll(pageable);
        return list.map(x -> new UserDTO(x));
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        Optional<User> obj = UserRepository.findById(id);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        List<TaskDTO> tasks = taskService.findbyUserId(entity);
        return new UserDTO(entity, tasks);
    }
    @Transactional
    public UserDTO insert(UserInsertDTO dto) {
        User entity = new User();
        copyDtoToEntity(dto, entity);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity = UserRepository.save(entity);
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO update(Long id, UserUpdateDTO dto) {
        try {
            User entity = UserRepository.getById(id);
            copyDtoToEntity(dto, entity);
            entity = UserRepository.save(entity);
            return new UserDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    public void delete(long id) {
        try {
            UserRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        } catch (DataIntegrityViolationException e) { // um projeto com varias tasksk nao pode ser deletado
            throw new DataBaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(UserDTO dto, User entity) {
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.getRoles().clear();
        for (RoleDTO roleDTO : dto.getRoles()) {
            Role role = roleRepository.getById(roleDTO.getId());
            entity.getRoles().add(role);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = UserRepository.findByEmail(username); // email = username
        if (user == null) {
            logger.error("User not found: " + username);
            throw new UsernameNotFoundException("Unimplemented method 'loadUserByUsername'");
        }
        logger.info("User found: " + username);
        return user;
    }
}
