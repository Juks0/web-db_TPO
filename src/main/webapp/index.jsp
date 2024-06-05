<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Wyszukiwarka zamówień</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-image: url('plane.jpg'); /* ścieżka do obrazu */
      background-size: cover;
      background-position: center;
      margin: 0;
      padding: 0;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }

    .container {
      background-color: rgba(255, 255, 255, 0.8); /* dodatkowa warstwa aby tekst był czytelny */
      border-radius: 10px;
      padding: 20px;
      box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
    }

    form {
      display: flex;
      flex-direction: column;
      align-items: center;
    }

    label {
      margin-bottom: 10px;
    }

    input[type="text"],
    select {
      padding: 10px;
      margin-bottom: 15px;
      border: 1px solid #ccc;
      border-radius: 5px;
      width: 300px;
    }

    input[type="submit"] {
      padding: 10px 20px;
      background-color: #4CAF50;
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      transition: background-color 0.3s;
    }

    input[type="submit"]:hover {
      background-color: #45a049;
    }

    .image-container {
      text-align: center;
      margin-top: 20px;
    }

    .image-container img {
      max-width: 100%;
      border-radius: 10px;
      box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Wyszukiwarka zamówień</h1>
  <form action="search-orders" method="GET">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username">

    <label for="product">Product:</label>
    <input type="text" id="product" name="product">

    <label for="order-date">Order Date (YYYY-MM-DD):</label> <!-- Changed the label to match the order date -->
    <input type="text" id="order-date" name="order-date"> <!-- Changed the name to match the order date parameter -->

    <input type="submit" value="Szukaj">
  </form>
</div>

<div class="image-container">
  <!-- Tutaj możesz umieścić inne elementy lub jeśli nie jest potrzebny, usuń ten blok -->
</div>
</body>
</html>
