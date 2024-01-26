package com.gill.recipesharingapp.Repository;

import com.gill.recipesharingapp.Models.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean addUser(User user) throws Exception {
        Response response = new Response();
        String query = "INSERT INTO Users (name,password,email) VALUES (? , ? ,?)";
        // Use update method instead of query for non-query (insert, update, delete) operations
        int rowsAffected = jdbcTemplate.update(query, user.getName(), user.getPassword(), user.getEmail());
        return rowsAffected > 0;
        // Check if the insertion was successful (rowsAffected > 0)
    }

    public boolean getByEmailId(String emailId) throws Exception {
        String query = "SELECT count(*) FROM Users WHERE email = ?";
        // Use queryForObject with RowMapper to fetch a single result as Integer
        Integer count = jdbcTemplate.queryForObject(query, Integer.class, emailId);

        // If count is greater than 0, the user exists
        return count != null && count > 0;

    }

    // get user by userid
    public User getByUserid(Integer userId) throws Exception {
        String query = "SELECT * from Users WHERE user_id = ?";
        return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(User.class), userId);
    }

    //get all users;
    public List<User> getAllUsers() throws Exception {
        String query = "SELECT * FROM Users";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class));
    }


    //update user by userID;
    public boolean updateUser(User user) throws Exception {
        String query = "UPDATE Users SET name = ?, email = ?, password = ? WHERE user_id = ?";
        return jdbcTemplate.update(query, user.getName(), user.getEmail(), user.getPassword(), user.getUserId()) > 0;
    }


    //Delete user by userID;
    public int deleteUser(Integer userId) throws Exception {
        String query = "DELETE FROM Users WHERE user_id = ?";
        return jdbcTemplate.update(query, userId);
    }

}
