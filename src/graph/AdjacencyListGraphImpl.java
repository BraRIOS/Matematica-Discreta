package graph;

import java.util.LinkedList;
import java.util.List;


public class AdjacencyListGraphImpl<T> implements Graph<T> {
    public Vertex<T>[] V; //Array de vertices y sus listas de adyacentes
    private int n;
    private int alpha;

    public AdjacencyListGraphImpl() {
        V = new Vertex[10];
        n = 0;
        alpha = 0;
    }
    public AdjacencyListGraphImpl(int capacity) {
        V = new Vertex[capacity];
        n = 0;
        alpha = 0;
    }

    @Override
    public void addVertex(T x) {
        if ((int)x >= V.length){
            growVertexes((int) x);
        }
        V[(int)x] = new Vertex<>(x);
        n++;
    }

    private void growVertexes(int x) {
        Vertex<T>[] aux =  new Vertex[V.length+x];
        System.arraycopy(V, 0, aux, 0, V.length);
        V = aux;
    }

    @Override
    public boolean hasVertex(T v){
        if (!((int)v >= V.length))
            return V[(int)v] !=null;
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
        if(!((int)v >= V.length || (int)w >=V.length)){
            V[(int)v].addAdjacent(w);
            V[(int)w].addAdjacent(v);
            alpha++;
        }
    }

    @Override
    public void removeEdge(T v, T w) {
        if (hasEdge(v,w)){
            V[(int)v].removeAdjacent(w);
            V[(int)w].removeAdjacent(v);
            alpha--;
        }
    }

    @Override
    public boolean hasEdge(T v, T w) {
        if (!((int)v>=V.length))
            return V[(int)v].existAdjacent(w);
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
            for (Vertex<T> tVertex : V) {
                if (tVertex != null)
                    list.add(tVertex.getData());
            }
        return list;
    }

    @Override
    public List<T> getAdjacencyList(T v) {
        if (hasVertex(v)) return V[(int)v].getAdjacencyList();
        return new LinkedList<>();
    }

}
