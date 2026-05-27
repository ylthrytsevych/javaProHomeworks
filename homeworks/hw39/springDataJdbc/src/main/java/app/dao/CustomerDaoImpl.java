package app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import app.entity.Customer;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    private final JdbcOperations jdbcOperations;
    private final RowMapper<Customer> customerRowMapper;

    @Autowired
    public CustomerDaoImpl(JdbcOperations jdbcOperations, RowMapper<Customer> customerRowMapper) {
        this.jdbcOperations = jdbcOperations;
        this.customerRowMapper = customerRowMapper;
    }

    @Override
    public void createTable() {
        jdbcOperations.execute("DROP TABLE IF EXISTS customer"); //reset

        String sql = """
                CREATE TABLE IF NOT EXISTS customer (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    full_name VARCHAR(255) NOT NULL,
                    email VARCHAR(255) NOT NULL,
                    ssn VARCHAR(255) NOT NULL
                );
                """;
        jdbcOperations.execute(sql);
        System.out.println("Таблиця 'customer' готова до роботи.");
    }

    @Override
    public void create(Customer customer) {
        Objects.requireNonNull(customer, "Кастомер не може бути null");
        String sql = "INSERT INTO customer (full_name, email, ssn) VALUES (?, ?, ?)";
        jdbcOperations.update(sql, customer.getFullName(), customer.getEmail(), customer.getSocialSecurityNumber());
    }

    @Override
    public Optional<Customer> findById(Long id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        // query() повертає список
        List<Customer> result = jdbcOperations.query(sql, customerRowMapper, id);
        return result.stream().findFirst(); //нам треба лише один, або ніхто (опшнал)
    }

    @Override
    public List<Customer> findAll() {
        String sql = "SELECT * FROM customer";
        // Завдяки RowMapper, Spring сам пройдеться по всіх рядках і поверне готовий List
        return jdbcOperations.query(sql, customerRowMapper);
    }

    @Override
    public void update(Customer customer) {
        if (customer == null || !customer.hasId()) {
            throw new IllegalArgumentException("Для оновлення кастомер повинен мати ID");
        }
        String sql = "UPDATE customer SET full_name = ?, email = ?, ssn = ? WHERE id = ?";
        jdbcOperations.update(sql, customer.getFullName(), customer.getEmail(), customer.getSocialSecurityNumber(), customer.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM customer WHERE id = ?";
        jdbcOperations.update(sql, id); //окремої команди деліт нема
    }
}