package hw19.app;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] numbers = new int[10];
        Random random = new Random();
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(100);
        }

        ArrayUtils utils = new ArrayUtils();
        System.out.println("Оригінальний масив: ");
        System.out.println(Arrays.toString(numbers));
        System.out.println("--------------------------------------------------");

        utils.mergeSort(numbers, 0, numbers.length-1);
        System.out.println("Відсортований масив (Merge Sort): ");
        System.out.println(Arrays.toString(numbers));
        System.out.println("--------------------------------------------------");


        int targetToFind = numbers[random.nextInt(numbers.length)];
        System.out.println("Шукаємо число: " + targetToFind);

        int resultIndex = utils.binarySearch(numbers, targetToFind);
        if (resultIndex != -1) {
            System.out.println("Результат: Число знайдено на індексі [" + resultIndex + "]");
        } else {
            System.out.println("Результат: Число не знайдено в масиві.");
        }

        // Демонстрація пошуку числа, якого точно немає (наприклад, -5)
        System.out.println("\nШукаємо число: -5");
        int notFoundIndex = utils.binarySearch(numbers, -5);
        if (notFoundIndex == -1) {
            System.out.println("Результат: Число -5 очікувано не знайдено (індекс -1).");
        }
    }
}
