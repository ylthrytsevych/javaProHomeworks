package hw29.app;

public class Main {

    public static void main(String[] args) {
        //при вході спробувати створити таблицю якщо ще нема і заодно перевіряю чи працює конект і запит
        String createTableSql = "CREATE TABLE IF NOT EXISTS employees (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "age INT NOT NULL, " +
                "position VARCHAR(100) NOT NULL, " +
                "salary FLOAT NOT NULL" +
                ")";

        System.out.println("Перевірка структури бази даних...");
        if (DatabaseConnector.tableExists("employees")) {
            System.out.println("Таблиця employees вже існує.");
        } else {
            System.out.println("Таблиці employees не знайдено в базі.");
            System.out.println("Створюємо таблицю...");
            DatabaseConnector.executeUpdate(createTableSql);
            System.out.println("Створено таблицю employees!");
        }

        EmployeeDAO dao = new EmployeeDAO();

        System.out.println("\n--- ДОДАЄМО СПІВРОБІТНИКІВ ---");
        Employee emp1 = new Employee("Alex Lion", 25, "Software Engineer", 2500.0f);
        Employee emp2 = new Employee("John Doe", 30, "Project Manager", 3000.0f);
        Employee emp3 = new Employee("Anna Smith", 22, "QA Engineer", 1800.0f);

        dao.addEmployee(emp1);
        dao.addEmployee(emp2);
        dao.addEmployee(emp3);

        System.out.println("\nВ БАЗУ ДОДАНІ: ");
        if (dao.getAllEmployees().isEmpty()) {
            System.out.println("...На жаль чомусь база ще порожня.");
        } else {
            dao.getAllEmployees().forEach(System.out::println);
        }
// міняємо посаду і зп для ід=1
        //
         //УВАГА!!!!!!!!!! ID рахуються не з 0, а з 1
        System.out.println("\n--- ОНОВЛЕННЯ ДАНИХ СПІВРОБІТНИКА (ID = 1) ---");
        Employee employeeToUpdate = dao.getEmployeeById(1);
        if (employeeToUpdate != null) {
            employeeToUpdate.setPosition("Senior Toilet Janitor");
            employeeToUpdate.setSalary(120.0f);

            dao.updateEmployee(employeeToUpdate);

            System.out.println("Оновлена сутність з бази: " + dao.getEmployeeById(1));
        }
        else{
            System.out.println("На жаль дані про співробітника не знайдені.");
        }

        System.out.println("\n--- ВИДАЛЕННЯ СПІВРОБІТНИКА (ID = 2) ---");
        dao.deleteEmployee(2);

        System.out.println("\nПОТОЧНИЙ СТАН БАЗИ:");
        if (dao.getAllEmployees().isEmpty()) {
            System.out.println("База порожня.");
        } else {
            dao.getAllEmployees().forEach(System.out::println);
        }

    }
}
