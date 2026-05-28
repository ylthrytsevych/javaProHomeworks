package app.service;

import app.model.Order;
import app.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;

    @Autowired
    public OrderServiceImpl(final OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public List<Order> getAll() {
        return repository.getAll();
    }

    @Override
    public void addOrder(Order order) {
        repository.add(order);
    }
}