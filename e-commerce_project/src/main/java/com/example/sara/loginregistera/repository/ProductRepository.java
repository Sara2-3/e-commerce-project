package com.example.sara.loginregistera.repository;

import com.example.sara.loginregistera.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryIgnoreCase(String category);
//    List<Product> findByCategory(String category);
}