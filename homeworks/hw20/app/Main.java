package hw20.app;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        System.out.println("1. ДОДАВАННЯ ВЕРШИН ТА РЕБЕР");
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(5, 1);

        graph.printGraph();
//        graph.removeVertex(3);
//        graph.printGraph();

        System.out.println("2. ПЕРЕВІРКА МЕТОДІВ hasVertex та hasEdge");
        System.out.println("Чи є вершина 3? : " + graph.hasVertex(3));
        System.out.println("Чи є вершина 99? : " + graph.hasVertex(99));
        System.out.println("Чи є ребро між 3 і 2? : " + graph.hasEdge(3, 2));
        System.out.println("Чи є ребро між 1 і 4? : " + graph.hasEdge(1, 4));
        System.out.println("-------------------------");

        System.out.println("3. ВИДАЛЕННЯ РЕБРА (між 3 і 2)");
        graph.removeEdge(3, 2);
        System.out.println("Чи є ребро між 3 і 2 тепер? : " + graph.hasEdge(3, 2));
        graph.printGraph();

        System.out.println("4. ВИДАЛЕННЯ ВЕРШИНИ (видаляємо 3)");
        graph.removeVertex(3);
        graph.printGraph();
    }
}
