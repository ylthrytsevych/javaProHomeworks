package hw33.app;

import java.io.*;
import java.net.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {
    private static final int PORT = 8080;

    // для потокозалежних мап
    private static final ConcurrentHashMap<String, ClientSession> activeClients = new ConcurrentHashMap<>();
    // потокозалежний лічильник
    private static final AtomicInteger clientCounter = new AtomicInteger(1);

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("[SERVER] Сервер запущено. Очікування підключень на порту " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                String clientName = "Client-" + clientCounter.getAndIncrement(); //новий клікєнт

                // створюємо сесію і додаємо в мапу
                ClientSession session = new ClientSession(clientName, clientSocket);
                activeClients.put(clientName, session);
                System.out.println("[SERVER] " + clientName + " успішно підключився!");

                // обов'язково Оокремий Потік для спілкування з новим клієнтом!
                // інакше сервер не прийме інших
                ClientHandler handler = new ClientHandler(session, activeClients);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            System.err.println("Помилка сервера: " + e.getMessage());
        }
    }

}