package hw11.additional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.SecureRandom;
import java.util.Random;

public class FileHandler {

    public String readFromFile(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            return "Something wrong " + e.getMessage();
        }
    }

    public void createFile(Path path) throws IOException {
        if (path.getParent() != null) {
            Files.createDirectories(path.getParent());
        }

        if (Files.notExists(path)) {
            Random rand = new Random();
            String randomContent = "Generated text: " + generate(rand.nextInt(10,30));
            // CREATE створює файл, WRITE дозволяє запис
            Files.writeString(path, randomContent,  StandardOpenOption.CREATE);
        }
    }

    //ну тут без ші не обійшлось, решту писав сам
    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    private static String generate(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ALPHANUMERIC.charAt(RANDOM.nextInt(ALPHANUMERIC.length())));
        }
        return sb.toString();
    }
}