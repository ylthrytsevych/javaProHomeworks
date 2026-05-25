package hw33.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;


public class ClientHandler implements Runnable {
    private final ClientSession session;
    private final ConcurrentHashMap<String, ClientSession> activeClients;

    public ClientHandler(ClientSession session, ConcurrentHashMap<String, ClientSession> activeClients) {
        this.session = session;
        this.activeClients = activeClients;
    }

    @Override
    public void run() {
        String clientName = session.getName();
        try (
                BufferedReader input = new BufferedReader(new InputStreamReader(session.getSocket().getInputStream())); //читає
                PrintWriter output = new PrintWriter(session.getSocket().getOutputStream(), true) // висилає
        ) {
            String message;
            // Читаємо повідомлення від клієнта в циклі
            while ((message = input.readLine()) != null) {
                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("[SERVER] Отримано команду 'exit' від " + clientName + ". Відключаємо...");
                    break; // брейк тут = завершення потоку і закриття сокету
                }
                System.out.println("[" + clientName + "]: " + message);
                output.println("Сервер отримав твоє повідомлення: " + message);
            }
        } catch (IOException e) {
            System.err.println("Зв'язок з " + clientName + " втрачено.");
        } finally {
            activeClients.remove(clientName);
            System.out.println("[SERVER] " + clientName + " видалено зі списку активних з'єднань.");
            try {
                session.getSocket().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}