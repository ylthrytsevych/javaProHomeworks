package app.service;

import app.entity.Order;
import app.entity.Product;
import app.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class OrderServiceImp implements OrderService{

    private final OrderRepository oRep;

    @Autowired
    public OrderServiceImp(final OrderRepository orderRepository) {
        this.oRep = orderRepository;
    }

    @Transactional(readOnly = true) // теоретично якщо поставити рід - то читання з бази швидше в транзакції
    @Override
    public Order getById(Long id) {
        return oRep.findByIdWithProducts(id)
                .orElseThrow(() -> new NoSuchElementException("Замовлення з ID=" + id + " не знайдено"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Order> getAll() {
        return oRep.findAll();
    }

    @Override
    public Order create(Order order) {
//        return oRep.save(order); //ккраще передодати і перерахувати суму продуктів через адд
        Order newOrder = new Order();
        for (Product product : order.getProducts()) {
            newOrder.addProduct(product);
        }
        return oRep.save(newOrder);
    }

    @Override
    public Order update(Long id, Order orderDetails) {
        Order existingOrder = getById(id);

        existingOrder.setCreatedAt(orderDetails.getCreatedAt());
//        existingOrder.setProducts(orderDetails.getProducts()); //це не працює, до того ж тоді просто додасть продукти, а ціну треба сетити окремо
        //продукти - ліст, отже спочатку чистим і лодаєм всі
        existingOrder.getProducts().clear();
        existingOrder.setTotalCost(BigDecimal.ZERO); //обнуляєм вартість перед підрахунком
        for (Product product : orderDetails.getProducts()) {
            existingOrder.addProduct(product);
        }

        return oRep.save(existingOrder);
    }

    @Override
    public void delete(Long id) {
        Order existingOrder = getById(id);
        oRep.delete(existingOrder);
    }
}
