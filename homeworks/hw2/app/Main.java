package hw2.app;

class Main {

    public static final double CONV_M_TO_KM = 1.60934;

    public static void main(String[] args) {
        System.out.println("Converter app");

        double miles = 5;
        double kilms = 10;
        double KMS = convMilesToKms(miles);
        double MILS = convKmsToMiles(kilms);
        System.out.printf("Result are: %n%.0f miles is %.2f kilometers.%n%.0f kilometers is %.2f miles%n",
                miles, KMS, kilms, MILS);
    }

    private static double convMilesToKms(double mils) {
        return mils * CONV_M_TO_KM;
    }

    private static double convKmsToMiles(double kms) {
        return kms / CONV_M_TO_KM;
    }
}