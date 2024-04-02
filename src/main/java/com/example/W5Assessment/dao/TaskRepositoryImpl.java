package com.example.W5Assessment.dao;

import com.example.W5Assessment.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepository{
    private EntityManager entityManager;

    @Autowired
    public TaskRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Task> findAll() {
        TypedQuery<Task> query = entityManager.createQuery(
                "from Task where isDeleted = false", Task.class);
        // execute query
        return query.getResultList();
    }

    @Override
    public Task findById(Integer id) {
        return entityManager.find(Task.class, id);
    }

    @Override
    public void save(Task task) {
        entityManager.persist(task);
    }

    @Override
    public void update(Task task) {
        entityManager.merge(task);
    }

}
