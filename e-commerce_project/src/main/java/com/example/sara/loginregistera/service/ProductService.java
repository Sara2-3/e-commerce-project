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

    public List<Product> findProductsByCategory(String category) {
        return productRepo.findByCategoryIgnoreCase(category); // Fetch products by category
    }

    public Product findProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    public void saveProduct(Product product) {
        productRepo.save(product);
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
    // Find product by ID
    public Product findById(Long productId) {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        return optionalProduct.orElse(null); // Return product if found, else null
    }
    // Check inventory for a specific size
    public boolean isStockAvailable(Product product, String size, int quantity) {
        switch (size.toUpperCase()) {
            case "S": return product.getStockS() >= quantity;
            case "M": return product.getStockM() >= quantity;
            case "L": return product.getStockL() >= quantity;
            case "XL": return product.getStockXL() >= quantity;
            default: return false;
        }
    }

    // Deduct inventory for a specific size
    public void updateStock(Product product, String size, int quantity) {
        switch (size.toUpperCase()) {
            case "S":
                product.setStockS(product.getStockS() - quantity);
                break;
            case "M":
                product.setStockM(product.getStockM() - quantity);
                break;
            case "L":
                product.setStockL(product.getStockL() - quantity);
                break;
            case "XL":
                product.setStockXL(product.getStockXL() - quantity);
                break;
        }
        saveProduct(product); // Persist changes
    }


//    public List<Product> findProductsByCategoryNew(String category) {
//        return productRepo.findByCategory(category);
//    }
}
