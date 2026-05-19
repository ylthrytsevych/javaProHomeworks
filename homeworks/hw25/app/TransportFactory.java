package hw25.app;

public abstract class TransportFactory {
    abstract Transport createTransport();
}

class CarFactory extends TransportFactory {
    @Override
    Transport createTransport() {
        return new Car();
    }
}

class PlaneFactory extends TransportFactory {
    @Override
    Transport createTransport() {
        return new Plane();
    }
}