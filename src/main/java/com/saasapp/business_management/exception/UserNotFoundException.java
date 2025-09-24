package com.saasapp.business_management.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
        super("User with ID "+id+" not found.");
    }
}
