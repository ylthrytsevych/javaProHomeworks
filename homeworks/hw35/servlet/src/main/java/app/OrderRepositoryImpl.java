package app;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OrderRepositoryImpl implements OrderRepository{
    private final Map<Integer, Order> database = new ConcurrentHashMap<>();

    @Override
    public void save(Order order) {
        database.put(order.getId(), order);
    }

    @Override
    public Order getById(int id) {
        return database.get(id);
    }

    @Override
    public void deleteById(int id) {
        database.remove(id);
    }
}
