package app;

import java.util.List;

public class Order {
    private int id;
    private String date;
    private double cost;
    private List<Product> products;

    public Order() {
    }

    public Order(int id, String date, double cost, List<Product> products) {
        this.id = id;
        this.date = date;
        this.cost = cost;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    /*
    приклад

        {
          "id": 1,
          "date": "2023-10-25",
          "cost": 1500.50,
          "products": [
            {
              "id": 101,
              "name": "Laptop",
              "cost": 1000.00
            },
            {
              "id": 102,
              "name": "Mouse",
              "cost": 500.50
            }
          ]
        }
     */
}