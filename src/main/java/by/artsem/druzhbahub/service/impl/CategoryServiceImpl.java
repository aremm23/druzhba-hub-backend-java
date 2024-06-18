package by.artsem.druzhbahub.service.impl;

import by.artsem.druzhbahub.exception.DataNotCreatedException;
import by.artsem.druzhbahub.exception.DataNotFoundedException;
import by.artsem.druzhbahub.model.Category;
import by.artsem.druzhbahub.repository.CategoryRepository;
import by.artsem.druzhbahub.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Category create(Category entity) {
        if (categoryRepository.existsByName(entity.getName())) {
            throw new DataNotCreatedException("Category with name " + entity.getName() + " already exist");
        }
        entity.setCreatedAt(LocalDateTime.now());
        return categoryRepository.save(entity);
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Category with id %d not found".formatted(id))
        );
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public Category update(Long id, Category updatedCategory) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Category with id %d not found".formatted(id))
        );
        existingCategory.setName(updatedCategory.getName());
        return categoryRepository.save(existingCategory);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        categoryRepository.delete(categoryRepository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Category with id %d not found".formatted(id))
        ));
    }
}
