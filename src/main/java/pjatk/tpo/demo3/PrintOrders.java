package pjatk.tpo.demo3;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PrintOrders {
    public static void print(HttpServletRequest request, HttpServletResponse response, Connection connection, PreparedStatement statement) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                out.print("No results found\n\n");
            } else {
                out.println("<html><body>");
                out.println("<table border='1'>");
                out.println("<tr>");
                out.println("<th>OrderID</th>");
                out.println("<th>Username</th>");
                out.println("<th>Product</th>");
                out.println("<th>Total Amount</th>");
                out.println("<th>Status</th>");
                out.println("</tr>");

                do {
                    int orderId = resultSet.getInt("OrderID");
                    String user = resultSet.getString("UserName");
                    String product = resultSet.getString("ProductName");
                    double amount = resultSet.getDouble("TotalAmount");
                    String status = resultSet.getString("Status");

                    out.println("<tr>");
                    out.println("<td>" + orderId + "</td>");
                    out.println("<td>" + user + "</td>");
                    out.println("<td>" + product + "</td>");
                    out.println("<td>" + amount + "</td>");
                    out.println("<td>" + status + "</td>");
                    out.println("</tr>");
                } while (resultSet.next());

                out.println("</table>");
                out.println("</body></html>");
            }

            out.println("<a href=\"index.jsp\">Back to homepage</a>");

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
