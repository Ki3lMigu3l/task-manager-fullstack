package br.dev.ezcoder.taskmanager.services;

import br.dev.ezcoder.taskmanager.domain.categories.CategoryModel;
import br.dev.ezcoder.taskmanager.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryModel saveCategory(CategoryModel categoryModel) {
        return categoryRepository.save(categoryModel);
    }

    public Optional<CategoryModel> findByTitle(String title) {
        return categoryRepository.findCategoryModelByTitle(title);
    }
}
