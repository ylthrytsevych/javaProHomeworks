package hw14.app.StreamTask;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StreamTaskTest {

    private final StreamTask task = new StreamTask();

    @Test
    void getElementLength_ShouldReturnLengths() {
        List<Integer> result = task.getElementLength(List.of("Java", "Stream", ""));
        assertEquals(List.of(4, 6, 0), result);

        assertTrue(task.getElementLength(List.of()).isEmpty());
    }

    @Test
    void getElementLengthAndDistinct_ShouldRemoveDuplicateStrings() {
        List<String> input = List.of("apple", "apple", "pear");
        List<Integer> result = task.getElementLengthAndDistinct(input);
        assertEquals(List.of(5, 4), result);
    }

    @Test
    void getFirstCharacterOfString() {
        List<Character> result = task.getFirstCharacterOfString(List.of("Apple", "Banana"));
        assertEquals(List.of('A', 'B'), result);
    }

    @Test
    @Disabled
    void getFirstCharacterOfString_ShouldThrowExceptionOnEmptyString() {
        assertThrows(StringIndexOutOfBoundsException.class, () -> {
            task.getFirstCharacterOfString(List.of(""));
        });
    }

    @Test
    void getFirstCharacterOfString_ShouldIgnoreEmptyStrings() {
        List<String> input = List.of("Apple", "", "Banana");

        List<Character> result = task.getFirstCharacterOfString(input);

        assertEquals(List.of('A', 'B'), result);
    }

    @Test
    void getFirstCharacterOfString_ShouldReturnEmptyListForEmptyStrings() {
        List<String> input = List.of("", "");

        List<Character> result = task.getFirstCharacterOfString(input);

        assertTrue(result.isEmpty());
    }

    @Test
    void getFullName_ShouldConcatenateNames() {
        User user1 = new User("John", "Doe");
        User user2 = new User("Anna", "Smith");

        List<String> result = task.getFullName(List.of(user1, user2));
        assertEquals(List.of("John Doe", "Anna Smith"), result);
    }

    @Test
    void rotateElements_ShouldWorkCorrectly() {
        assertEquals(List.of("b", "c", "d", "a"), task.rotateElements(List.of("a", "b", "c", "d")));

        assertEquals(List.of("a"), task.rotateElements(List.of("a")));

        assertTrue(task.rotateElements(List.of()).isEmpty());
    }

}