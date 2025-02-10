package br.dev.ezcoder.taskmanager.domain.tasks;

import br.dev.ezcoder.taskmanager.domain.categories.CategoryModel;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_tasks")
public class TaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_task")
    private Long id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private LocalDateTime createdAt;
    @Column
    private Boolean isEdited;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @Column
    private LocalDateTime dateEdited;
    @OneToMany
    private List<CategoryModel> categories;

    public TaskModel () {
        this.createdAt = LocalDateTime.now();
        this.isEdited = false;
        this.status = TaskStatus.PENDING;
        this. categories = new ArrayList<>();
    }

    public TaskModel (String title, String description) {
        this.title = title;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.isEdited = false;
        this.status = TaskStatus.PENDING;
    }

    public List<CategoryModel> getCategories() {
        return categories;
    }

    public void setCategories(CategoryModel category) {
        this.categories.add(category);
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
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
