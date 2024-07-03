package by.artsem.druzhbahub.controller;

import by.artsem.druzhbahub.model.Category;
import by.artsem.druzhbahub.model.dto.category.CategoryRequestDto;
import by.artsem.druzhbahub.model.dto.category.CategoryGetResponseDto;
import by.artsem.druzhbahub.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@CrossOrigin
public class CategoryController {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<HttpStatus> createCategory(@RequestBody CategoryRequestDto categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        categoryService.create(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryGetResponseDto> getCategoryById(@PathVariable Long id) {
        return new ResponseEntity<>(
                modelMapper.map(categoryService.getById(id), CategoryGetResponseDto.class),
                HttpStatus.OK
        );
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<CategoryGetResponseDto>> getAllCategories() {
        return new ResponseEntity<>(
                categoryService.getAll().stream().map(
                        category -> modelMapper.map(category, CategoryGetResponseDto.class)
                ).toList(),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryRequestDto> updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryRequestDto categoryDTO
    ) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category updatedCategory = categoryService.update(id, category);
        CategoryRequestDto updatedCategoryCreateRequestDto = modelMapper.map(updatedCategory, CategoryRequestDto.class);
        return new ResponseEntity<>(updatedCategoryCreateRequestDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
