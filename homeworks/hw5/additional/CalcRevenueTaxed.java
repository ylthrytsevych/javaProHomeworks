package hw5.additional;

// Клас розрахунку доходу,
// враховуючи податок на доход.
// Клас успадковує клас CalcRevenueBase.
public class CalcRevenueTaxed extends CalcRevenueBase {

    // Ставка податку на доход у відсотках
    private  double TAX_RATE = 5;

    @Override
    public double calcRevenue(Sale sale) {
        return (super.calcRevenue(sale)) * (1 - TAX_RATE / 100);
    }
}