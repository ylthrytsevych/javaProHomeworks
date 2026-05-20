package hw26.app;

public class User {
    private String name;
    private Address address;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return this.address;
    }
}
