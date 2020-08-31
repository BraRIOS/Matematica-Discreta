package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class AdjacencyMatrixGraphImpl<T> implements Graph<T> {
    private T[] V;
    private boolean[][] A;
    private int n;
    private int alpha;

    public AdjacencyMatrixGraphImpl() {
        V = (T[]) new Object[10];
        A = new boolean[10][10];
        n = 0;
        alpha = 0;
    }
    public AdjacencyMatrixGraphImpl(int capacity) {
        V = (T[]) new Object[capacity];
        A = new boolean[capacity][capacity];
        n = 0;
        alpha = 0;
    }

    @Override
    public void addVertex(T x) {
        if (n == V.length){
            growVertexes();
            growMatrixA();
        }
        V[n] = x;
        n++;
    }

    private void growVertexes() {
        T[] aux = (T[]) new Object[V.length*2];
        System.arraycopy(V, 0, aux, 0, V.length);
        V = aux;
    }

    private void growMatrixA() {
        int size = A.length*2;
        boolean[][] matrix = new boolean[size][size];
        for (int i = 0; i < A.length; i++) {
            System.arraycopy(A[i], 0, matrix[i], 0, A.length);
        }
    }

    @Override
    public boolean hasVertex(T v){
        return V[(int)v]!=null;
    }

    @Override
    public void removeVertex(T x) {
        for (int v = 0; v < n ; v++) {
            if (V[v] == x) {
                V[v] = null;
                n--;
            } else throw new NoSuchElementException("El elemento no existe");
        }
    }

    @Override
    public void addEdge(T v, T w) {
        if (!A[(int) v][(int) w]){
            A[(int)v][(int)w]=A[(int)w][(int)v] = true;
            alpha++;
        }
    }

    @Override
    public void removeEdge(T v, T w) {
        if (A[(int)v][(int)w]){
            A[(int)v][(int)w]=A[(int)w][(int)v] = false;
            alpha--;
        }
    }

    @Override
    public boolean hasEdge(T v, T w) {
        return A[(int)v][(int)w];
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
        return new LinkedList<>(Arrays.asList(V));
    }

    @Override
    public List<T> getAdjacencyList(T v) {
        LinkedList<Integer> lst = new LinkedList<>();
        for (int w = 0; w < n ; w++)
            if (A[(int)v][w])
                lst.add(w);
        return (List<T>) lst;
    }
}
