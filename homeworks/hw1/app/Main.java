package app;

class Main {

    public static final double CONV_K = 2.20462;

    public static void main(String[] args) {
        System.out.println("Converter app");

        double kgs = 5;
        double pounds = convKgsToPounds(kgs);
        System.out.printf("Result is %.2f pounds.%n", pounds);
    }

    private static double convKgsToPounds(double kgs) {
        return kgs * CONV_K;
    }
}