package com.example.sara.loginregistera.controller;

import com.example.sara.loginregistera.model.Product;
import com.example.sara.loginregistera.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Display all products on the `/home` page.
     * Show admin-specific controls (add, edit, delete) if the user is an admin.
     */
    @GetMapping("/home")
    public String productList(Model model, HttpSession session) {
        model.addAttribute("products", productService.findAllProducts());

        // Check if logged-in user is an admin
        Object userRole = session.getAttribute("userRole");
        model.addAttribute("isAdmin", userRole != null && userRole.toString().equals("ADMIN"));

        return "home"; // Render `home.jsp`
    }

    /**
     * Display the add product page (Admin-only).
     *
     * @param model   Adds an empty product object to the model for the form.
     * @param session Checks user role for admin access.
     * @return The `product_form.jsp` view for creating a new product.
     */
    @GetMapping("/products/add")
    public String addProductForm(Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/home"; // Redirect non-admin users to `/home`
        }

        model.addAttribute("product", new Product()); // Add an empty product object for the form
        return "product_form"; // Render the `product_form.jsp` view
    }

    /**
     * Handle form submission for adding a new product (Admin-only).
     *
     * @param product The product details submitted via the form.
     * @param session Checks user role for admin access.
     * @return Redirect to `/home` after successfully adding the product.
     */
    @PostMapping("/products/add")
    public String createProduct(@Valid @ModelAttribute("product") Product product, HttpSession session, Model model) {
        if (!isAdmin(session)) {
            return "redirect:/home"; // Restrict access to admins
        }

        productService.saveProduct(product); // Save the product
        return "redirect:/home"; // Redirect back to `/home`
    }
    /**
     * Display product details for a specific product.
     */
    @GetMapping("/products/details/{id}")
    public String productDetails(@PathVariable Long id, Model model) {
        Product product = productService.findProductById(id);
        if (product == null) {
            return "redirect:/home"; // Redirect if product is not found
        }
        model.addAttribute("product", product);
        return "product_details"; // Render product_detail.jsp
    }

    /**
     * Deletes a product by ID (Admin-only).
     *
     * @param id      The ID of the product to delete.
     * @param session Checks user role for admin access.
     * @return Redirect to `/home` after deletion.
     */
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/home"; // Restrict access to admins
        }

        productService.deleteProduct(id); // Delete the product
        return "redirect:/home"; // Reload `/home`
    }

    /**
     * Displays a product details page for editing (Admin-only).
     */
    @GetMapping("/products/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/home"; // Restrict access to admins
        }

        Product product = productService.findProductById(id);
        if (product == null) {
            return "redirect:/home"; // Redirect to `home` if product is not found
        }

        model.addAttribute("product", product); // Add the product to the model
        return "edit_form"; // Render `edit_form.jsp` for editing
    }

    /**
     * Handle the edit form submission (Admin-only).
     */
    @PostMapping("/products/edit/{id}")
    public String editProduct(@PathVariable Long id, @Valid @ModelAttribute("product") Product product, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/home"; // Restrict access to admins
        }

        product.setId(id); // Ensure the product ID remains unchanged
        productService.saveProduct(product); // Save the updated product
        return "redirect:/home"; // Redirect back to `/home`
    }

    /**
     * Helper method to check if the logged-in user is an admin.
     */
    private boolean isAdmin(HttpSession session) {
        Object userRole = session.getAttribute("userRole");
        return userRole != null && userRole.toString().equals("ADMIN");
    }
    @GetMapping("/{category}")
    public String viewCategory(@PathVariable String category, Model model) {
        List<Product> filteredProducts = productService.findProductsByCategory(category);
        model.addAttribute("products", filteredProducts);
        model.addAttribute("category", category);
        return "category_list"; // Render JSP to display filtered products
    }
}