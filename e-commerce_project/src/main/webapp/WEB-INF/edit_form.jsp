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

    <!-- Form binds to the 'product' model attribute -->
    <form:form
            action="/products/edit/${product.id}" method="POST" modelAttribute="product">

        <!-- Product Name -->
        <div>
            <form:label path="name">Product Name:</form:label>
            <form:errors path="name" cssClass="error" />
            <form:input path="name" />
        </div>
        <br />

        <!-- Description -->
        <div>
            <form:label path="description">Description:</form:label>
            <form:errors path="description" cssClass="error" />
            <form:textarea path="description"></form:textarea>
        </div>
        <br />

        <!-- Price -->
        <div>
            <form:label path="price">Price (in $):</form:label>
            <form:errors path="price" cssClass="error" />
            <form:input path="price" type="number" step="0.01" />
        </div>
        <br />

        <!-- Image URL -->
        <div>
            <form:label path="imageUrl">Image URL:</form:label>
            <form:errors path="imageUrl" cssClass="error" />
            <form:input path="imageUrl" />
        </div>
        <br />

        <!-- Category -->
        <div>
            <form:label path="category">Category:</form:label>
            <form:errors path="category" cssClass="error" />
            <form:select path="category">
                <form:option value="">-- Select Category --</form:option>
                <form:option value="Dresses">Dresses</form:option>
                <form:option value="Tops">Tops</form:option>
                <form:option value="Pants">Pants</form:option>
            </form:select>
        </div>
        <br />

        <!-- Submit Button -->
        <input type="submit" value="Update Product" />
    </form:form>
</div>
</body>
</html>