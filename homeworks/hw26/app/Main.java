package hw26.app;

public class Main {

    public static void main(String[] args) {
        System.out.println("--- Демонстрація принципу GRASP Expert ---");
        User user = new User("Julian H");
        Address address = new Address("Lviv", "str. Lvivska, 11",79032);
        user.setAddress(address);
        System.out.println("Користувач: " + user.getName());
        System.out.println("Адреса: " + user.getAddress());

    }

}
