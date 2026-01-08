package hw3.app;

public class Converter {
    public static final int FAHRENHEIT_OFFSET = 32;

    public static final double CONV_FACTOR = 5.0 / 9.0;

    public static double convertFahrenheitToCelsius(double temp) {
        return (temp - FAHRENHEIT_OFFSET) * CONV_FACTOR;
    }
}
