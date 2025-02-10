package br.dev.ezcoder.taskmanager.controllers;


import br.dev.ezcoder.taskmanager.domain.categories.CategoryModel;
import br.dev.ezcoder.taskmanager.domain.categories.CategoryRequestDTO;
import br.dev.ezcoder.taskmanager.domain.categories.CategoryResponseDTO;
import br.dev.ezcoder.taskmanager.domain.tasks.TaskModel;
import br.dev.ezcoder.taskmanager.services.CategoryService;
import br.dev.ezcoder.taskmanager.services.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final TaskService taskService;

    public CategoryController(CategoryService categoryService, TaskService taskService) {
        this.categoryService = categoryService;
        this.taskService = taskService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> createCategory (@PathVariable Long id, @RequestBody CategoryRequestDTO categoryDto) {
        TaskModel task = taskService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        CategoryModel categoryModel = new CategoryModel();
        BeanUtils.copyProperties(categoryDto, categoryModel);
        categoryModel.setTaskModel(task);
        categoryService.saveCategory(categoryModel);

        task.setCategories(categoryModel);
        taskService.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CategoryResponseDTO(categoryModel.getTitle(), categoryModel.getTaskModel()));
    }
}
