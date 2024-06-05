package pjatk.tpo.demo3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class TestDBConnection {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "1234";
    private static final String DRIVER = "org.postgresql.Driver";


    public static void main(String[] args) {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try  {
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            System.out.println("Connected to the database!");
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE Testowanieeeeee (ID INT PRIMARY KEY, NAME VARCHAR(255))");
//            statement.executeUpdate("SELECT * FROM Orders;");

            System.out.println("Table created successfully!");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
