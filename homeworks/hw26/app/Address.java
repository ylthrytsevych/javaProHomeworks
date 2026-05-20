package hw26.app;

public class Address {
    private String city;
    private String street;
    private int zipCode;

    public Address(String city, String street, int zipCode) {
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return String.valueOf(zipCode);
    }

    @Override
    public String toString() {
        return street + ", " + city+ ", "+zipCode;
    }

}
