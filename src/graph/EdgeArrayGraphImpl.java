package graph;

import java.util.LinkedList;
import java.util.List;

// TODO: implement
public class EdgeArrayGraphImpl<T> implements Graph<T> {

    int n;
    int alpha;
    List<int[]> lst;
    T[] V;

    public EdgeArrayGraphImpl() {
        n = 0;
        alpha = 0;
        lst = new LinkedList<>();
        V = (T[]) new Object[10];
    }

    public EdgeArrayGraphImpl(int capacity) {
        n = 0;
        alpha = 0;
        lst = new LinkedList<>();
        V = (T[]) new Object[capacity];
    }

    @Override
    public void addVertex(T x) {
        if ((int)x >= V.length)
            growVertexes((int) x);
        V[(int)x] = x;
        n++;
    }

    private void growVertexes(int x) {
        T[] aux = (T[]) new Object[V.length+x];
        System.arraycopy(V, 0, aux, 0, V.length);
        V = aux;
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
        if (!hasEdge(v, w) && hasVertex(v) && hasVertex(w))
            lst.add(new int []{(int) v, (int) w});
        alpha++;
    }

    @Override
    public void removeEdge(T v, T w) {
        int[] edge = new int[2];
        for (int i = 0; i < alpha; i++) {
            edge = lst.get(i);
            if ((edge[0] == (int) v &&  edge[1] == (int) w) || (edge[1] == (int) v &&  edge[0] == (int) w))
                lst.remove(i);
        }
        alpha--;
    }

    @Override
    public boolean hasEdge(T v, T w) {
        for (int[] edge: lst) {
            if ((edge[0] == (int) v &&  edge[1] == (int) w) || (edge[1] == (int) v &&  edge[0] == (int) w))
                return true;
        } return false;
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
        List<T> out = new LinkedList<>();
        if (n!= 0) {
            for (T v: V) {
                if(v!=null)
                    out.add(v);
            }
        } return out;
    }

    @Override
    public List<T> getAdjacencyList(T v) {
        List<T> out = new LinkedList<>();
        for(int[] edge: lst) {
            if (edge[0] == (int) v)
                out.add(V[edge[1]]);
            if (edge[1] == (int) v)
                out.add(V[edge[0]]);
        } return out;
    }
}
