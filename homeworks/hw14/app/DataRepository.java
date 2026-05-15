package hw14.app;


import java.util.ArrayList;
import java.util.List;

public class DataRepository {
    // Метод повертає список імен

    //Alice
    //Bob
    //Lucy
    //Denis
    //Tom
    public List<String> getData() {
        List<String> list = new ArrayList<String>();
        list.add("Alice");
        list.add("Bob");
        list.add("Lucy");
        list.add("Denis");
        list.add("Tom");
        return list;
    }
}
