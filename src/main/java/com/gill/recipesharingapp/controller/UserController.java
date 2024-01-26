package com.gill.recipesharingapp.controller;

import com.gill.recipesharingapp.Models.Response;
import com.gill.recipesharingapp.Models.User;
import com.gill.recipesharingapp.Repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonIgnore;


import java.lang.reflect.Type;

@Log4j2
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;


    @PostMapping("/user")
    public ResponseEntity addUser(@RequestBody User user) {
        Response response = new Response();
        try {
            boolean isUserAdded = userRepository.addUser(user);

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

}

//    @GetMapping("/")
//    public ResponseEntity<?> getUsers(){
//
//    }

