package app.mapper;

import app.dto.OrderDto;
import app.dto.ProductDto;
import org.springframework.stereotype.Component;
import app.entity.Order;
import app.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {

    public OrderDto toDto(Order order){
        //спочатку перетворити список продуктів
        List<Product> prods = order.getProducts();
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product prod : prods){
            ProductDto temp = new ProductDto(prod.getId(), prod.getName(), prod.getPrice());
            productDtos.add(temp);
        }

        return new OrderDto(
                order.getId(),
                order.getTotalCost(),
                order.getCreatedAt(),
                productDtos
        );
    }
}
