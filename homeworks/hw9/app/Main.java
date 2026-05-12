package hw9.app;

/*
Products: (1) apple (2) grape (3) mango (4) apple (5) orange
Sales, EUR: (1) 1520.89 (2) 2058.35 (3) 1807.29 (4) 899.99 (5) 1924.25
 */

public class Main {

    public static void main(String[] args) {

        DataProvider provider = new DataProvider();
        DataHandler dataHandler = new DataHandler();

        // Обробка масиву даних найменувань товарів
        String namesOutput = dataHandler.handleData(provider.getProductNames());
        getOutput("Products: " + namesOutput);

        // Обробка масиву даних про суми продажів
        String salesOutput = dataHandler.handleData(provider.getSalesAmounts());
        getOutput("Sales, EUR: " + salesOutput);
    }

    // Виведення результату роботи програми
    private static void getOutput(String output) {
        System.out.println(output);
    }
}
