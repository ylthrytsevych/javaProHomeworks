package app.repository;

import app.entity.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface OrderRepository extends JpaRepository<Order, Long> {

    //спец запис із fetch - підтягуємо і замовлення, і всі його продукти за 1 запит - уникнення n+1
    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.products WHERE o.id = :id")
    Optional<Order> findByIdWithProducts(@Param("id") Long id);

    // знову ж таки для уникнення n+1 - при запуску без цього вилатала 500 помилка
    //ailed to lazily initialize a collection of role: app.entity.Order.products: could not initialize proxy - no Session
    @Override
    @EntityGraph(attributePaths = {"products"})
    List<Order> findAll();
}
