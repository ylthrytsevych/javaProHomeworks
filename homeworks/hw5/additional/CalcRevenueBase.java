package hw5.additional;

// Клас розрахунку базового доходу
public class CalcRevenueBase {

    public double calcRevenue(Sale sale) {
        return sale.getQuota() * sale.getPrice();
    }
}