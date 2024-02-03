package com.gill.recipesharingapp.Service;

import com.gill.recipesharingapp.Models.Recipe;
import com.gill.recipesharingapp.Models.Response;
import com.gill.recipesharingapp.Repository.RecipeRepository;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class RecipeService {
    @Autowired
    RecipeRepository recipeRepository;

    public Response createRecipe(Integer userId, Recipe recipe){
        Response response = new Response();
        try {
            log.trace("recipe {}",recipe);
            boolean recipeCreated  = recipeRepository.createRecipe(userId, recipe);
            response.setMessage(recipeCreated ? "Recipe Added successfully" : "Recipe did Not Added, check parameters");
        } catch (Exception e) {
            response.setMessage("Error while Adding recipe");
            e.printStackTrace();
        }
        return response;
    }

    public List<Recipe> getRecipes() throws Exception {
       return recipeRepository.getRecipes();
    }

    public List<Recipe> getRecipeByUserId(Integer userId) throws Exception{
        return recipeRepository.getRecipeByUserId(userId);
    }

    public Recipe getRecipeById(Integer id) throws  Exception{
       return recipeRepository.getRecipeById(id);
    }

    public Response updateRecipe(Recipe recipe)  {
        Response response = new Response();
        try {
            boolean isUpdated = recipeRepository.updateRecipe( recipe);
            response.setMessage(isUpdated ? " Recipe Updated Successfully" : "Recipe did not updated");
        }catch (Exception e) {
            response.setMessage("Error updating Recipe");
            e.printStackTrace();
        }
        return response;
    }

    public Response deleteRecipe(Integer id){
        Response response = new Response();
        try {
            boolean isDeleted = recipeRepository.deleteRecipe(id);
            response.setMessage(isDeleted ? " Recipe Deleted Successfully" : "Recipe did not Deleted");
        }catch (Exception e) {
            response.setMessage("Error while deleting a  Recipe");
            e.printStackTrace();
        }
        return response;
    }

}
