package br.dev.ezcoder.taskmanager.controllers;

import br.dev.ezcoder.taskmanager.domain.tasks.TaskCreateResponseDTO;
import br.dev.ezcoder.taskmanager.domain.tasks.TaskModel;
import br.dev.ezcoder.taskmanager.domain.tasks.TaskRequestDTO;
import br.dev.ezcoder.taskmanager.domain.tasks.TaskResponseDTO;
import br.dev.ezcoder.taskmanager.services.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin("*")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskCreateResponseDTO> createNewTask (@RequestBody TaskRequestDTO taskDto) {
        var newTask = new TaskModel();
        BeanUtils.copyProperties(taskDto, newTask);
        taskService.save(newTask);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new TaskCreateResponseDTO(newTask));
    }

    @GetMapping
    public ResponseEntity<List<TaskModel>> getAllTasks () {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById (@PathVariable Long id) {
        var taskFind = taskService
                .findTaskById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found!"));

        return ResponseEntity.status(HttpStatus.OK)
                .body(new TaskResponseDTO(taskFind));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, @RequestBody TaskRequestDTO taskDto) {
        var taskFind = taskService.findTaskById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));

        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTask(id, taskFind, taskDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask (@PathVariable Long id) {
        var taskFind = taskService
                .findTaskById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found!"));
        taskService.delete(taskFind);
        return ResponseEntity.noContent().build();
    }
}
