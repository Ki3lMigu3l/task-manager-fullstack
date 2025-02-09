package br.dev.ezcoder.taskmanager.domain.tasks;

import java.time.LocalDateTime;

public record TaskResponseDTO(String title, String description, LocalDateTime createdAt) {
}
