package hw33.app.client;

import java.io.BufferedReader;
import java.io.IOException;

public class ServerListener implements Runnable {
    private final BufferedReader serverInput;

    public ServerListener(BufferedReader serverInput) {
        this.serverInput = serverInput;
    }

    @Override
    public void run() {
        try {
            String response;
            while ((response = serverInput.readLine()) != null) {
                System.out.println(response);
            }
        } catch (IOException e) {
            // помилка і самостійне закриття сокету через команду exit
            System.out.println("З'єднання з сервером розірвано.");
        }
    }
}