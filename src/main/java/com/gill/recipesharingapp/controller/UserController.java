package com.gill.recipesharingapp.controller;

import com.gill.recipesharingapp.Models.Response;
import com.gill.recipesharingapp.Models.User;
import com.gill.recipesharingapp.Repository.UserRepository;
import com.gill.recipesharingapp.Service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Log4j2
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;


    @PostMapping("/user")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        Response response = new Response();
        try {
            boolean userAlreadyExists = userService.UserEmailExists(user.getEmail());

            boolean isUserAdded = userRepository.addUser(user);
            if (userAlreadyExists) {
                response.setMessage("User with this email, already exists");
                return ResponseEntity.badRequest().body(response);
            }

            if (isUserAdded) {
                response.setMessage("User added successfully");
                return ResponseEntity.ok().body(response);
            } else {
                response.setMessage("User not added. Check your input or try again later.");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            log.error("Error while adding user", e);
            response.setMessage("Error while adding user");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers()  {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id){
        try{
            return ResponseEntity.ok().body(userService.getUser(id));
        }
        catch (Exception e){
            log.trace("Error while getting user",e);
            Response response = new Response();
            response.setMessage("Error while getting user");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/user")
    public Response updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/user/{id}")
    public Response deleteUser(@PathVariable Integer id){
        return userService.deleteUser(id);
    }


}



