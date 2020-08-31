package tp1;

import graph.Graph;

import java.util.List;

// TODO: implement
public class Tp1Impl<T> implements Tp1<T> {
    @Override
    public void exercise_a(Graph<T> graph) {
        System.out.println("STEINS;GATE");
    }

    @Override
    public int exercise_b(Graph<T> graph) {
        List<T> vertex = graph.getVertexes();
        int loops = 0;
        for (T v : vertex) {
            if (graph.hasEdge(v, v))
                loops++;
        } return loops;
    }

    @Override
    public List<T> exercise_c(Graph<T> graph) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean exercise_d(Graph<T> graph, T vertex) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public int exercise_e(Graph<T> graph) {
        List<T> vertex = graph.getVertexes();
        int isolated = 0;
        for (T v : vertex){
            if (graph.getAdjacencyList(v).isEmpty())
                ++isolated;
        }
        return isolated;
    }

    @Override
    public List<T> exercise_f(Graph<T> graph) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public Graph<T> exercise_g(Graph<T> graph) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public int[][] exercise_h(Graph<T> graph) {
        List<T> v = graph.getVertexes();
        int n = graph.order();
        int[][] A = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if (graph.hasEdge(v.get(i), v.get(j)))
                    A[i][j] = 1;
            }
        } return A;
    }

    @Override
    public int[][] exercise_i(Graph<T> graph) {
        throw new UnsupportedOperationException("TODO");
    }
}
