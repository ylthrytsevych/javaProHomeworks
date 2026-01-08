package hw3.app;

public class Main {
    public static void main(String[] args) {
        System.out.println("Temperature converter app");
        System.out.println("Version 1.2");
        System.out.println("----------");


        double tempF = 98;  //98 far = 36.6 cel
        double celsius = Converter.convertFahrenheitToCelsius(tempF);

        System.out.printf("%.1f degrees Fahrenheit is %.1f degrees Celsius.%n",
                tempF, celsius);

    }
}
