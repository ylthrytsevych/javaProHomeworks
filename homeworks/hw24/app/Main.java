package hw24.app;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        System.out.println("--- Початок програми ---");
        Logger logger1 = Logger.getInstance();
        Thread.sleep(random.nextLong(500, 1200));
        logger1.log();

        Logger logger2 = Logger.getInstance();
        Thread.sleep(random.nextLong(500, 1200));
        logger2.log();

        Logger logger3 = Logger.getInstance();
        Thread.sleep(random.nextLong(500, 1200));
        logger3.log();

        System.out.println("\n--- Перевірка Singleton ---");

        // 3.a Перевіряємо, що всі виклики повертають один і той же об'єкт
        System.out.println("Чи logger1 та logger2 це один об'єкт? " + (logger1 == logger2));
        System.out.println("Чи logger2 та logger3 це один об'єкт? " + (logger2 == logger3));

        System.out.println(logger1.hashCode() + " " + logger2.hashCode() + " " + logger3.hashCode());
    }
}
