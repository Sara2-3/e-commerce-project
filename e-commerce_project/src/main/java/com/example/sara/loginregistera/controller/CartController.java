package com.example.sara.loginregistera.controller;

import com.example.sara.loginregistera.Size;
import com.example.sara.loginregistera.model.CartItem;
import com.example.sara.loginregistera.model.Product;
import com.example.sara.loginregistera.service.CartService;
import com.example.sara.loginregistera.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    // View the cart
    @GetMapping
    public String viewCart(Model model) {
        List<CartItem> cartItems = cartService.getCartItems(); // Get all items in the cart
        double totalPrice = cartService.getTotalPrice(); // Calculate total price

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);
        return "cart"; // Renders cart.jsp
    }

    // Add a product to the cart
    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId,
                            @RequestParam String size,
                            @RequestParam int quantity,
                            Model model) {
        // Check if product exists
        Product product = productService.findById(productId);

        if (product == null) {
            // Gracefully display an error message on the product details page
            model.addAttribute("errorMessage", "Product not found with ID: " + productId);
            return "product_details"; // Return to product details page with an error
        }

        // Validate size input and add to cart
        try {
            Size selectedSize = Size.valueOf(size.toUpperCase());
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setSize(selectedSize);
            cartItem.setQuantity(quantity);

            cartService.addToCart(cartItem); // Add product to cart
            return "redirect:/cart"; // Redirect to cart page
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", "Invalid size selected. Please try again.");
            return "product_details"; // Return to product details page with an error
        }
    }

    // Remove a product from the cart
    @GetMapping("/remove/{productId}")
    public String removeProductFromCart(@PathVariable Long productId, @RequestParam String size, Model model) {
        Size selectedSize = Size.valueOf(size.toUpperCase());
        cartService.removeFromCart(productId, selectedSize); // Remove product by ID and size
        return "redirect:/cart"; // Redirect to the cart page
    }

    // Clear the entire cart
    @GetMapping("/clear")
    public String clearCart() {
        cartService.clearCart(); // Clear all items from the cart
        return "redirect:/cart"; // Redirect to the cart page
    }
}