package com.example.sara.loginregistera.controller;

import com.example.sara.loginregistera.Size;
import com.example.sara.loginregistera.model.CartItem;
import com.example.sara.loginregistera.model.Product;
import com.example.sara.loginregistera.service.CartService;
import com.example.sara.loginregistera.service.ProductService;
import com.example.sara.loginregistera.service.StockService;
import com.example.sara.loginregistera.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;
    // Additional Autowired Services
    @Autowired
    private StockService stockService;

    /**
     * Display the home page with product listing
     */
    @GetMapping("/home")
    public String productList(Model model, HttpSession session) {
        // Fetch all products
        model.addAttribute("products", productService.findAllProducts());

        // Checking user role to show Admin functionality
        Object userRole = session.getAttribute("userRole");
        model.addAttribute("isAdmin", userRole != null && userRole.toString().equals("ADMIN"));

        return "home";
    }

    /**
     * Form for Adding a New Product (Admin Only)
     */
    @GetMapping("/products/new")
    public String newProductForm(Model model, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/home";

        model.addAttribute("product", new Product());
        return "product_form";
    }

    /**
     * Save a New Product (Admin Only)
     */
    @PostMapping("/products")
    public String createProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/home";

        if (result.hasErrors()) {
            return "product_form";
        }

        productService.saveProduct(product);
        return "redirect:/home";
    }

    /**
     * Delete Product (Admin Only)
     */
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/home";

        productService.deleteProduct(id);
        return "redirect:/home";
    }

    /**
     * Helper Method to Check Admin Role
     */
    private boolean isAdmin(HttpSession session) {
        Object userRole = session.getAttribute("userRole");
        return userRole != null && userRole.toString().equals("ADMIN");
    }
    // Display Edit Product Form (Admin Only)
    @GetMapping("/products/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model, HttpSession session) {
        // Restrict non-admin users
        if (!isAdmin(session)) {
            return "redirect:/home";
        }

        // Retrieve the product by ID
        Product product = productService.findProductById(id);
        if (product == null) {
            return "redirect:/home"; // Redirect if no product is found
        }

        // Add the product to the model with the name 'editProduct'
        model.addAttribute("editProduct", product);
        return "edit_form"; // Render edit_form.jsp
    }

    // Save the Updated Product (Admin Only)
    @PostMapping("/products/edit/{id}")
    public String updateProduct(
            @PathVariable Long id,
            @Valid @ModelAttribute("editProduct") Product product,
            BindingResult result,
            HttpSession session
    ) {
        // Restrict non-admin users
        if (!isAdmin(session)) {
            return "redirect:/home";
        }

        // Handle validation errors
        if (result.hasErrors()) {
            return "edit_form";
        }

        // Update the product with the same ID in the database
        product.setId(id); // Ensure the ID remains unchanged
        productService.saveProduct(product); // Save updated product
        return "redirect:/home"; // Redirect to home after successful update
    }
    @GetMapping("/products/details/{id}")
    public String productDetails(@PathVariable Long id, Model model, HttpSession session) {
        // Retrieve the product by ID
        Product product = productService.findProductById(id);

        // If product does not exist, redirect to the home page
        if (product == null) {
            return "redirect:/home";
        }

        // Add the product to the model
        model.addAttribute("product", product);

        // Add sizes (enum) for the dropdown in the JSP
        model.addAttribute("sizes", Size.values());

        // Add a new CartItem object to bind to the form (for adding to the cart)
        model.addAttribute("cartItem", new CartItem());

        // Determine if the logged-in user is an admin
        Object userRole = session.getAttribute("userRole");
        boolean isAdmin = userRole != null && userRole.toString().equals("ADMIN");
        model.addAttribute("isAdmin", isAdmin);

        return "product_details"; // Directs to product_details.jsp
    }
    //Your method to render the product details page
    @GetMapping("/product/{id}")
    public String showProductDetails(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        if (product == null) {
            throw new RuntimeException("Product not found with ID: " + id);
        }

        // Add the enum values to the model
        model.addAttribute("product", product);
        model.addAttribute("sizes", Size.values()); // Size.values() returns an array of enum constants
        return "product_details"; // Render product_details.jsp
    }


}