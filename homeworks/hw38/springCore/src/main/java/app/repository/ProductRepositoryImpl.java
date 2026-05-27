package app.repository;
import app.Product;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

// @Repository - типу @Component, але для класів які працюють з даними
//реп - Класи, що безпосередньо спілкуються з базою даних або списками
//
@Repository
public class ProductRepositoryImpl implements ProductRepository{

    private final List<Product> products = new ArrayList<>();

    // конструктор спрацює автоматично при старті Spring і наповнить список
    public ProductRepositoryImpl() {
        products.add(new Product(1, "Хліб", 25.50));
        products.add(new Product(2, "Молоко", 38.00));
        products.add(new Product(3, "Яблука", 45.20));
        products.add(new Product(4, "Кава", 150.00));
        products.add(new Product(5, "Шоколад", 60.00));
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null; // Якщо товар не знайдено
    }

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public void deleteById(int id) {
        products.removeIf(product -> product.getId() == id);
    }
}
