package hw14.app.StreamTask;

import java.util.List;
import java.util.stream.Stream;

public class StreamTask {

    // 1. Перетворити список рядків у список їх довжин
    public List<Integer> getElementLength(List<String> input) {
        return input.stream()
                .map(String::length)
                .toList();
    }

    // 2. Перетворити список рядків у їх довжини та видалити дублікати СЛІВ
    // Примітка: у вашому коді distinct() стоїть ДО map,
    // тобто видаляються однакові слова, а не однакові довжини.
    public List<Integer> getElementLengthAndDistinct(List<String> input) {
        return input.stream()
                //.distinct()
                .map(String::length)
                .distinct() //тут буде краще видалення - саме по цифрах
                .toList();
    }

    // 3. Перетворити список слів у список перших літер
    public List<Character> getFirstCharacterOfString(List<String> input) {
        return input.stream()
                .filter(s -> !s.isEmpty()) //якщо пустий то не візьме символи
                .map(s -> s.charAt(0))
                .toList();
    }

    // 4. Додати лог під час обробки
    public List<String> addLogDuringWork(List<String> input) {
        return input.stream()
                .peek(System.out::println)
                .toList();
    }

    // 5. Перетворити список Person (User) у список імен
    public List<String> getFullName(List<User> input) {
        return input.stream()
                .map(s -> s.getFirstName() + " " + s.getLastName())
                .toList();
    }

    // 6. Циклічний зсув вліво (a b c d -> b c d a)
    public List<String> rotateElements(List<String> input) {
        if (input == null || input.isEmpty()) {
            return List.of();
        }
        if (input.size() == 1) {
            return List.copyOf(input);
        }
        return Stream.concat(
                input.stream().skip(1),
                input.stream().limit(1)
        ).toList();
    }
}