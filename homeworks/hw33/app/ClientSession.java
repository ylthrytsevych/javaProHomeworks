package hw33.app;

import java.net.Socket;
import java.time.LocalDateTime;

public class ClientSession {
    private String name;
    private LocalDateTime connectionTime;
    private Socket socket;

    public ClientSession(String name, Socket socket) {
        this.name = name;
        this.socket = socket;
        this.connectionTime = LocalDateTime.now();
    }

    public String getName() { return name; }
    public Socket getSocket() { return socket; }
}
