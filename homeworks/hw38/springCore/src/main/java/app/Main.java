package app;

import java.util.Scanner;

import app.repository.ProductRepository;
import app.service.Cart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        // ініціалізація Spring контексту
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // отримуємо залежності по ІНТЕРФЕЙСУ
        ProductRepository repository = context.getBean(ProductRepository.class);
        Cart cart = context.getBean(Cart.class); // Отримали новий екземпляр Prototype

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ласкаво просимо до магазину!");

        while (true) {
            System.out.println("\n--- МЕНЮ ---");
            System.out.println("1. Показати всі товари в магазині");
            System.out.println("2. Додати товар до кошика (за ID)");
            System.out.println("3. Видалити товар з кошика (за ID)");
            System.out.println("4. Показати мій кошик");
            System.out.println("5. Вихід");
            System.out.print("Оберіть дію: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("\n--- Каталог товарів ---");
                    for (Product p : repository.findAll()) {
                        System.out.println(p.toString());
                    }
                    break;
                case "2":
                    System.out.print("Введіть ID товару: ");
                    int addId = Integer.parseInt(scanner.nextLine());
                    cart.addProductById(addId);
                    break;
                case "3":
                    System.out.print("Введіть ID товару: ");
                    int removeId = Integer.parseInt(scanner.nextLine());
                    cart.removeProductById(removeId);
                    break;
                case "4":
                    System.out.println("\n--- Ваш кошик ---");
                    cart.showCart();
                    break;
                case "5":
                    System.out.println("Дякуємо за покупки! До побачення.");
                    return;
                default:
                    System.out.println("Невідома команда!");
            }
        }
    }
}
