package com.gill.recipesharingapp.Service;

import com.gill.recipesharingapp.Models.Response;
import com.gill.recipesharingapp.Models.User;
import com.gill.recipesharingapp.Repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Log4j2
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public boolean UserEmailExists(String email) {
        try {
            return userRepository.getByEmailId(email);
        } catch (Exception e) {
            log.trace("error while getting user by email {}", e);
            return false;
        }
    }

    public List<User> getAllUsers() {
        try {
            List<User> users = userRepository.getAllUsers();
            return users;
        } catch (Exception e) {
            log.trace("error while getting users {}", e);
            return Collections.emptyList();
        }
    }

    public User getUser(Integer userId) throws Exception {
        return userRepository.getByUserid(userId);
    }

    public Response updateUser(User user) {
        Response response = new Response();
        try {
            boolean userUpdated = userRepository.updateUser(user);
            if (userUpdated) {
                response.setMessage("user Updated successfully");
            }
        } catch (Exception e) {
            log.trace("error while updating user {}", e);
            response.setMessage("error while updating user {");
        }
        return response;
    }

    public Response deleteUser(Integer userId) {
        Response response = new Response();
        try {
            int userUpdated = userRepository.deleteUser(userId);
            if (userUpdated > 0) {
                response.setMessage("user deleted successfully");
            }
        } catch (Exception e) {
            log.trace("error while updating user {}", e);
            response.setMessage("error while deleting user ");
        }
        return response;
    }

}
