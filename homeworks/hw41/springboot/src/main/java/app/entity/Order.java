package app.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
    //id, totalCost, products, createdAt

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "order_id_seq", allocationSize = 1)
    private Long id;

    @JoinColumn(name = "order_id") // не продуктід, а ордер_ід --- Hibernate в бд створить колонку order_id у таблиці products(до якого замовлення належить продукт)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true) //без ремув - не видалялись старі товари при апдейті
    private List<Product> products;
    //якщо не написати @JoinColumn, Hibernate б створив  додаткову таблицю (наприклад, order_products),
    // щоб зберігати там зв'язки. Це часто буває зайвим, тому ми явно вказуємо @JoinColumn, щоб він
    // просто додав одну колонку в таблицю products.

    @Column(name = "total_cost", nullable = false)
    private BigDecimal totalCost;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Order() {
        this.createdAt = LocalDateTime.now();
        this.totalCost = BigDecimal.ZERO;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
        // зразу додаєм ціну товару до загальної вартості
        this.totalCost = this.totalCost.add(product.getPrice());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, products, totalCost, createdAt);
    }
}
