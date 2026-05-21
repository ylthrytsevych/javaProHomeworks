package hw29.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {
    private static String url = "jdbc:mysql://localhost:3306/company";
    private static String user = "root";
    private static String password = "rootroot";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    //No suitable driver found for jdbc:mysql://localhost:3306/company
    //не працює підключення, порада від ШІ:
    /*
        Відкрий налаштування твого проєкту в IntelliJ:
        Перейди в File -> Project Structure... (або натисни комбінацію клавіш Ctrl + Alt + Shift + S).
        У меню ліворуч обери вкладку Libraries (у розділі Project Settings).
        Натисни на маленький плюсик + вгорі середньої панелі та обери варіант From Maven...
        У рядку пошуку, який з'явиться, вставити офіційні координати драйвера MySQL:
        code
        Text
        com.mysql:mysql-connector-j:8.3.0
        Натисни на значок лупи (пошуку) праворуч від цього рядка, щоб IntelliJ перевірила його в репозиторії.
        Постав галочку біля пункту Download to... (щоб завантажити сам файл .jar у твій проєкт).
        Натисни OK [1, 2].
        У головному вікні налаштувань натисни Apply, а потім OK [1, 2].

     */

    public static void executeUpdate(String sql) {
        try (Connection conn = getConnection()){
            Statement req = conn.createStatement();
            req.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("Помилка підключення бази: " + e.getMessage());
        }
    }

    //ші метод
    public static boolean tableExists(String tableName) {
        try (Connection conn = getConnection()) {
            java.sql.DatabaseMetaData dbmd = conn.getMetaData();

            try (java.sql.ResultSet rs = dbmd.getTables(null, null, tableName, null)) {
                return rs.next(); // якщо rs.next() повернув true, значить таку таблицю знайдено!
            }
        } catch (java.sql.SQLException e) {
            System.err.println("Помилка перевірки таблиці: " + e.getMessage());
            return false;
        }
    }

}
