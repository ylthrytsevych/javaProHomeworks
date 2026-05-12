package hw8.additional;

public class TemperatureValidator {

    public static String validateTemperature(int temperature) throws TemperatureException {
        if (temperature >= -10 && temperature <= 35) {
            return "Temperature is in bounds.";
        } else {
            throw new TemperatureException("Invalid temperature.");
        }
    }
}