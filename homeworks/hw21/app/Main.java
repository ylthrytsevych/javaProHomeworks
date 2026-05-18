package hw21.app;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws Exception{
        int[] data = {5, 2, 8, 1, 9};
        System.out.println("Початковий масив: " + Arrays.toString(data));
        System.out.println("-----------------------------------\n");

        ArrayUtils utils = new ArrayUtils();
        Class<ArrayUtils> clazz = ArrayUtils.class;

        utils.mergeSort(data, 0, data.length-1);

//        Method mergeSortMethod = clazz.getDeclaredMethod("mergeSort");
        Method mergeSortMethod = clazz.getDeclaredMethod("mergeSort", int[].class, int.class, int.class);
        //тут код підказав ШІ, чому getDeclaredMethod не знайшов методу по імені - не розумію, але знайшов по імені і кількості параметрів, дивно

        System.out.println("Дані лише по одному методу");
        displayAnnotationInfo(mergeSortMethod);


        //--- спроба вивести всі дані
        Method[] allMethods = clazz.getDeclaredMethods();

        System.out.println("\n=========Дані по усіх методах=========\n");
        for (Method method : allMethods) {
            displayAnnotationInfo(method);

        }


    }


    private static void displayAnnotationInfo(Method method) {
        if (method.isAnnotationPresent(MethodInfo.class) && method.isAnnotationPresent(Author.class)) {
            MethodInfo info = method.getAnnotation(MethodInfo.class);
            Author author = method.getAnnotation(Author.class);

            System.out.println("ПОШУК АНОТАЦІЙ ДЛЯ МЕТОДУ: " + method.getName());

            System.out.println("Повна назва: " + info.name());
            System.out.println("Опис: " + info.description());
            System.out.println("Повертає: " + info.returnType());
            System.out.println("Автор: " + author.firstName() + " " + author.lastName()+". Створено "+author.date());
            System.out.println("-----------------------------------\n");
        }
    }
}
