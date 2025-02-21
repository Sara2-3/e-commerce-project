package com.example.sara.loginregistera.repository;

import com.example.sara.loginregistera.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUsername(String username); // Fetch cart items for a specific user
}
