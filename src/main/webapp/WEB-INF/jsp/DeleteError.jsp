<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%> <%@taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <title>DELETE ERROR</title>
  </head>
  <body>
    <h1>DELETE ERROR OCCURED</h1>
    <h5 style="color: red">${ERROR}</h5>
    <a href="${pageContext.request.contextPath}/SQLInventoryProductsList"
      >Product List</a
    >
  </body>
</html>
