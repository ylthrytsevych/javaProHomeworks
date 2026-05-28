package app.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private LocalDate creationDate;
    private double totalCost;
    private List<Product> products;

    public Order(Long id) {
        this.id = id;
        this.creationDate = LocalDate.now();
        this.products = new ArrayList<>();
        this.totalCost = 0.0;
    }

    public void addProduct(Product product) {
        this.products.add(product);
        this.totalCost += product.getCost(); // Автоматично оновлюємо ціну
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; }
    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }
    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }
}