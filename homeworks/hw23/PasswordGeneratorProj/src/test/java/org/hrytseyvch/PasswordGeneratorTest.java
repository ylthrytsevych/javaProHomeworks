package org.hrytseyvch;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordGeneratorTest {

    @Test
    void testPasswordLength() {
        int expectedLength = 15;
        String password = PasswordGenerator.generatePassword(expectedLength);

        assertNotNull(password, "Пароль не має бути null");
        assertEquals(expectedLength, password.length(), "Довжина пароля має відповідати заданій");
    }

    @Test
    void testPasswordRandomness() {
        String pass1 = PasswordGenerator.generatePassword(12);
        String pass2 = PasswordGenerator.generatePassword(12);
        assertNotEquals(pass1, pass2, "Два згенеровані паролі не мають бути однаковими, хоча шанс на мільярд є :)");
    }

    @Test
    void testWromgLength() {
        Exception exception0 = assertThrows(IllegalArgumentException.class, () -> {
            PasswordGenerator.generatePassword(0);
        });
        assertEquals("Довжина пароля має бути більше 0", exception0.getMessage());

        assertThrows(IllegalArgumentException.class, () -> {
            PasswordGenerator.generatePassword(-5);
        });
    }

    @Test
    void testPasswordContent() {
        String password = PasswordGenerator.generatePassword(100); // беремо велику довжину для гарантії
        boolean hasSpecialOrDigit = password.matches(".*[0-9!@#$%&*()_+\\-=\\[\\]?].*");
        assertTrue(hasSpecialOrDigit, "Пароль має містити цифри або спеціальні символи");
    }
}