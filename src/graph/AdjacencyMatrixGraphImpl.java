package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AdjacencyMatrixGraphImpl<T> implements Graph<T> {
    public T[] V; //Array de vertices
    private boolean[][] A; //Matriz de adyacencia
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
        if ((int)x >= V.length){
            growVertexes((int) x);
            growMatrixA((int) x);
        }
        V[(int)x] = x;
        n++;
    }

    private void growVertexes(int x) {
        T[] aux = (T[]) new Object[V.length+x];
        System.arraycopy(V, 0, aux, 0, V.length);
        V = aux;
    }

    private void growMatrixA(int x) {
        int size = A.length+x;
        boolean[][] matrix = new boolean[size][size];
        for (int i = 0; i < A.length; i++) {
            System.arraycopy(A[i], 0, matrix[i], 0, A.length);
        }
        A = matrix;
    }

    @Override
    public boolean hasVertex(T v){
        if (!((int)v >= V.length))
            return V[(int)v]!=null;
        return false;
    }

    @Override
    public void removeVertex(T x) {
        if (hasVertex(x)) {
            V[(int)x] = null;
        }
    }

    @Override
    public void addEdge(T v, T w) {
        if (!hasEdge(v,w) && !((int)v >= A.length || (int)w >=A.length)){
            A[(int)v][(int)w]=A[(int)w][(int)v] = true;
            alpha++;
        }
    }

    @Override
    public void removeEdge(T v, T w) {
        if (hasEdge(v,w)){
            A[(int)v][(int)w]=A[(int)w][(int)v] = false;
            alpha--;
        }
    }

    @Override
    public boolean hasEdge(T v, T w) {
        if (!((int)v>=A.length || (int)w>=A.length))
            return A[(int)v][(int)w];
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
        List<T> list = new LinkedList<>();
        if (n!=0)
            for (T v: V) {
                if (v!=null)
                    list.add(v);
            }
        return list;
    }

    @Override
    public List<T> getAdjacencyList(T v) {
        LinkedList<Integer> lst = new LinkedList<>();
        for (int w = 0; w < V.length ; w++)
            if (A[(int)v][w])
                lst.add(w);
        return (List<T>) lst;
    }
}
