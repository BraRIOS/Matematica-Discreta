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
        return existPath(graph, v, w);
    }

    public boolean existPath(Graph<T> graph, T v, T w) {
        boolean[] visited = new boolean[graph.order()];
        return existPath(graph, v, w, visited);
    }

    public boolean existPath(Graph<T> graph, T v, T w, boolean[] visited) {
        if (!graph.getVertexes().contains(v) || !graph.getVertexes().contains(w))
            return false;
        if ((v == w) || graph.hasEdge(v, w))
            return true;
        List<T> list;
        visited[graph.getVertexes().indexOf(v)] = true;
        list = graph.getAdjacencyList(v);
        if (list.isEmpty()) return false;
        for (T ady: list) {
            if (!visited[graph.getVertexes().indexOf(ady)] && existPath(graph, ady, w, visited))
                return true;
        }
        return false;
    }

    @Override
    public boolean exercise_b(Graph<T> graph, T v) {
        return existCycleFrom(graph, v);
    }

    public boolean existCycleFrom(Graph<T> graph, T v) {
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
            if (existCycleFrom(graph, v))
                return true;
        }
        return false;
    }

    @Override
    public boolean exercise_d(Graph<T> graph) {
        if (graph.order() == 0 || graph.alpha() == 0)
            return false;
        T first = graph.getVertexes().get(0);
        for (int i = 1; i < graph.order(); i++) {
            if (!existPath(graph, first, graph.getVertexes().get(i)))
                return false;
        }
        return true;
    }

    @Override
    public int exercise_e(Graph<T> graph, T v, T w) {
        return findPath(graph, v, w).size() - 1;
    }

    @Override
    public List<T> exercise_f(Graph<T> graph,T v, T w) {
        return findPath(graph, v, w);
    }

    public List<T> findPath(Graph<T> graph, T v, T w) {
        List<T> pathFounded = new ArrayList<>();
        boolean[] visited = new boolean[graph.order()];
        pathFounded = findPath(graph, v, w, visited, pathFounded);
        return pathFounded;
    }

    public List<T> findPath(Graph<T> graph, T v, T w, boolean[] visited, List<T> pathFounded) {
        if (!existPath(graph, v, w))
            return pathFounded;
        if (v == w) {
            pathFounded.add(v);
            return pathFounded;
        }
        List<T> list;
        visited[graph.getVertexes().indexOf(v)] = true;
        list = graph.getAdjacencyList(v);
        pathFounded.add(v);

        for (T ady: list) {
            if (!visited[graph.getVertexes().indexOf(ady)] ) {
                visited[graph.getVertexes().indexOf(ady)] = true;
                List<T> path = findPath(graph, ady, w, visited, pathFounded);
                return pathFounded;
            }
        }
        return pathFounded;
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