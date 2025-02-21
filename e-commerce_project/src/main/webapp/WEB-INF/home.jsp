<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Product List</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<h1>Welcome to the Product List</h1>

<!-- Display Categories -->
<div class="categories">
    <h2>Shop by Category</h2>
    <ul>
        <li><a href="/dresses">Dresses</a></li>
        <li><a href="/tops">Tops</a></li>
        <li><a href="/pants">Pants</a></li>
    </ul>
</div>

<!-- Admin-only: Add Product Button -->
<c:if test="${isAdmin}">
    <div>
        <a href="/products/add" class="btn btn-primary">Add a New Product</a>
    </div>
</c:if>

<!-- Display List of Products -->
<div class="product-list">
    <c:forEach items="${products}" var="product">
        <div class="product-item">
            <a href="/products/details/${product.id}">
                <img src="${product.imageUrl}" alt="${product.name}" class="product-image" />
            </a>
            <h3>${product.name}</h3>
            <p>${product.description}</p>
            <p>Price: $${product.price}</p>
            <p>Category: <strong>${product.category}</strong></p>

            <!-- Admin-only CRUD Controls -->
            <c:if test="${isAdmin}">
                <div class="admin-controls">
                    <!-- Edit Product Link -->
                    <a href="/products/edit/${product.id}" class="btn btn-warning">Edit</a>

                    <!-- Delete Product Link -->
                    <a href="/products/delete/${product.id}" class="btn btn-danger"
                       onclick="return confirm('Are you sure you want to delete this product?');">Delete</a>
                </div>
            </c:if>
        </div>
    </c:forEach>
</div>
</body>
</html>