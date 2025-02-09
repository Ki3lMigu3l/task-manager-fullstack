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

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
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

        System.out.println(newTask.getCreatedAt());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new TaskCreateResponseDTO(
                        newTask.getTitle(),
                        newTask.getDescription(),
                        newTask.getCreatedAt()
                ));
    }

    @GetMapping
    public ResponseEntity<List<TaskModel>> getAllTasks () {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTaskById (@PathVariable Long id) {
        var taskFind = taskService
                .findTaskById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found!"));

        return ResponseEntity.status(HttpStatus.OK)
                .body(new TaskResponseDTO(
                        taskFind.getTitle(),
                        taskFind.getDescription(),
                        taskFind.getCreatedAt(),
                        taskFind.getEdited(),
                        taskFind.getDateEdited()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask (@PathVariable Long id) {
        var taskFind = taskService
                .findTaskById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found!"));

        taskService.delete(taskFind);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("Task: " + taskFind.getTitle() + " deletada com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, @RequestBody TaskRequestDTO taskDto) {
        var taskFind = taskService.findTaskById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));

        taskFind.setId(id);
        taskFind.setTitle(taskDto.title());
        taskFind.setDescription(taskDto.description());
        taskFind.setEdited(true);
        taskFind.setDateEdited(LocalDateTime.now());

        taskService.save(taskFind);

        return ResponseEntity.status(HttpStatus.OK).body(new TaskResponseDTO(
                taskFind.getTitle(),
                taskFind.getDescription(),
                taskFind.getCreatedAt(),
                taskFind.getEdited(),
                taskFind.getDateEdited()));
    }
}
