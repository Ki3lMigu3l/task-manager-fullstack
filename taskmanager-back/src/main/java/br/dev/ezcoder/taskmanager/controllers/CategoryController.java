package br.dev.ezcoder.taskmanager.controllers;


import br.dev.ezcoder.taskmanager.domain.categories.CategoryRequestDTO;
import br.dev.ezcoder.taskmanager.services.CategoryService;
import br.dev.ezcoder.taskmanager.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final TaskService taskService;

    public CategoryController(CategoryService categoryService, TaskService taskService) {
        this.categoryService = categoryService;
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Object> createCategory (@RequestBody CategoryRequestDTO categoryDto) {
        return null;
    }
}
