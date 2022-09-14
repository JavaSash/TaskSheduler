package ru.stepenko.taskSheduler.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.stepenko.taskSheduler.model.Task;
import ru.stepenko.taskSheduler.repository.TaskRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/create")
    public void create(@RequestBody Task task) {
        log.info("Task = {} successfully created", task);
        this.taskRepository.save(task);
    }

    @GetMapping("get/{id}")
    public Task getById(@PathVariable(name = "id") Long id) {
        return this.taskRepository.findById(id).orElse(null);
    }

    @GetMapping("/get/tasks")
    public List<Task> getTasks() {
        List<Task> tasks = (List<Task>) this.taskRepository.findAll();
        return tasks.stream().sorted(Comparator.comparing(Task::getId)).collect(Collectors.toList());
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable(name = "id") Long id, @RequestBody Task task) {
        try {
            task.setId(getById(id).getId());
        } catch (NullPointerException e) {
            log.warn("There is no task with id = {}", id);
        }
        log.info("Task with id = {} successfully updated", id);
        this.taskRepository.save(task);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        log.info("Task with id = {} successfully deleted", id);
        this.taskRepository.deleteById(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        log.info("All tasks successfully deleted");
        this.taskRepository.deleteAll();
    }

    @PatchMapping("/task/{id}:mark-as-done")
    public void markAsDone(@PathVariable(name = "id") Long id) {
        if (this.taskRepository.existsById(id)) {
            log.info("Task with id = {} is done", id);
        } else {
            log.warn("There is no task with id = {}", id);
        }
        this.taskRepository.markAsDone(id);
    }

}
