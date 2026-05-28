package app.service;

import app.model.Order;

import java.util.List;

public interface OrderService {
    Order getById(Long id);
    List<Order> getAll();
    void addOrder(Order order);
}