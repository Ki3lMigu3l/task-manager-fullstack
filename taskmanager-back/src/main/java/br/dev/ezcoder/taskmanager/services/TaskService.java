package br.dev.ezcoder.taskmanager.services;

import br.dev.ezcoder.taskmanager.domain.tasks.TaskModel;
import br.dev.ezcoder.taskmanager.domain.tasks.TaskResponseDTO;
import br.dev.ezcoder.taskmanager.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskModel save(TaskModel taskModel) {
        return taskRepository.save(taskModel);
    }

    public List<TaskModel> findAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<TaskModel> findTaskById(Long id) {
        return taskRepository.findById(id);
    }
}
