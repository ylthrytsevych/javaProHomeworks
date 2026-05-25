package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {

        CoffeeOrderBoard board = new CoffeeOrderBoard();

        Order order1 = new Order("Alen");
        Order order2 = new Order("Yoda");
        Order order3 = new Order("Obi-van");

        board.add(order1);
        board.add(order2);
        board.add(order3);

        board.deliver(-50);//warn

        board.draw();
        Thread.sleep(1000);
        board.deliver();
        Thread.sleep(1000);
        board.draw();
        board.add(new Order("JJ"));
        board.add(new Order("JJ2"));
        board.add(new Order("JJ3"));
        board.add(new Order("JJ4"));
        board.add(new Order("JJ5"));
        Thread.sleep(1000);
        board.draw();

        Thread.sleep(1000);
        board.deliver(4);

        Thread.sleep(1000);
        board.draw();
        Thread.sleep(1000);
        board.deliver();
        Thread.sleep(1000);
        board.draw();
        Thread.sleep(1000);
        board.deliver(1);
        board.deliver(6);
        board.deliver(3);

        Thread.sleep(1000);
        board.draw();

        for (int i = 0; i<4;i++){
            Thread.sleep(1000);
            board.deliver();
        }
        Thread.sleep(1000);
        board.draw();


        // тестуємо ERROR
        try {
            logger.info("Спроба додати завідомо некоректне замовлення...");
            board.add(new Order("")); // Exception
        } catch (IllegalArgumentException e) {
            logger.error("Вийняток додавання замовлення: {}", e.getMessage(), e);
        }
    }
}
