package hw20.app;

import java.util.*;

public class Graph {
    private Map<Integer, Set<Integer>> graph;

    public Graph() {
        this.graph = new HashMap<>();
    }

    public void addVertex(int vertex) {
        graph.putIfAbsent(vertex, new HashSet<>());
    }

    public boolean hasVertex(int vertex) {
        return graph.containsKey(vertex);
    }

    public void addEdge(int source, int destination) {
        addVertex(source);
        addVertex(destination);

        graph.get(source).add(destination);
        graph.get(destination).add(source);
    }

    public boolean hasEdge(int source, int destination) {
        if (!hasVertex(source)) return false;
        return graph.get(source).contains(destination);
    }

    public void removeVertex(int vertex) {
        if (!hasVertex(vertex)) return;

        for(int next : graph.get(vertex)){
            graph.get(next).remove(vertex);
        }
        graph.remove(vertex);
    }

    public void removeEdge(int source, int destination) {
        if (hasVertex(source) && hasVertex(destination)) {
            graph.get(source).remove(destination);
            graph.get(destination).remove(source);
        }
    }


    public void printGraph() {
        System.out.println("Поточний стан графа:");
        for (Map.Entry<Integer, Set<Integer>> entry : graph.entrySet()) {
            System.out.println("Вершина " + entry.getKey() + " з'єднана з: " + entry.getValue());
        }
        System.out.println("-------------------------");
    }


}
