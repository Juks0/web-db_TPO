import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class DisplayAllData {
   private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();

            // Pobierz nazwy wszystkich tabel
            ResultSet tableNamesResult = connection.getMetaData().getTables(null, null, "%", null);
            while (tableNamesResult.next()) {
                String tableName = tableNamesResult.getString(3); // Nazwa tabeli

                // Wykonaj zapytanie dla każdej tabeli
                ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);

                // Wyświetl dane tabeli
                System.out.println("Tabela: " + tableName);
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(metaData.getColumnName(i) + "\t");
                }
                System.out.println(); // Nowa linia po nazwach kolumn

                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(resultSet.getString(i) + "\t");
                    }
                    System.out.println(); // Nowa linia po każdym wierszu
                }
                System.out.println(); // Pusta linia między tabelami
            }

            // Zamknij zasoby
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}