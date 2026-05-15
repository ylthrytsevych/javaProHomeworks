package hw16.app;

import java.util.function.*;

public class Main {
    public static void main(String[] args) {

        //анонімний клас, реалізує (перевищначає) метод фукнціконального інтерфейсу
        MathOperation mathAnon = new MathOperation() {
            @Override
            public int operate(int a, int b) {
                return a+b;
            }
        };
        System.out.println("Анонімний клас каже, що 5+4 буде "+mathAnon.operate(5,4));

        //--------------

        //лямда реалізовує функціональний інтерфейс

        StringManipulator sman = (s) -> s.toUpperCase();
        String result = sman.manipulate("функціональний інтерфейс");
        System.out.println("Лямда вираз реалізовує "+result);

        //---------------

        //здається посилання на методи через функцію а не через лямбду
        Function<String, Integer> counter = StringListProcessor::countUppercase;
        int result2 = counter.apply(result);

        System.out.println("Кількість великих літер: " + result2 + " (визначено посиланням на функцію)");

        //--------------

        //саплаєр який генерує випадкове число через клас RandomNumberGenerator
        Supplier<Integer> randomIntegerSupplier = () -> RandomNumberGenerator.generateRandomNumber(1, 100);
        System.out.println("Випадкове число від саплаєра: " + randomIntegerSupplier.get());
    }
}
