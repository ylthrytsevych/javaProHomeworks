package hw9.additional;

public class Main {

    public static void main(String[] args) {

        DataProvider provider = new DataProvider();
        DataHandler dataHandler = new DataHandler();

        // Обробка масиву даних імен користувачів
        String namesOutput = dataHandler.handleData(provider.getUserNames());
        getOutput("Active users per day: " + namesOutput);

        // Обробка масиву даних кількості постів
        String postsOutput = dataHandler.handleData(provider.getPostsQuota());
        getOutput("Posts per day: " + postsOutput);
    }

    // Виведення результату роботи програми
    private static void getOutput(String output) {
        System.out.println(output);
    }
}