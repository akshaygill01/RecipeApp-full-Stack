package com.gill.recipesharingapp.Repository;

import com.gill.recipesharingapp.Models.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean addUser (User user) throws Exception {
        Response response = new Response();
        String query = "INSERT INTO Users (name,password,email) VALUES (?,? ,?)";
        // Use update method instead of query for non-query (insert, update, delete) operations
        int rowsAffected = jdbcTemplate.update(query, user.getName(),  user.getPassword(), user.getEmail());
        return rowsAffected >0 ? true : false;
        // Check if the insertion was successful (rowsAffected > 0)
    }
}
