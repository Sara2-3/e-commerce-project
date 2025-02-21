<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Product Details</title>
    <link rel="stylesheet" href="/css/style.css" />
</head>
<body>
<div class="container">

    <h1>${product.name}</h1>
    <img src="${product.imageUrl}" alt="${product.name}" />
    <p>${product.description}</p>
    <p>Price: $${product.price}</p>


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