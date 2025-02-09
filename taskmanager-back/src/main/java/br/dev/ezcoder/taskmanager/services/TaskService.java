package br.dev.ezcoder.taskmanager.services;

import br.dev.ezcoder.taskmanager.domain.tasks.TaskModel;
import br.dev.ezcoder.taskmanager.repositories.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskModel save(TaskModel taskModel) {
        return taskRepository.save(taskModel);
    }
}
