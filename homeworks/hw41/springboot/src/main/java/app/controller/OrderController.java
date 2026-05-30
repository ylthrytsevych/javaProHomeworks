package app.controller;

import app.dto.OrderDto;
import app.entity.Order;
import app.mapper.OrderMapper;
import app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService serv;
    private final OrderMapper mapper;

    @Autowired
    public OrderController(final OrderService service, final OrderMapper orderMapper) {
        this.serv = service;
        this.mapper = orderMapper;
    }

    @GetMapping
    public List<OrderDto> getAll() {

        return serv.getAll().stream().map(x -> mapper.toDto(x)).toList();
        // return ResponseEntity - можна ставити власні коди при успішному створенні замовлення
        // за стандартами REST прийнято повертати статус 201 Created замість 200 OK
    }

    @PostMapping
    public OrderDto createOrder(@RequestBody Order order) {
        Order createdOrder = serv.create(order);
        return mapper.toDto(createdOrder);
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable("id") Long id) {
        return mapper.toDto(serv.getById(id));
    }

    @PutMapping("/{id}")
    public OrderDto updateOrder(@PathVariable("id") Long id, @RequestBody Order orderDetails) {
        Order updatedOrder = serv.update(id, orderDetails);
        return mapper.toDto(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable("id") Long id) {
        serv.delete(id);
        return "Замовлення ID=" + id + " успішно видалено";
    }

}
