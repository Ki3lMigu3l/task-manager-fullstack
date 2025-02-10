package br.dev.ezcoder.taskmanager.domain.tasks;

import br.dev.ezcoder.taskmanager.domain.categories.CategoryModel;

import java.time.LocalDateTime;
import java.util.List;

public record TaskResponseDTO(
        String title,
        String description,
        LocalDateTime createdAt,
        boolean wasEdited,
        LocalDateTime dateEdited,
        TaskStatus status,
        List<CategoryModel> categories) {
}
