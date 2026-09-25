package com.techstep.taskmng.service;

import com.techstep.taskmng.model.Task;
import com.techstep.taskmng.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public Task create(Task task){
        return taskRepository.save(task);
    }

    public void delete(Long id){
        boolean existsById = taskRepository.existsById(id);
        if(!existsById){
            throw new EntityNotFoundException();
        }
        taskRepository.deleteById(id);
    }
}
