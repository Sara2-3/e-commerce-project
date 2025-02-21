package com.example.sara.loginregistera.repository;

import com.example.sara.loginregistera.Role;
import com.example.sara.loginregistera.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Check if at least one user exists with the given role
    boolean existsByRole(Role role);

    // Find a user by email (used during login/registration)
    Optional<User> findByEmail(String email);
}