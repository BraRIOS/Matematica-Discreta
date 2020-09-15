package tp2;

import graph.AdjacencyMatrixGraphImpl;
import graph.Graph;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Tp2Impl<T> implements Tp2<T> {
    @Override
    public List<T> depth_first_search(Graph<T> graph) {
        throw new UnsupportedOperationException("TODO");
    }

    /*public void bfs(Grafo g, int v) {
 int fr;
 boolean[] visitado = new visitado[g.orden()];
 Cola c = new ColaD();
 intList lst;
 c.agregar(c);
 visitado[v] = true;
 while (!c.esVacia()){
 fr = c.verFrente();
 c.sacar();
 procesar(fr);
 lst = g.getListaAdy(fr);
 if(lst.longitud() != 0 ){
 lst.irPrimero();
 for (int i = 0; i < lst.longitud(); i++){
 if(!visitado[lst.verActual()]{
 visitado[lst.verActual()]= true;
c.agregar(lst.verActual());
 }
 lst.irSiguiente();
 }
 }
 }
 }*/
    @Override
    public List<T> breadth_first_search(Graph<T> graph) {
        List<T> result = new ArrayList<>();
        List<T> vertexes = graph.getVertexes();
        T front;
        boolean[] visited = new boolean[graph.order()];
        Queue<T> queue = new LinkedBlockingQueue<>();
        List<T> list;
        queue.add(vertexes.get(0));
        visited[0] = true;
        while (!queue.isEmpty()) {
            front = queue.poll();
            result.add(front);
            list = graph.getAdjacencyList(front);
            if (list.size() != 0) {
                for (T v: list) {
                    if (!visited[vertexes.indexOf(v)]) {
                        visited[vertexes.indexOf(v)] = true;
                        queue.add(v);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public boolean exercise_a(Graph<T> graph, T v, T w) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean exercise_b(Graph<T> graph, T v) {
        return false;
    }

    @Override
    public boolean exercise_c(Graph<T> graph) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean exercise_d(Graph<T> graph) {
        return false;
    }

    @Override
    public int exercise_e(Graph<T> graph, T v, T w) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public List<T> exercise_f(Graph<T> graph,T v, T w) {
        return new ArrayList<>();
    }

    @Override
    public List<T> exercise_g(Graph<T> graph, T v, T w) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public int exercise_h(Graph<T> graph) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean exercise_i(Graph<T> g1, Graph<T> g2) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean exercise_j(Graph<T> g1, Graph<T> g2) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean exercise_k(Graph<T> g1) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public Graph<T> exercise_l(Graph<T> graph) {
        return new AdjacencyMatrixGraphImpl<>();
    }

    @Override
    public int exercise_m(Graph<T> graph, T v) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public Map<T, Integer> exercise_n(Graph<T> graph) {
        return new HashMap<>();
    }

}