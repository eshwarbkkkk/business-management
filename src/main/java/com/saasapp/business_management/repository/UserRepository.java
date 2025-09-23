package com.saasapp.business_management.repository;

import com.saasapp.business_management.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser,Long> {
}
