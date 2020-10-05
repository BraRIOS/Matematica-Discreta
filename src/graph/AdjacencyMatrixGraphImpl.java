package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AdjacencyMatrixGraphImpl<T> implements Graph<T> {
    private final List<T> V; //Array de vertices
    private boolean[][] A; //Matriz de adyacencia
    private int n;
    private int alpha;

    public AdjacencyMatrixGraphImpl() {
        V = new ArrayList<>(10);
        A = new boolean[10][10];
        n = 0;
        alpha = 0;
    }

    public AdjacencyMatrixGraphImpl(int capacity) {
        V = new ArrayList<>(capacity);
        A = new boolean[capacity][capacity];
        n = 0;
        alpha = 0;
    }

    @Override
    public void addVertex(T x) {
        if (V.size() == A.length)
            growMatrixA();
        V.add(x);
        n++;
    }

    private void growMatrixA() {
        int size = A.length+10;
        boolean[][] matrix = new boolean[size][size];
        for (int i = 0; i < A.length; i++) {
            System.arraycopy(A[i], 0, matrix[i], 0, A.length);
        }
        A = matrix;
    }

    @Override
    public boolean hasVertex(T v){
        return V.contains(v);
    }

    @Override
    public void removeVertex(T x) {
        V.remove(x);
    }

    @Override
    public void addEdge(T v, T w) {
        if (!hasEdge(v,w) && V.contains(v) && V.contains(w)){
            A[V.indexOf(v)][V.indexOf(w)] = A[V.indexOf(w)][V.indexOf(v)] = true;
            alpha++;
        }
    }

    @Override
    public void removeEdge(T v, T w) {
        if (hasEdge(v,w)){
            A[V.indexOf(v)][V.indexOf(w)] = A[V.indexOf(w)][V.indexOf(v)] = false;
            alpha--;
        }
    }

    @Override
    public boolean hasEdge(T v, T w) {
        if (V.contains(v) && V.contains(w))
            return A[V.indexOf(v)][V.indexOf(w)];
        return false;
    }

    @Override
    public int order() {
        return n;
    }

    @Override
    public int alpha() {
        return alpha;
    }

    @Override
    public List<T> getVertexes() {
        return V;
    }

    @Override
    public List<T> getAdjacencyList(T v) {
        LinkedList<T> lst = new LinkedList<>();
        for (int w = 0; w < V.size() ; w++)
            if (A[V.indexOf(v)][w])
                lst.add(V.get(w));
        return lst;
    }
}
