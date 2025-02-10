package br.dev.ezcoder.taskmanager.domain.categories;

import br.dev.ezcoder.taskmanager.domain.tasks.TaskModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_categories")
public class CategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name = "id_task")
    @JsonIgnore
    private TaskModel taskModel;

    public CategoryModel () {}

    public CategoryModel(String title) {
        this.title = title;
    }

    public CategoryModel(String title, TaskModel taskModel) {
        this.title = title;
        this.taskModel = taskModel;
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

    public TaskModel getTaskModel() {
        return taskModel;
    }

    public void setTaskModel(TaskModel taskModel) {
        this.taskModel = taskModel;
    }
}
