package com.example.W5Assessment.service;

import com.example.W5Assessment.dao.TaskRepository;
import com.example.W5Assessment.entity.Task;
import com.example.W5Assessment.exception.EmptyTaskListException;
import com.example.W5Assessment.exception.NoSuchTaskExistsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findAll() {
        if (taskRepository.findAllTaskExist() != null) {
            return taskRepository.findAll();
        } else {
            throw new EmptyTaskListException("Task list is empty");
        }
    }

    @Override
    public List<Task> findAllTaskExist() {
        if (taskRepository.findAllTaskExist() != null) {
            return taskRepository.findAllTaskExist();
        } else {
            throw new EmptyTaskListException("Task list is empty");
        }
    }

    @Override
    public Task findById(Integer id) {
        if (taskRepository.findById(id) != null) {
            return taskRepository.findById(id);
        } else {
            throw new NoSuchTaskExistsException("Task not found with ID: " + id);
        }
    }

    @Override
    @Transactional
    public void save(Task task) {
        task.setDateCreated(LocalDateTime.now());
        task.setDateUpdated(LocalDateTime.now());
        task.setDeleted(false);
        taskRepository.save(task);
    }

    @Override
    @Transactional
    public void update(Task task) {
        if (taskRepository.findById(task.getId()) != null) {
            Task tempTask = taskRepository.findById(task.getId());
            task.setDateCreated(tempTask.getDateCreated());
            task.setDateUpdated(LocalDateTime.now());
            task.setDeleted(false);
            taskRepository.update(task);
        } else {
            throw new NoSuchTaskExistsException("Task not found with ID: " + task.getId());
        }
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (taskRepository.findById(id) != null) {
            Task task = taskRepository.findById(id);
            task.setDeleted(true);
            taskRepository.update(task);
        } else {
            throw new NoSuchTaskExistsException("Task not found with ID: " + id);
        }
    }
}
