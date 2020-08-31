package graph;

import java.util.LinkedList;

public class Vertex<T> {
    private T data;
    private LinkedList<T> adjacencyList;

    public Vertex(T data) {
        this.data = data;
        this.adjacencyList = new LinkedList<>();
    }

    public boolean existAdjacent(T v){
        return adjacencyList.contains(v);
    }

    public void addAdjacent(T v){
        if (!existAdjacent(v)) adjacencyList.add(v);
    }

    public void removeAdjacent(T v){
        adjacencyList.remove(v);
    }

    public T getData() {
        return data;
    }

    public LinkedList<T> getAdjacencyList() {
        return adjacencyList;
    }
}
