package com.example.sara.loginregistera.service;

import com.example.sara.loginregistera.model.Product;
import com.example.sara.loginregistera.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    public List<Product> findAllProducts() {
        return productRepo.findAll();
    }

    public Product findProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    public Product saveProduct(Product product) {
        return productRepo.save(product);
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
    //pjesa e cart
    public Product findById(Long productId) {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        return optionalProduct.orElse(null); // Return the product if found, else return null
    }
}