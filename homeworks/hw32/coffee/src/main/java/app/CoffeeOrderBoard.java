package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CoffeeOrderBoard {
    private static final Logger logger = LoggerFactory.getLogger(CoffeeOrderBoard.class);

    private final List<Order> orderList = new ArrayList<>();
    private long lastOrderNumber = 0;

    public void add(Order order) {
        if (order != null) {
            lastOrderNumber++;
            order.setNumber(lastOrderNumber);
            orderList.add(order);
            logger.info("Нове замовлення додано: {}", order);
        } else {
            logger.warn("Була спроба додати null замовлення");
        }
    }

    public void deliver() {
        if (orderList.isEmpty()) {
            logger.warn("Видача з помилкою: черга замовлень пуста.");
            System.out.println("\nЧерга порожня, немає замовлень для видачі.");
            return;
        }
        System.out.println("\nВидано замовлення: " + orderList.get(0));
        logger.info("Замовлення видано: {}", orderList.get(0));
        orderList.remove(0);
    }

    public void deliver(long num) {
        if (num > 0) {
            orderList.stream()
                    .filter(o -> o.getNumber() == num)
                    .findFirst()
                    .ifPresentOrElse(order -> {
                        System.out.println("\n==== ПОЗАЧЕРГОВА ВИДАЧА ====");
                        System.out.println("Видано: " + order);
                        logger.info("Замовлення видано позачергово: {}", order);
                        orderList.remove(order);
                    }, () -> {                         // Якщо НЕ знайшли
                        System.out.println("\nЗамовлення №" + num + " не знайдено.");
                        logger.warn("Замовлення №{} не знайдено.", num);
                    });
        } else {
            logger.warn("Некоректний номер замовлення видачі: {}", num);
        }
    }


    public void draw() {
        logger.debug("Виклик методу видачі списку черги");

        System.out.println("\n============= ЧЕРГА =============");
        if (orderList.isEmpty()) {
            System.out.println("Черга порожня, немає замовлень для видачі.");
            logger.warn("Видача з помилкою: черга замовлень пуста.");
        } else {
            System.out.println("Num | Name");
            for (Order order : orderList) {
                System.out.println(order.getNumber() + " | " + order.getName());
            }
        }
        System.out.println("================================");
    }
}
