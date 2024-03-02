<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <title>UPDATE THE PRODUCT</title>
  </head>
  <body>
    <h1>UPDATE THE PRODUCT</h1>
    <p style="color: red">${ERROR}</p>
    <form action="${pageContext.request.contextPath}/SQLInventoryUpdateProduct?command=update" method="POST">
      <label>product_id: </label><label>${product.product_id}</label><input type="number" name="product_id" value="${product.product_id}" hidden="true"/><br/> <label>name</label
      ><br /><input type="text" placeholder="name" name="name" value="${product.name}" /><br />
      <label>quantity_in_stock</label
      ><br /><input type="number" placeholder="quantity_in_stock" name="quantity_in_stock" value="${product.quantity_in_stock}"/><br />
      <label>unit_price</label
      ><br /><input type="number" placeholder="unit_price" name="unit_price" value="${product.unit_price}"/><br />
      <hr />
      <input type="submit" value="Update" /><a style="color: red;" href="${pageContext.request.contextPath}/SQLInventoryProductsList">Cancel</a>
    </form>
  </body>
</html>
