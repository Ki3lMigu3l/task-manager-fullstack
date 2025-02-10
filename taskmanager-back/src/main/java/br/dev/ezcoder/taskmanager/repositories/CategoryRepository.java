package br.dev.ezcoder.taskmanager.repositories;

import br.dev.ezcoder.taskmanager.domain.categories.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {}
