<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Add Product</title>
    <link rel="stylesheet" href="/css/style.css" />
</head>
<body>
<div class="container">
    <h1>Add Product</h1>
    <form:form action="/products" method="POST" modelAttribute="product">
        <div>
            <form:label path="name">Name:</form:label>
            <form:errors path="name" cssClass="error" />
            <form:input path="name" />
        </div>

        <div>
            <form:label path="description">Description:</form:label>
            <form:errors path="description" cssClass="error" />
            <form:textarea path="description"></form:textarea>
        </div>

        <div>
            <form:label path="price">Price:</form:label>
            <form:errors path="price" cssClass="error" />
            <form:input path="price" type="number" step="0.01" />
        </div>

        <div>
            <form:label path="imageUrl">Image URL:</form:label>
            <form:errors path="imageUrl" cssClass="error" />
            <form:input path="imageUrl" />
        </div>

        <input type="submit" value="Save Product">
    </form:form>
</div>
</body>
</html>