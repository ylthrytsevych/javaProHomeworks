package app.repository;

import app.model.Order;
import java.util.List;

public interface OrderRepository {
    Order getById(Long id);
    List<Order> getAll();
    void add(Order order);
}