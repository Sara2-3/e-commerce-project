<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Product Details</title>
    <link rel="stylesheet" href="/css/style.css" />
</head>
<body>
<div class="container">
 , i

    <div class="product-details">
        <img src="${product.imageUrl}" alt="${product.name}" />
        <h2>${product.name}</h2>
        <p>${product.description}</p>
        <p>Price: $${product.price}</p>
    </div>


    <!-- Add to Cart -->
    <form action="/cart/add" method="POST">
        <input type="hidden" name="productId" value="${product.id}" />
        <label for="size">Size:</label>
        <select name="size" id="size" required>
            <option value="S">S</option>
            <option value="M">M</option>
            <option value="L">L</option>
            <option value="XL">XL</option>
        </select>
        <label for="quantity">Quantity:</label>
        <input type="number" name="quantity" id="quantity" min="1" required />
        <button type="submit">Add to Cart</button>
    </form>
    <a href="/home">Back to Home</a>
</div>
</body>
</html>