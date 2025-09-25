package com.saasapp.business_management.service;

import com.saasapp.business_management.dto.AppUserDTO;
import com.saasapp.business_management.entity.AppUser;
import com.saasapp.business_management.exception.UserNotFoundException;
import com.saasapp.business_management.repository.AppUserRepository;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppUserService {

    private static final Logger logger = LoggerFactory.getLogger(AppUserService.class);
    @Autowired
    private AppUserRepository appUserRepository;
    private AppUserDTO convertToDTO(AppUser user){
        return new AppUserDTO(user.getId(),user.getName(),user.getEmail());
    }

    private List<AppUserDTO> convertToDTOList(List<AppUser> users){
        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<AppUserDTO> getAllUser() {
        logger.debug("Fetching all Users form database");
        return convertToDTOList(appUserRepository.findAll());
    }

    public AppUserDTO getUserById(@Min(value = 1) Long id) {
        logger.debug("Fetching user by ID : {}",id);
        Optional<AppUser> user = appUserRepository.findById(id);
        return convertToDTO(user.orElseThrow(()->new UserNotFoundException(id)));
    }

    public AppUserDTO createUser(AppUser user) {
        logger.debug("Saving new user: {}",user.getEmail());
        return convertToDTO(appUserRepository.save(user));
    }

    public AppUserDTO updateUser(Long id, AppUser userDetails) {
        logger.debug("Updating user with ID {}",id);
        return appUserRepository.findById(id)
                .map(user -> {
                    user.setName(userDetails.getName());
                    user.setEmail(userDetails.getEmail());
                    return convertToDTO(appUserRepository.save(user));
                })
                .orElseThrow(()->new UserNotFoundException(id));

    }

    public String deleteUser(Long id) {
        logger.debug("Deleting user with ID {}",id);
        if(appUserRepository.existsById(id)){
            appUserRepository.deleteById(id);
            return "User deleted successfully.";
        }
        throw new UserNotFoundException(id);
    }
}
