package br.dev.ezcoder.taskmanager.services;

import br.dev.ezcoder.taskmanager.domain.tasks.TaskModel;
import br.dev.ezcoder.taskmanager.domain.tasks.TaskRequestDTO;
import br.dev.ezcoder.taskmanager.domain.tasks.TaskResponseDTO;
import br.dev.ezcoder.taskmanager.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public void delete(TaskModel taskFind) {
        taskRepository.delete(taskFind);
    }

    public Optional<TaskModel> findById(Long id) {
        return taskRepository.findById(id);
    }

    public TaskResponseDTO updateTask(Long id, TaskModel taskFind, TaskRequestDTO taskDto) {
        taskFind.setId(id);
        taskFind.setTitle(taskDto.title());
        taskFind.setDescription(taskDto.description());
        taskFind.setEdited(true);
        taskFind.setDateEdited(LocalDateTime.now());
        taskRepository.save(taskFind);

        return new TaskResponseDTO(taskFind);
    }
}
