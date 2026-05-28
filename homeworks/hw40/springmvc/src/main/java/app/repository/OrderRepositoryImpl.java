package app.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import app.model.Order;
import app.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private final List<Order> orders = new ArrayList<>();

    public OrderRepositoryImpl() {
        // ініціалізуєм тестове замовлення
        Order order = new Order(1L);
        order.addProduct(new Product(101L, "Ноутбук", 1500.0));
        order.addProduct(new Product(102L, "Мишка", 50.0));
        orders.add(order);
    }

    @Override
    public Order getById(Long id) {
        return orders.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Order> getAll() {
        return orders;
    }

    @Override
    public void add(Order order) {
        orders.add(order);
    }
}