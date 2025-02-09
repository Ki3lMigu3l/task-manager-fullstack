package br.dev.ezcoder.taskmanager.controllers;

import br.dev.ezcoder.taskmanager.domain.tasks.TaskModel;
import br.dev.ezcoder.taskmanager.domain.tasks.TaskRequestDTO;
import br.dev.ezcoder.taskmanager.domain.tasks.TaskResponseDTO;
import br.dev.ezcoder.taskmanager.services.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createNewTask (@RequestBody TaskRequestDTO taskDto) {
        var newTask = new TaskModel();
        BeanUtils.copyProperties(taskDto, newTask);
        taskService.save(newTask);

        System.out.println(newTask.getCreatedAt());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new TaskResponseDTO(
                        newTask.getTitle(),
                        newTask.getDescription(),
                        newTask.getCreatedAt()));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks () {
        List<TaskModel> tasksList = taskService.findAllTasks();
        List<TaskResponseDTO> taskResponseDTOList = new ArrayList<>();

        for (TaskModel task : tasksList) {
            taskResponseDTOList.add(new TaskResponseDTO(task.getTitle(), task.getDescription(), task.getCreatedAt()));
        }

        return ResponseEntity.status(HttpStatus.OK).body(taskResponseDTOList.stream().toList());
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
                        taskFind.getCreatedAt()));
    }
}
