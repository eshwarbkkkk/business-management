package com.saasapp.business_management.controller;

import com.saasapp.business_management.entity.AppUser;
import com.saasapp.business_management.service.AppUserService;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AppUserController {

    private static final Logger logger = LoggerFactory.getLogger(AppUserController.class);


    @Autowired
    private AppUserService appUserService;

    @GetMapping
    public List<AppUser> getAllUsers(){
        logger.info("GET request: Fetching all users");
        return appUserService.getAllUser();
    }

    @GetMapping("/{id}")
    public AppUser getUserById(@PathVariable @Min(value = 1) Long id){
        logger.info("GET request: Fetching user with ID{}",id);
        return appUserService.getUserById(id);
    }

    @PostMapping
    public AppUser createUser(@RequestBody AppUser user){
        logger.info("POST request: Creating new user with email {}",user.getEmail());
        return appUserService.createUser(user);
    }

    @PutMapping("/{id}")
    public AppUser updateUser(@PathVariable Long id, @RequestBody AppUser user){
        logger.info("PUT request: Updating user with ID {}",id);
        return appUserService.updateUser(id,user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        logger.info("DELETE request : Deleting user with ID {}",id);
        return appUserService.deleteUser(id);
    }
}
