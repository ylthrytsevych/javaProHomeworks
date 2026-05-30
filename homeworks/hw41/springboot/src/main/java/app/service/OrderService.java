package app.service;

import app.entity.Order;
import java.util.List;

public interface OrderService {
    Order getById(Long id);
    List<Order> getAll();
    Order create(Order order);
    Order update(Long id, Order orderDetails);
    void delete(Long id);
}