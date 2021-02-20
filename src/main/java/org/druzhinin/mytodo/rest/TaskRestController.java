package org.druzhinin.mytodo.rest;

import org.druzhinin.mytodo.model.Task;
import org.druzhinin.mytodo.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/api/tasks")
public class TaskRestController {

    @Autowired
    private TaskRepository taskRepository;


    /**
     * Get all tasks
     * @return List Tasks
     */
    @GetMapping
    public List<Task> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks;
    }

    /**
     * Get task by id
     * @param id
     * @return Task
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (!task.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok()
                    .body(task.get());
        }
    }

    /**
     * Create Task
     * @param task
     * @return Task
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Task> createNewTask(
            @RequestBody Task task
    ){
        Task newTask = taskRepository.save(task);
        if (newTask == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .build()
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(newTask);
        }
    }

    /**
     * Update task By ID
     * @param id
     * @param task
     * @return Task
     */
    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long id,
            @RequestBody Task task
    ){
        task.setId(id);
        Task updatingTask = taskRepository.save(task);

        if (updatingTask != null ) {
            return ResponseEntity.accepted()
                    .body(updatingTask);
        } else {
            return ResponseEntity.notFound().build();

        }
    }

    /**
     * Delete Task By Id
     * @param id
     * @return
     */
    @DeleteMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Task> deleteTask( @PathVariable Long id ){
        Optional<Task> taskRemove = taskRepository.findById(id);
        if (taskRemove.isPresent()) {
            taskRepository.deleteById(id);
            return ResponseEntity.ok()
                    .body(taskRemove.get());
        } else {
            return ResponseEntity.notFound().build();

        }
    }
}
