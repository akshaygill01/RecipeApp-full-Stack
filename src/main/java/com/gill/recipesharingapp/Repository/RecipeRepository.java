package com.gill.recipesharingapp.Repository;

import com.gill.recipesharingapp.Models.Recipe;
import com.gill.recipesharingapp.Models.Response;
import jakarta.persistence.ManyToOne;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Log4j2
public class RecipeRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean createRecipe(Integer userId, Recipe recipe) throws Exception {
        log.trace("recipe {}",recipe);
        String query = "INSERT INTO recipes (name, description, user_id, image) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(query, recipe.getName(), recipe.getDescription(), userId, recipe.getImage()) > 0;

    }

    public List<Recipe> getRecipes() throws Exception {
        String query = "SELECT * FROM recipes;";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Recipe.class));
    }


    public List<Recipe> getRecipeByUserId(Integer userId) throws Exception{
        String query = "SELECT * FROM recipes where user_id = ? ;";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Recipe.class),userId);
    }

    public Recipe getRecipeById(Integer id) throws Exception {
        String query = "SELECT * FROM recipes WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Recipe.class), id);
    }


    public boolean updateRecipe( Recipe recipe) throws Exception{
        String query = "update recipes set name = ? , description = ? , image = ? where id = ?; ";
        return jdbcTemplate.update(query ,recipe.getName(),recipe.getDescription(), recipe.getImage() , recipe.getId() ) > 0;
    }

    public boolean deleteRecipe(Integer id) throws Exception{
        String query = "delete from recipes where id = ?;";
        return jdbcTemplate.update(query,id ) > 0;
    }


}
