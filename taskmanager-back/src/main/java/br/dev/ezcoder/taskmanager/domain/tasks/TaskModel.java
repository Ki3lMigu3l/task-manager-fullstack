package br.dev.ezcoder.taskmanager.domain.tasks;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_tasks")
public class TaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private LocalDateTime createdAt;
    @Column
    private Boolean isEdited;

    @Column
    private LocalDateTime dateEdited;

    public TaskModel () {
        this.createdAt = LocalDateTime.now();
        this.isEdited = false;
    }

    public TaskModel (String title, String description) {
        this.title = title;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.isEdited = false;
    }

    public LocalDateTime getDateEdited() {
        return dateEdited;
    }

    public void setDateEdited(LocalDateTime dateEdited) {
        this.dateEdited = dateEdited;
    }

    public Boolean getEdited() {
        return isEdited;
    }

    public void setEdited(Boolean edited) {
        isEdited = edited;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
