package tp2;

import graph.AdjacencyMatrixGraphImpl;
import graph.Graph;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Tp2Impl<T> implements Tp2<T> {

    @Override
    public List<T> depth_first_search(Graph<T> graph) {
        List<T> result = new ArrayList<>();
        List<T> vertexes = graph.getVertexes();        
        T top;
        boolean[] visited = new boolean[graph.order()];
        Stack<T> stack = new Stack<>();
        List<T> list;
        stack.push(vertexes.get(0));
        visited[0] = true;
        while (!stack.isEmpty()) {
            top = stack.pop();
            result.add(top);
            list = graph.getAdjacencyList(top);
            for (T v: list) {
                if (!visited[vertexes.indexOf(v)]) {
                    visited[vertexes.indexOf(v)] = true;
                    stack.push(v);
                }
            }
        }
        return result;
    }
    
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
            for (T v: list) {
                if (!visited[vertexes.indexOf(v)]) {
                    visited[vertexes.indexOf(v)] = true;
                    queue.add(v);
                }
            }
        }
        return result;
    }

    @Override
    public boolean exercise_a(Graph<T> graph, T v, T w) {
        // Analiza si existe un Camino Simple
        if (!graph.getVertexes().contains(v) || !graph.getVertexes().contains(w))
            return false;
        if (v == w || graph.hasEdge(v, w))
            return true;
        T top;
        List<T> list;
        Stack<T> toVisit = new Stack<>();
        boolean[] visited = new boolean[graph.order()];
        toVisit.push(v);
        while (!toVisit.isEmpty()) {
            top = toVisit.pop();
            list = graph.getAdjacencyList(top);
            visited[graph.getVertexes().indexOf(top)] = true;
            for (T t: list) {
                if (t == w) return true;
                if (!visited[graph.getVertexes().indexOf(t)])
                    toVisit.push(t);
            }
        }
        return false;
    }

    @Override
    public boolean exercise_b(Graph<T> graph, T v) {
        if (!graph.getVertexes().contains(v))
            return false;
        T top;
        int l; // Longitud del camino
        List<T> list;
        Stack<T> toVisit = new Stack<>();
        boolean[] visited = new boolean[graph.order()];
        visited[graph.getVertexes().indexOf(v)] = true;
        for (T w: graph.getAdjacencyList(v)) {
            l = 0;
            toVisit.push(w);
            while (!toVisit.isEmpty()) {
                top = toVisit.pop();
                list = graph.getAdjacencyList(top);
                visited[graph.getVertexes().indexOf(top)] = true;
                for (T t: list) {
                    if (t == v && l > 0) return true;
                    if (!visited[graph.getVertexes().indexOf(t)]) {
                        toVisit.push(t);
                    }
                }
                l++;
            }
        }
        return false;
    }

    @Override
    public boolean exercise_c(Graph<T> graph) {
        for (T v: graph.getVertexes()) {
            if (exercise_b(graph, v))
                return true;
        }
        return false;
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