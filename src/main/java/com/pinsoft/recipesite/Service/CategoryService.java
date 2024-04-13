package com.pinsoft.recipesite.Service;

import com.pinsoft.recipesite.Dto.CategoryDto;
import com.pinsoft.recipesite.Entity.Category;
import com.pinsoft.recipesite.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }
    public  void addCategory(CategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
    }
}
