<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>${product.name}</title>
</head>
<body>
<div class="container">

    <!-- Product Details -->
    <h1>${product.name}</h1>
    <img src="${product.imageUrl}" alt="${product.name}" />

    <p>${product.description}</p>
    <p><strong>Price:</strong> $${product.price}</p>

    <!-- Error Message -->
    <c:if test="${not empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>

    <!-- Add to Cart Form -->
    <form action="/cart/add" method="POST">
        <input type="hidden" name="productId" value="${product.id}" />

        <!-- Size Selection -->
        <label for="size">Select Size:</label>
        <select name="size" id="size" required>
            <c:forEach items="${sizes}" var="size">
                <option value="${size}">${size}</option>
            </c:forEach>
        </select>

        <!-- Quantity -->
        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" min="1" value="1" required />

        <button type="submit">Add to Cart</button>
    </form>

    <a href="/home">Back to Product List</a>
</div>
</body>
</html>