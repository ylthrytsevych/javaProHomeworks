package hw25.app;

public class Client {
    public void run() {
        System.out.println("--- Початок роботи клієнта ---");

        TransportFactory carFactory = new CarFactory();
        Transport car = carFactory.createTransport();

        TransportFactory planeFactory = new PlaneFactory();
        Transport plane = planeFactory.createTransport();

        System.out.print("Тестуємо машину:\n    ");
        car.move();

        System.out.print("Тестуємо літак:\n    ");
        plane.move();

        System.out.println("--- Завершення роботи клієнта ---");
    }
}
