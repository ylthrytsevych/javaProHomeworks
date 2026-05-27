package app.service;

public interface CartService {
    void addProductById(int id);
    void removeProductById(int id);
    void showCart();
}