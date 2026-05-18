import org.hrytseyvch.PasswordGenerator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n=====Генератор випадкових паролів=====");

        Scanner sc = new Scanner(System.in);
        System.out.println("Введіть число символів для генерації паролю:");
        int len = sc.nextInt();

        System.out.printf("%nВаш новий пароль на %d символів:%n",len);
        System.out.println(PasswordGenerator.generatePassword(len));
    }
}
