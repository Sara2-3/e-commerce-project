package com.example.sara.loginregistera.model;

import com.example.sara.loginregistera.Size;

import jakarta.persistence.*;

@Entity // Mark this class as a JPA entity
@Table(name = "cart_items") // Optional: Specify table name
public class CartItem {
    @Id // Mark this as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate the ID
    private Long id;

    @ManyToOne // Define the relationship with Product
    @JoinColumn(name = "product_id", nullable = false) // Specify the foreign key column
    private Product product;

    @Enumerated(EnumType.STRING) // Store the enum as a string in the database
    private Size size;

    private int quantity;

    private String username; // Associate the cart item with a specific user

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Method to calculate subtotal for this item
    public double getSubtotal() {
        return product.getPrice() * quantity;
    }
}