package com.example.W5Assessment.rest;

import com.example.W5Assessment.entity.Task;
import com.example.W5Assessment.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/taskapi")

public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("tasks")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Task>> findAll() {
        return new ResponseEntity<List<Task>>(taskService.findAll(), HttpStatus.OK);
    }

    @GetMapping("tasks/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Task> findById(@PathVariable Integer id) {
        return new ResponseEntity<Task>(taskService.findById(id), HttpStatus.OK);
    }

    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> save(@RequestBody Task task) {
        taskService.save(task);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> update(@RequestBody Task task) {
        taskService.update(task);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        taskService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
