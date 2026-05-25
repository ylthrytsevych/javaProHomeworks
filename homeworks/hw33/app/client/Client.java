package hw33.app.client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);
             BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter serverOutput = new PrintWriter(socket.getOutputStream(), true)) {          // try-with-resources автоматично закривати сокет

            System.out.println("Підключено до сервера! Вводьте повідомлення (або 'exit' для виходу):");

            ServerListener listener = new ServerListener(serverInput);
            Thread responseListenerThread = new Thread(listener);
            responseListenerThread.start();

            Scanner consoleScanner = new Scanner(System.in);
            while (true) {
                String userInput = consoleScanner.nextLine();
                serverOutput.println(userInput);

                if (userInput.equalsIgnoreCase("exit")) {
                    System.out.println("Відключення від сервера...");
                    break;
                }
            }

        } catch (IOException e) {
            System.err.println("Помилка підключення: " + e.getMessage());
        }
    }
}