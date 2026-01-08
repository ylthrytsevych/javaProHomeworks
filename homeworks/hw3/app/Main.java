package hw3.app;

public class Main {
    public static void main(String[] args) {
        System.out.println("Temperature converter app");
        System.out.println("Version 1.3");
        System.out.println("----------");


        double tempF = 97.9;  //98 far = 36.6 cel
        double celsius = Converter.convertFahrenheitToCelsius(tempF);

        System.out.printf("%.1f degrees Fahrenheit is %.1f degrees Celsius.%n",
                tempF, celsius);

        System.out.println("----------");

        double tempC = 100;
        double fahrenheitResult = Converter.convertCelsiusToFahrenheit(tempC);
        System.out.printf("%.1f degrees Celsius is %.1f degrees Fahrenheit.%n",
                tempC, fahrenheitResult);


    }
}
