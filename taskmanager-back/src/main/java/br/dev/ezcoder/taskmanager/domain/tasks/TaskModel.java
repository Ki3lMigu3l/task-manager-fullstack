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

    public TaskModel () {}

    public TaskModel (String title, String description) {
        this.title = title;
        this.description = description;
        this.createdAt = LocalDateTime.now();
    }
}
