package com.saasapp.business_management.controller;

import com.saasapp.business_management.dto.AppUserDTO;
import com.saasapp.business_management.entity.AppUser;
import com.saasapp.business_management.exception.UserNotFoundException;
import com.saasapp.business_management.service.AppUserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.aspectj.bridge.IMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AppUserController {

    private static final Logger logger = LoggerFactory.getLogger(AppUserController.class);


    @Autowired
    private AppUserService appUserService;

    @GetMapping
    public ResponseEntity<List<AppUserDTO>> getAllUsers(){
        logger.info("GET request: Fetching all users");
        List<AppUserDTO> dtos = appUserService.getAllUser();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUserDTO> getUserById(@PathVariable @Min(value = 1) Long id) {
        logger.info("GET request: Fetching user with ID {}", id);
        AppUserDTO user = appUserService.getUserById(id); // No need for orElseThrow here
        return ResponseEntity.ok(user);
    }


    @PostMapping
    public ResponseEntity<AppUserDTO> createUser(@Valid @RequestBody AppUser user){
        logger.info("POST request: Creating new user with email {}",user.getEmail());
         AppUserDTO dto = appUserService.createUser(user);
         return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUserDTO> updateUser(@PathVariable Long id, @RequestBody AppUser user){
        logger.info("PUT request: Updating user with ID {}",id);
        AppUserDTO updatedUser = appUserService.updateUser(id,user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        logger.info("DELETE request : Deleting user with ID {}",id);
        String message = appUserService.deleteUser(id);
        return ResponseEntity.ok(message);
    }
}
