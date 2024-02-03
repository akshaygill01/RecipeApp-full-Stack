package com.gill.recipesharingapp.controller;

import com.gill.recipesharingapp.Models.Recipe;
import com.gill.recipesharingapp.Service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RecipeController {
    @Autowired
    RecipeService recipeService;


    @PostMapping("/recipe/{id}")
    public ResponseEntity<?> createRecipe(@PathVariable Integer id,@RequestBody Recipe recipe) {
        return ResponseEntity.ok().body(recipeService.createRecipe(id,recipe));
    }


    @GetMapping("/recipes")
    public ResponseEntity<?> getRecipes(){
        List<Recipe> recipes = new ArrayList();
        try{
           recipes = recipeService.getRecipes();
        }catch(Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(recipes);
    }

    @GetMapping("/recipes/{userId}")
    public ResponseEntity<?> getRecipesById(@PathVariable Integer userId){
        List<Recipe> recipes = new ArrayList();
        try{
            recipes = recipeService.getRecipeByUserId(userId);
        }catch(Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(recipes);
    }

    @GetMapping("/recipe/{id}")
    public ResponseEntity<?> getRecipeById(@PathVariable Integer id){
        Recipe recipe = new Recipe();
        try{
            recipe = recipeService.getRecipeById(id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(recipe);
    }

    @PutMapping("/recipe")
    public ResponseEntity<?> updateRecipe(@RequestBody Recipe recipe) {
        return ResponseEntity.ok().body(recipeService.updateRecipe(recipe));
    }

    @DeleteMapping("/recipe/{id}")
    public ResponseEntity<?> updateRecipe(@PathVariable Integer id) {
        return ResponseEntity.ok().body(recipeService.deleteRecipe(id));
    }






}
