package hw11.app;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHandler {

    public String createFile(String path) {
        Path newFile;
        try {
            //додав створення папки
            Path path_p = Path.of(path);
            if (path_p.getParent() != null) {
                Files.createDirectories(path_p.getParent());
            }

            newFile = Files.createFile(path_p);
        } catch (FileAlreadyExistsException e) {
            return "File already exists!";
        } catch (IOException e) {
            return "Something wrong " + e.getMessage();
        }
        return "Created " + newFile;
    }

    public String writeToFile(Path path, String content) {
        try {
            Files.writeString(path, content);
        } catch (IOException e) {
            return e.getMessage();
        }
        return "Recorded in " + path;
    }

    public String readFromFile(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            return "Something wrong " + e.getMessage();
        }
    }
}

