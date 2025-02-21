<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>${product.id == null ? "Add Product" : "Edit Product"}</title>
    <link rel="stylesheet" href="/css/style.css" />
</head>
<body>
<div class="container">
    <h1>${product.id == null ? "Add Product" : "Edit Product"}</h1>

    <!-- Form for adding or editing a product -->
    <form:form
            action="${product.id == null ? '/products/add' : '/products/edit/' + product.id}"
            method="POST"
            modelAttribute="product">

        <!-- Name field -->
        <div>
            <form:label path="name">Name:</form:label>
            <form:errors path="name" cssClass="error" />
            <form:input path="name" />
        </div>

        <!-- Description field -->
        <div>
            <form:label path="description">Description:</form:label>
            <form:errors path="description" cssClass="error" />
            <form:textarea path="description"></form:textarea>
        </div>

        <!-- Price field -->
        <div>
            <form:label path="price">Price:</form:label>
            <form:errors path="price" cssClass="error" />
            <form:input path="price" type="number" step="0.01" />
        </div>

        <!-- Image URL field -->
        <div>
            <form:label path="imageUrl">Image URL:</form:label>
            <form:errors path="imageUrl" cssClass="error" />
            <form:input path="imageUrl" />
        </div>

        <!-- Category dropdown -->
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

        <!-- Submit button -->
        <input type="submit" value="${product.id == null ? "Add Product" : "Update Product"}">
    </form:form>
</div>
</body>
</html>