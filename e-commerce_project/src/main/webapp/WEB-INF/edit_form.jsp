<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Edit Product</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div class="form-container">
    <h1>Edit Product</h1>

    <!-- Form binds to the 'editProduct' model attribute -->
    <form:form
            action="/products/edit/${editProduct.id}" method="POST" modelAttribute="editProduct">

        <!-- Product Name -->
        <form:label path="name">Product Name:</form:label>
        <form:input path="name" />
        <br />

        <!-- Description -->
        <form:label path="description">Description:</form:label>
        <form:textarea path="description"></form:textarea>
        <br />

        <!-- Price -->
        <form:label path="price">Price (in $):</form:label>
        <form:input path="price" type="number" step="0.01" />
        <br />

        <!-- Image URL -->
        <form:label path="imageUrl">Image URL:</form:label>
        <form:input path="imageUrl" />
        <br />

        <!-- Submit Button -->
        <input type="submit" value="Update Product" />
    </form:form>
</div>
</body>
</html>