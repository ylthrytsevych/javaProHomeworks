package hw5.additional;

public class Main {

    public static void main(String[] args) {
        // Отримуємо вхідні дані
        String[] data = getData();
        // Формуємо об'єкт з вхідних даних
        Sale sale = new Sale(Integer.parseInt(data[0]),
                Double.parseDouble(data[1]));
        // Розраховуємо базовий дохід
        CalcRevenueBase revenueBase = new CalcRevenueBase();
        double baseRevenue = revenueBase.calcRevenue(sale);
        // Розраховуємо дохід, з урахуванням податку
        CalcRevenueTaxed revenueTaxed = new CalcRevenueTaxed();
        double taxedRevenue = revenueTaxed.calcRevenue(sale);
        // Формуємо виведення
        String baseOutput = sale + "\nRevenue is " +
                Constants.CURRENCY + " " + baseRevenue + ".";
        String taxedOutput = sale + "\nRevenue taxed is " +
                Constants.CURRENCY + " " + taxedRevenue + ".";
        // Виводимо результат
        getOutput(baseOutput);
        getOutput(taxedOutput);
    }

    // Набір вхідних даних
    public static String[] getData() {
        return new String[] { "120", "4.5"};
    }

    public static void getOutput(String output) {
        System.out.println(output);
    }
}