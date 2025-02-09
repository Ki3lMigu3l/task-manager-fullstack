package br.dev.ezcoder.taskmanager.repositories;

import br.dev.ezcoder.taskmanager.domain.tasks.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskModel, Long> {}
