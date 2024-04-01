package com.example.W5Assessment.service;

import com.example.W5Assessment.entity.Task;

import java.util.List;

public interface TaskService {

    List<Task> findAll();

    List<Task> findAllTaskExist();

    Task findById(Integer id);

    void save(Task task);

    void update(Task task);

    void delete(Integer id);
}
