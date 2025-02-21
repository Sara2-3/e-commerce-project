<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Your Shopping Cart</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div class="container">
    <h1>Your Shopping Cart</h1>

    <!-- If the cart is empty -->
    <c:if test="${empty cartItems}">
        <p>Your cart is currently empty.</p>
        <a href="/home" class="btn">Continue Shopping</a>
    </c:if>

    <!-- If there are items in the cart -->
    <c:if test="${not empty cartItems}">
        <table class="cart-table">
            <thead>
            <tr>
                <th>Product</th>
                <th>Size</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Subtotal</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <!-- Loop through each cart item -->
            <c:forEach items="${cartItems}" var="item">
                <tr>
                    <td>
                        <img src="${item.product.imageUrl}" alt="${item.product.name}" width="50" height="50"/>
                            ${item.product.name}
                    </td>
                    <td>${item.size}</td>
                    <td>${item.quantity}</td>
                    <td>$${item.product.price}</td>
                    <td>$${item.quantity * item.product.price}</td>
                    <td>
                        <!-- Remove button -->
                        <a href="/cart/remove/${item.product.id}?size=${item.size}" class="btn btn-danger">Remove</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="4" align="right"><strong>Total Price:</strong></td>
                <td colspan="2">$${totalPrice}</td>
            </tr>
            </tfoot>
        </table>

        <div class="cart-actions">
            <a href="/cart/clear" class="btn btn-danger">Clear Cart</a>
        </div>
    </c:if>
</div>
</body>
</html>