package pjatk.tpo.demo3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DatabaseInitializer {
    private static final String JDBC_URL = "jdbc:derby:myDB;create=true";
    private static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private  static  final  String USER = "postgres";
    private  static  final  String PASSWORD = "1234";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static final String[] DROP_TABLE_QUERIES = {
            "DROP TABLE OrderDetails",
            "DROP TABLE Orders",
            "DROP TABLE Products",
            "DROP TABLE Users"
    };

    private static final String[] CREATE_TABLE_QUERIES = {
            "CREATE TABLE Users (" +
                    "UserID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                    "UserName VARCHAR(100) NOT NULL, " +
                    "Email VARCHAR(100) NOT NULL UNIQUE, " +
                    "PasswordHash VARCHAR(255) NOT NULL, " +
                    "CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP)",

            "CREATE TABLE Products (" +
                    "ProductID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                    "ProductName VARCHAR(255) NOT NULL, " +
                    "ProductDescription CLOB, " +
                    "Price DECIMAL(10, 2) NOT NULL, " +
                    "Stock INT NOT NULL, " +
                    "Category VARCHAR(100), " +
                    "CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP)",

            "CREATE TABLE Orders (" +
                    "OrderID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                    "UserID INT NOT NULL, " +
                    "OrderDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    "TotalAmount DECIMAL(10, 2) NOT NULL, " +
                    "Status VARCHAR(50) DEFAULT 'Pending', " +
                    "FOREIGN KEY (UserID) REFERENCES Users(UserID))",

            "CREATE TABLE OrderDetails (" +
                    "OrderDetailID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                    "OrderID INT NOT NULL, " +
                    "ProductID INT NOT NULL, " +
                    "Quantity INT NOT NULL, " +
                    "Price DECIMAL(10, 2) NOT NULL, " +
                    "FOREIGN KEY (OrderID) REFERENCES Orders(OrderID), " +
                    "FOREIGN KEY (ProductID) REFERENCES Products(ProductID))"
    };

    private static final String[] IMPORT_DATA_QUERIES = {
            "INSERT INTO Users (UserName, Email, PasswordHash) VALUES " +
                    "('JohnDoe', 'johndoe@example.com', 'hashedpassword1'), " +
                    "('JaneSmith', 'janesmith@example.com', 'hashedpassword2'), " +
                    "('AliceJones', 'alicejones@example.com', 'hashedpassword3'), " +
                    "('BobBrown', 'bobbrown@example.com', 'hashedpassword4'), " +
                    "('CharlieBlack', 'charlieblack@example.com', 'hashedpassword5'), " +
                    "('DavidWhite', 'davidwhite@example.com', 'hashedpassword6'), " +
                    "('EvaGreen', 'evagreen@example.com', 'hashedpassword7'), " +
                    "('FrankBlue', 'frankblue@example.com', 'hashedpassword8'), " +
                    "('GraceYellow', 'graceyellow@example.com', 'hashedpassword9'), " +
                    "('HannahRed', 'hannahred@example.com', 'hashedpassword10')",

            "INSERT INTO Products (ProductName, ProductDescription, Price, Stock, Category) VALUES " +
                    "('Laptop', 'High performance laptop', 999.99, 50, 'Electronics'), " +
                    "('Smartphone', 'Latest model smartphone', 599.99, 150, 'Electronics'), " +
                    "('Coffee Maker', 'Automatic coffee maker', 89.99, 75, 'Home Appliances'), " +
                    "('Blender', 'High-speed blender', 49.99, 100, 'Home Appliances'), " +
                    "('Book', 'Bestselling novel', 19.99, 200, 'Books'), " +
                    "('Jeans', 'Comfortable jeans', 39.99, 80, 'Clothing'), " +
                    "('Running Shoes', 'Lightweight running shoes', 79.99, 120, 'Sports'), " +
                    "('Headphones', 'Noise-cancelling headphones', 129.99, 90, 'Electronics'), " +
                    "('Desk Lamp', 'Adjustable desk lamp', 29.99, 60, 'Home Appliances'), " +
                    "('Backpack', 'Durable backpack', 49.99, 110, 'Accessories')",

            "INSERT INTO Orders (UserID, TotalAmount, Status) VALUES " +
                    "(1, 1099.98, 'Completed'), " +
                    "(2, 599.99, 'Pending'), " +
                    "(3, 169.98, 'Completed'), " +
                    "(4, 39.99, 'Shipped'), " +
                    "(5, 119.98, 'Pending'), " +
                    "(6, 209.97, 'Cancelled'), " +
                    "(7, 259.98, 'Shipped'), " +
                    "(8, 399.99, 'Completed'), " +
                    "(9, 79.99, 'Pending'), " +
                    "(10, 89.98, 'Completed')",

            "INSERT INTO OrderDetails (OrderID, ProductID, Quantity, Price) VALUES " +
                    "(1, 1, 1, 999.99), " +
                    "(1, 3, 1, 89.99), " +
                    "(2, 2, 1, 599.99), " +
                    "(3, 5, 2, 19.99), " +
                    "(4, 6, 1, 39.99), " +
                    "(5, 4, 2, 49.99), " +
                    "(6, 7, 3, 79.99), " +
                    "(7, 8, 2, 129.99), " +
                    "(8, 1, 1, 999.99), " +
                    "(8, 9, 1, 29.99), " +
                    "(9, 7, 1, 79.99), " +
                    "(10, 10, 2, 49.99)"
    };

    private static final String[] SHOW_TABLE_QUERIES = {
            "SELECT * FROM Users",
            "SELECT * FROM Products",
            "SELECT * FROM Orders",
            "SELECT * FROM OrderDetails"
    };

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL);
    }

    public static void initializeDatabase() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            dropTables(connection);
            createTables(connection);
            insertData(connection);
            showTableData(connection);
            System.out.println("Database initialized successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void dropTables(Connection connection) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            for (String query : DROP_TABLE_QUERIES) {
                try {
                    stmt.executeUpdate(query);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void createTables(Connection connection) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            for (String query : CREATE_TABLE_QUERIES) {
                stmt.executeUpdate(query);
            }
        }
    }

    private static void insertData(Connection connection) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            for (String query : IMPORT_DATA_QUERIES) {
                stmt.executeUpdate(query);
            }
        }
    }

    private static void showTableData(Connection connection) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            for (String query : SHOW_TABLE_QUERIES) {
                try (ResultSet resultSet = stmt.executeQuery(query)) {
                    while (resultSet.next()) {
                        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                            System.out.print(resultSet.getMetaData().getColumnName(i) + ": ");
                            System.out.print(resultSet.getString(i) + " ");
                        }
                        System.out.println();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        initializeDatabase();
    }
}
