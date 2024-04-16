package com.pinsoft.recipesite.Service;

import com.pinsoft.recipesite.Dto.AddRecipeDto;
import com.pinsoft.recipesite.Dto.UpdateRecipeDto;
import com.pinsoft.recipesite.Entity.Category;
import com.pinsoft.recipesite.Entity.Recipe;
import com.pinsoft.recipesite.Entity.User;
import com.pinsoft.recipesite.Repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private CategoryService categoryService;

    @Autowired UserService userService;

    public List<Recipe> getAllRecipes(){return recipeRepository.findAll();}

    public Optional<Recipe> getRecipeById(Long id){return recipeRepository.findById(id); }

    public void addRecipe (AddRecipeDto addRecipeDto){
        Recipe recipe = new Recipe();
        recipe.setName(addRecipeDto.getName());
        recipe.setPreparation_time(addRecipeDto.getPreparation_time());
        recipe.setBase64image(addRecipeDto.getBase64image());
        recipe.setExplanation(addRecipeDto.getExplanation());
        recipe.setMaterials(addRecipeDto.getMaterials());
        Category category = categoryService.getCategoryById(addRecipeDto.getCategoryId()).get();
        User user = userService.getUserById(addRecipeDto.getUser_id()).get();
        recipe.setUser_id(user);
        recipe.setCategory(category);
        recipeRepository.save(recipe);
    }
    public void updateRecipe(UpdateRecipeDto updateRecipeDto){
        Recipe recipe = recipeRepository.findById(updateRecipeDto.getId()).get();
        recipe.setName(updateRecipeDto.getName());
        recipe.setPreparation_time(updateRecipeDto.getPreparation_time());
        recipe.setBase64image(updateRecipeDto.getBase64image());
        recipe.setExplanation(updateRecipeDto.getExplanation());
        recipe.setMaterials(updateRecipeDto.getMaterials());
        Category category = categoryService.getCategoryById(updateRecipeDto.getCategoryId()).get();
        User user = userService.getUserById(updateRecipeDto.getUser_id()).get();
        recipe.setUser_id(user);
        recipe.setCategory(category);
        recipeRepository.save(recipe);
    }
    public void deleteRecipe(Long id){
        recipeRepository.deleteById(id);
    }
}
