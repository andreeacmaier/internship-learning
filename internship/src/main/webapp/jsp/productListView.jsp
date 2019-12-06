<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import = "java.io.*,java.util.*" %>
<%@ page import = "javax.servlet.http.*" %>
<%@ page import = "com.arobs.entities.Item" %>
<%@ page import = "com.arobs.entities.Order" %>
<%@ page import = "com.arobs.entities.OrderDetails" %>
<%@ page import = "com.arobs.service.ProductManager" %>
<%@ page import = "java.util.Map" %>
<%@ page import = "java.util.HashMap" %>

<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Product List</title>
 </head>
 <body>

    <h3>Product List</h3>

    <%
        ProductManager productManager = (ProductManager)session.getAttribute("productManager");
        Map<String, Integer> shoppingCart = productManager.getShoppingCart(1); %>

    <%
        if(shoppingCart.isEmpty()) {
    %>
        <p> Product list is empty. </p>
    <%
        } else {
     %>
        <table border="1" cellpadding="5" cellspacing="1" >
               <tr>
                  <th>Product</th>
                  <th>Quantity</th>

               </tr>
               <%  for (Map.Entry<String,Integer> entry : shoppingCart.entrySet())   { %>
                  <tr>
                     <td> <% out.println(entry.getKey()); %> </td>
                     <td> <% out.println(entry.getValue()); %> </td>
                  </tr>
               <% } %>
            </table>
    <%
    }%>

 </body>
</html>