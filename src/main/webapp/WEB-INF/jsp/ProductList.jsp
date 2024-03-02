<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%> <%@taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <title>Product List</title>
  </head>
  <body>
    <h1>Product List</h1>
    <h4 style="color: red">${ERROR}</h4>
    <table border="0" cellpadding="5" cellspacing="3">
      <tr>
        <th>product_id</th>
        <th>name</th>
        <th>quantity_in_stock</th>
        <th>unit_price</th>
        <th>Update</th>
        <th>Delete</th>
      </tr>
      <c:forEach items="${products}" var="product">
        <tr>
          <td>${product.product_id}</td>
          <td>${product.name}</td>
          <td>${product.quantity_in_stock}</td>
          <td>${product.unit_price}</td>

          <td><a href="${pageContext.request.contextPath}/SQLInventoryUpdateProduct?command=updateProduct&product_id=${product.product_id}">Update</a></td>

          <td><a href="${pageContext.request.contextPath}/SQLInventoryDeleteProduct?command=delete&product_id=${product.product_id}">Delete</a></td>
        </tr>
      </c:forEach>
    </table>
    <hr />
    <a href="${pageContext.request.contextPath}/SQLInventoryInsertProduct?command=insertProduct">Insert new product</a>
  </body>
</html>
