package pjatk.tpo.demo3;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "orderSearch", value = "/search-orders")
public class OrderSearch extends HttpServlet {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "1234";
    private static final String DRIVER = "org.postgresql.Driver";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = new ArrayList<>();

        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            String query = "SELECT Orders.OrderID, Users.UserName, Orders.ProductName, Orders.Quantity, Orders.Price, Orders.OrderDate FROM Orders JOIN Users ON Orders.UserID = Users.UserID WHERE 1=1";

            String username = request.getParameter("username");
            String product = request.getParameter("product");
            String orderDate = request.getParameter("order-date");

            if (username != null && !username.isEmpty()) {
                query += " AND Users.UserName LIKE ?";
            }
            if (product != null && !product.isEmpty()) {
                query += " AND Orders.ProductName LIKE ?";
            }
            if (orderDate != null && !orderDate.isEmpty()) {
                query += " AND Orders.OrderDate::date = ?";
            }

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                int parameterIndex = 1;
                if (username != null && !username.isEmpty()) {
                    statement.setString(parameterIndex++, "%" + username + "%");
                }
                if (product != null && !product.isEmpty()) {
                    statement.setString(parameterIndex++, "%" + product + "%");
                }
                if (orderDate != null && !orderDate.isEmpty()) {
                    statement.setDate(parameterIndex++, Date.valueOf(orderDate));
                }

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Order order = new Order();
                        order.setOrderId(resultSet.getInt("OrderID"));
                        order.setUserName(resultSet.getString("UserName"));
                        order.setProductName(resultSet.getString("ProductName"));
                        order.setQuantity(resultSet.getInt("Quantity"));
                        order.setPrice(resultSet.getBigDecimal("Price"));
                        order.setOrderDate(resultSet.getDate("OrderDate"));
                        orders.add(order);
                    }
                }
            }

        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }
}
