package app.service;

import app.Product;
import app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//компонент - Використовується для будь-яких допоміжних класів, які не є логікою або базою даних. Наприклад: утиліти, мапери, валідатори.
//Це просто деталь конструктора, яку Spring має тримати в себе в коробці
//@Component і @Service — це одне й те саме. @Service — це просто @Component з іншою назвою для зручності розробника.
//@Component підкреслює, що це "деталь", яка створюється часто (Prototype).
// @Service зазвичай підкреслює, що це "головний мозок", який існує в одному екземплярі (Singleton).
@Component
@Scope("prototype") // прототип - кожен раз буде створюватися новий кошик! сінглотон - один на апку
public class Cart implements CartService{

    private final ProductRepository productRepository;
    // Список товарів, які лежать саме в цьому кошику
    private final List<Product> cartItems = new ArrayList<>();

    // Введення залежності через конструктор. Spring сам передасть сюди ProductRepository
    @Autowired
    public Cart(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProductById(int id) {
        Product product = productRepository.findById(id);
        if (product != null) {
            cartItems.add(product);
            System.out.println("Товар '" + product.getName() + "' додано до кошика.");
        } else {
            System.out.println("Помилка: Товар з ID " + id + " не знайдено.");
        }
    }

    public void removeProductById(int id) {
        // Шукаємо товар у кошику
        boolean removed = cartItems.removeIf(product -> product.getId() == id);
        if (removed) {
            System.out.println("Товар видалено з кошика.");
        } else {
            System.out.println("Помилка: Товар з ID " + id + " відсутній у кошику.");
        }
    }

    public void showCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Ваш кошик порожній.");
            return;
        }
        System.out.println("Вміст вашого кошика:");
        double total = 0;
        for (Product item : cartItems) {
            System.out.println(" - " + item.toString());
            total += item.getPrice();
        }
        System.out.printf("💰 Загальна сума: %.2f грн%n", total);
    }


}
