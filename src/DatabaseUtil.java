import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String URL = "jdbc:mysql://localhost:3307/zoologico"; // Reemplaza 'zoo' con el nombre de tu base de datos
    private static final String USER = "root"; // Reemplaza con tu usuario de MySQL
    private static final String PASSWORD = ""; // Reemplaza con tu contraseña de MySQL

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
