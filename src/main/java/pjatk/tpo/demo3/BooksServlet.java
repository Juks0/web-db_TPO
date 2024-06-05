package pjatk.tpo.demo3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "BooksServlet", urlPatterns = "/books")
public class BooksServlet extends HttpServlet {

    static {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void serviceRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:derby:myDB;create=true");

            String searchTerm = req.getParameter("search");

            String query;
            if (searchTerm != null && !searchTerm.isEmpty()) {
                query = "SELECT * FROM Products WHERE ProductName LIKE ?";
            } else {
                query = "SELECT * FROM Products";
            }

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if (searchTerm != null && !searchTerm.isEmpty()) {
                preparedStatement.setString(1, "%" + searchTerm + "%");
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            resp.setContentType("text/html; charset=utf-8");
            PrintWriter out = resp.getWriter();

            out.println("<h2>Wyszukiwarka książek</h2>");
            out.println("<form method='get' action='books'>");
            out.println("<input type='text' name='search' placeholder='Wyszukaj książkę...'>");
            out.println("<input type='submit' value='Szukaj'>");
            out.println("</form>");

            out.println("<h2>Lista dostępnych książek</h2>");
            out.println("<p>ProductID - ProductName - ProductDescription - Price - Stock - Category - CreatedAt</p>");

            while (resultSet.next()) {
                int productId = resultSet.getInt("ProductID");
                String productName = resultSet.getString("ProductName");
                String productDescription = resultSet.getString("ProductDescription");
                double price = resultSet.getDouble("Price");
                int stock = resultSet.getInt("Stock");
                String category = resultSet.getString("Category");
                Date createdAt = resultSet.getDate("CreatedAt");

                out.println("<p>" + productId + " - " + productName + " - " + productDescription + " - " + price + " - " + stock + " - " + category + " - " + createdAt + "</p>");
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        serviceRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        serviceRequest(request, response);
    }
}
