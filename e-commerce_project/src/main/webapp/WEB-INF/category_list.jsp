
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <title>${category} Products</title>
</head>
<body>
<h1>Products in ${category}</h1>

<c:forEach items="${products}" var="product">
  <div>
    <a href="/products/details/${product.id}">
      <img src="${product.imageUrl}" alt="${product.name}" />
    </a>
    <h3>${product.name}</h3>
    <p>${product.description}</p>
    <p>Price: $${product.price}</p>
<%--    <form action="/cart/add/${product.id}" method="POST">--%>
<%--      <input type="number" name="quantity" value="1" min="1" />--%>
<%--      <button type="submit">Add to Cart</button>--%>
    </form>
  </div>
</c:forEach>
</body>
</html>
