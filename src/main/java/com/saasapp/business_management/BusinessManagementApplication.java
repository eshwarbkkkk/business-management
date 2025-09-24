package com.saasapp.business_management;

import com.saasapp.business_management.entity.AppUser;
import com.saasapp.business_management.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BusinessManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusinessManagementApplication.class, args);
	}



}
