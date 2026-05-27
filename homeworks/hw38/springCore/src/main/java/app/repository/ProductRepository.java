package app.repository;
import app.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    Product findById(int id);
    void addProduct(Product product);
    void deleteById(int id);
}