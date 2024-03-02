<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%> <%@taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <title>INSERT NEW PRODUCT</title>
  </head>
  <body>
    <h1>INSERT NEW PRODUCT</h1>
    <p style="color: red">${ERROR}</p>
    <form action="${pageContext.request.contextPath}/SQLInventoryInsertProduct?command=insert" method="POST">
      <label>product_id</label> <br /><input type="number" name="product_id" placeholder="product_id" value="${product.product_id}"/>
      <br />
      <label>name</label> <br /><input type="text" name="name" placeholder="name" value="${product.name}"/>
      <br />
      <label>quantity_in_stock</label><br />
      <input type="number" name="quantity_in_stock" placeholder="quantity_in_stock" value="${product.quantity_in_stock}"/>
      <br />
      <label>unit_price</label><br /> <input type="number" name="unit_price" placeholder="unit_price" value="${product.unit_price}"/>
      <br />
      <hr />
      <input type="submit" value="Insert" /><a style="color: red;" href="${pageContext.request.contextPath}/SQLInventoryProductsList">Cancel</a>
    </form>
  </body>
</html>
