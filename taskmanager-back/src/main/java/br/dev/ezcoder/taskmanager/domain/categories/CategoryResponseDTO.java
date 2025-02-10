package br.dev.ezcoder.taskmanager.domain.categories;

import br.dev.ezcoder.taskmanager.domain.tasks.TaskModel;

public record CategoryResponseDTO(String title, TaskModel taskModel) {
}
