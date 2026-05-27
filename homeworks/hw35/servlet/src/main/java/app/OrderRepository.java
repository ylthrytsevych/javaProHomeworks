package app;

public interface OrderRepository {

    void save(Order order);

    Order getById(int id);

    void deleteById(int id);
}
