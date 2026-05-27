package app.dao;

import app.entity.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    void createTable();
    void create(Customer customer);
    Optional<Customer> findById(Long id);
    List<Customer> findAll();
    void update(Customer customer);
    void delete(Long id);
}