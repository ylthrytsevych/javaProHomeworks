package app;

public class Order {
    private long number;
    private String name;
//    private static long lastOrder = 0;

    public Order (String name){
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Ім'я не може бути пустим чи null");
        }
        this.name = name;
//        lastOrder++; ///тут не можна зберігати порядок всіх замовлень, це сутність одного замовлення, він не має знати про інші замовлення
//        this.number = lastOrder;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public long getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return number + " | " + name;
    }
}
