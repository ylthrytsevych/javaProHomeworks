package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import app.model.Order;
import app.model.Product;
import app.service.OrderService;

import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String getAllOrders(Model model) {
        model.addAttribute("orders", orderService.getAll());
        return "orders"; // це назва в'юшки - укатиме файл orders.html
    }

    @GetMapping("/{id}")
//    public String getOrderById(@PathVariable Long id, Model model) { // цей варіант не працює в поточній версії - треба додати
    public String getOrderById(@PathVariable("id") Long id, Model model) {
        Order order = orderService.getById(id);
        if (order == null) {
            return "redirect:/orders"; // Якщо не знайшли - повертаємось на список
        }
        model.addAttribute("order", order); //тут об'єкт order передаємо в модель за тою ж назвою
        return "order_detail"; // Шукатиме файл order_detail.html
    }

    @PostMapping
//    public String addOrder(@RequestParam("productName") String productName, @RequestParam("productCost") double productCost) {
    public String addOrder(
            @RequestParam("productNames") List<String> productNames,
            @RequestParam("productCosts") List<Double> productCosts) {
        Long orderId = new Random().nextLong(100, 999);
        Order newOrder = new Order(orderId);

        int productsCount = productNames.size();
//        Long productId = new Random().nextLong(1000, 9999);
//        newOrder.addProduct(new Product(productId, productName, productCost));

        for (int i = 0; i < productsCount; i++) {
            String name = productNames.get(i);
            Double cost = productCosts.get(i);

            Long productId = new Random().nextLong(1000, 9999);
            newOrder.addProduct(new Product(productId, name, cost));
        }
        orderService.addOrder(newOrder);

        return "redirect:/orders"; // Після додавання оновлюємо сторінку
    }
}