<%@ page import="pjatk.tpo.demo3.Order" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Danfo&display=swap" rel="stylesheet">
  <style>
    body {
      background-image: url('plane.jpg');
      background-size: cover;
      background-repeat: no-repeat;
      background-attachment: fixed;
    }
    .danfo-h1 {
      font-family: 'Danfo', serif;
      font-optical-sizing: auto;
      font-weight: 400;
      font-style: normal;
      font-variation-settings: "ELSH" 0;
      padding: 20px 10px;
      margin: 10px 520px;
      width: 1000px;
      border: 10px;
      letter-spacing: 5px;
      word-spacing: 10px;
    }
    table {
      width: 80%;
      margin: 50px auto;
      background-color: rgba(33, 43, 51, 0.7);
      border-collapse: collapse;
      color: white;
    }
    table, th, td {
      border: 1px solid white;
    }
    th, td {
      padding: 10px;
      text-align: center;
    }
  </style>
  <title>Order Search Results</title>
</head>
<body>
<h1 class="danfo-h1">Search Results</h1>
<% if (request.getAttribute("orders") == null || ((List<Order>) request.getAttribute("orders")).isEmpty()) { %>
<p>No orders found</p>
<% } else { %>
<table>
  <thead>
  <tr>
    <th>Order ID</th>
    <th>User Name</th>
    <th>Product Name</th>
    <th>Quantity</th>
    <th>Price (PLN)</th>
    <th>Order Date</th>
  </tr>
  </thead>
  <tbody>
  <% List<Order> orders = (List<Order>) request.getAttribute("orders");
    for (Order order : orders) { %>
  <tr>
    <td><%= order.getOrderId() %></td>
    <td><%= order.getUserName() %></td>
    <td><%= order.getProductName() %></td>
    <td><%= order.getQuantity() %></td>
    <td><%= order.getPrice() %></td>
    <td><%= order.getOrderDate() %></td>
  </tr>
  <% } %>
  </tbody>
</table>
<% } %>
</body>
</html>
