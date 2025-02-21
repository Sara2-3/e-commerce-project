package com.example.sara.loginregistera.repository;

import com.example.sara.loginregistera.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}