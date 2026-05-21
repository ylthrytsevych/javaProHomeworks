package hw29.app;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public void addEmployee(Employee employee) {

        String sql = "INSERT INTO employees (name, age, position, salary) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.getConnection()) {
            PreparedStatement prReq = conn.prepareStatement(sql);
                //id autoincr

            // перший int в пераметрах - це індекс зноаку ? ? ? ? в запиті - не провтикатись
            prReq.setString(1, employee.getName());
            prReq.setInt(2, employee.getAge());
            prReq.setString(3, employee.getPosition());
            prReq.setFloat(4, employee.getSalary());

            prReq.executeUpdate(); //executeQuery не працює на оновлення
            // executeQuery мабуть лише для sleect
            // execute - для збережених продцедур
            System.out.println("Співробітника [" + employee.getName() + "] додано в базу даних.");

        } catch (SQLException e) {
            System.err.println("Помилка додавання: " + e.getMessage());
        }
    }

//замінсть передачі індеска в параметрах можна його зразу витягнути з оновленого обєтка
    public void updateEmployee(Employee employee) {
        String sql = "UPDATE employees SET name = ?, age = ?, position = ?, salary = ? WHERE id = ?";

        try (Connection conn = DatabaseConnector.getConnection()) {

            PreparedStatement prReq = conn.prepareStatement(sql);
            prReq.setString(1, employee.getName());
            prReq.setInt(2, employee.getAge());
            prReq.setString(3, employee.getPosition());
            prReq.setFloat(4, employee.getSalary());
            prReq.setInt(5, employee.getId());

            prReq.executeUpdate();
            System.out.println("Дані співробітника з ID " + employee.getId() + " оновлено.");

        } catch (SQLException e) {
            System.err.println("Помилка оновлення: " + e.getMessage());
        }
    }

    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement prReq = conn.prepareStatement(sql)) {

            prReq.setInt(1, id);
            prReq.executeUpdate();
            System.out.println("Співробітника з ID " + id + " видалено з бази даних.");

        } catch (SQLException e) {
            System.err.println("Помилка видалення: " + e.getMessage());
        }
    }


    public Employee getEmployeeById(int id) {
        String sql = "SELECT * FROM employees WHERE id = ?";

        try (Connection conn = DatabaseConnector.getConnection()) {
            PreparedStatement prReq = conn.prepareStatement(sql);
            prReq.setInt(1, id);
            try (ResultSet rs = prReq.executeQuery()) {
                if (rs.next()) {
                    return new Employee( //чи треба нам тут заводити новий конструктор із id? теоретично щоб витягнути його потім в апдейт
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("age"),
                            rs.getString("position"),
                            rs.getFloat("salary")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Помилка отримання за ID: " + e.getMessage());
        }
        return null;//тут якщо нічо не знайшло
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        try (Connection conn = DatabaseConnector.getConnection()) {

            Statement req = conn.createStatement();
            ResultSet rs = req.executeQuery(sql);
            while (rs.next()) {
                employees.add(new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("position"),
                        rs.getFloat("salary")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Помилка отримання всіх записів: " + e.getMessage());
        }
        return employees;
    }
}
