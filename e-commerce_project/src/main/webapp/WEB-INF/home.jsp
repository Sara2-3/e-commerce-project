<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Home - Products</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div class="header">
</div>
<div class="second-header">
    <ul>
        <li><a href="/dresses">DRESSES</a></li>
        <li><a href="/tops">TOPS</a></li>
        <li><a href="/pants">PANTS</a></li>
    </ul>
</div>

<div class="container">
    <h1>All Products</h1>

    <!-- Add Product Button for Admin -->
    <c:if test="${isAdmin}">
        <a href="/products/new" class="btn">Add New Product</a>
    </c:if>

    <!-- Product Grid -->
    <div class="product-grid">
        <c:forEach items="${products}" var="product">
            <div class="product-item">
                <!-- Make the image clickable; links to product details -->
                <a href="/products/details/${product.id}">
                    <img src="${product.imageUrl}" alt="${product.name}" />
                </a>
                <div class="product-name">${product.name}</div>
                <div class="product-description">${product.description}</div>
                <div class="product-price">$${product.price}</div>

                <!-- Admin-specific options -->
                <c:if test="${isAdmin}">
                    <div>
                        <!-- Edit and Delete Buttons for Admin -->
                        <a href="/products/edit/${product.id}" class="btn">Edit</a>
                        <a href="/products/delete/${product.id}" class="btn btn-danger">Delete</a>
                    </div>
                </c:if>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>