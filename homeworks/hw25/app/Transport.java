package hw25.app;

public interface Transport {
    void move();
}

class Car implements Transport {
    @Override
    public void move() {
        System.out.println("Автомобіль їде по дорозі");
    }
}

class Plane implements Transport {
    @Override
    public void move() {
        System.out.println("Літак летить в хмарах");
    }
}