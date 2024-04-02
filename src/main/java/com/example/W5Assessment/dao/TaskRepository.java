package com.example.W5Assessment.dao;

import com.example.W5Assessment.entity.Task;

import java.util.List;

public interface TaskRepository {
    List<Task> findAll();

    Task findById(Integer id);

    void save (Task task);

    void update (Task task);


}
