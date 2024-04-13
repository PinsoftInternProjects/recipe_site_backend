package com.pinsoft.recipesite.Controller;

import com.pinsoft.recipesite.Dto.AddRecipeDto;
import com.pinsoft.recipesite.Dto.UpdateRecipeDto;
import com.pinsoft.recipesite.Entity.Recipe;
import com.pinsoft.recipesite.Service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/recipes")
    public ResponseEntity<List<Recipe>> getAllRecipes(){
        List<Recipe> recipes = recipeService.getAllRecipes();
        return  new ResponseEntity<>(recipes, HttpStatus.OK);
    }
    @GetMapping("/recipes/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id){
        Optional<Recipe> recipeOptional = recipeService.getRecipeById(id);
        if (recipeOptional.isPresent()){
            return new ResponseEntity<>(recipeOptional.get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/recipes")
    public  void addRecipe(@RequestBody AddRecipeDto addRecipeDto){
        recipeService.addRecipe(addRecipeDto);
    }
    @PutMapping("/recipes/{id}")
    public void updateRecipe(@RequestBody UpdateRecipeDto updateRecipeDto){
        recipeService.updateRecipe(updateRecipeDto);
    }
    @DeleteMapping("/recipes/{id}")
    public void deleteRecipe(@PathVariable Long id){
        recipeService.deleteRecipe(id);
    }
}
