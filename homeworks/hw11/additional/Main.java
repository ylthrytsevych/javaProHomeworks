package hw11.additional;

import java.io.IOException;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {
        FileHandler handler = new FileHandler();
        String path = "files/info.txt";
        handler.createFile(Path.of(path)); //кастомний метод створення файлу і рандомізованого тексту
        getOutput("FILE CONTENT: " + handler.readFromFile(path));
    }

    private static void getOutput(String output) {
        System.out.println(output);
    }
}