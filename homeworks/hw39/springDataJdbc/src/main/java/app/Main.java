package app;
import app.config.AppConfig;
import app.dao.CustomerDao;
import app.entity.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        CustomerDao dao = ctx.getBean(CustomerDao.class);

        // ство таблиці, всередині вже є sql код на перевірку чи існує
        dao.createTable();

        System.out.println("\n--- Додавання ---");
        dao.create(new Customer("Іван Франко", "ivan@mail.com", "SSN-1111"));
        dao.create(new Customer("Леся Українка", "lesya@mail.com", "SSN-2222"));
        dao.create(new Customer("Тарас Шевченко", "taras@mail.com", "SSN-3333"));
        System.out.println("Кастомерів успішно додано!");

        List<Customer> allCustomers = dao.findAll();
        allCustomers.forEach(System.out::println);

        Long searchId = allCustomers.get(0).getId();
        System.out.println("\n--- Пошук за ID = " + searchId + " ---");
        Optional<Customer> foundCustomer = dao.findById(searchId);
        foundCustomer.ifPresent(System.out::println);

        System.out.println("\n--- Оновлення ---");
        if (foundCustomer.isPresent()) {
            Customer toUpdate = foundCustomer.get();
            toUpdate.setFullName("Іван Якович Франко"); // Змінюємо ім'я в самому об'єкті і онвлюємо
            dao.update(toUpdate);
            System.out.println("Кастомера оновлено: " + dao.findById(searchId).get());
        }

        for(int i=0; i<allCustomers.size(); i++) {
            Long deleteId = allCustomers.get(i).getId();
            System.out.println("\n--- Видалення ID = " + deleteId + " ---");
            dao.delete(deleteId);
            System.out.println("Список після видалення:");
            List<Customer> temp = dao.findAll();
            if(!temp.isEmpty())
                dao.findAll().forEach(System.out::println);
            else
                System.out.println("...список пустий.");

        }
    }
}
