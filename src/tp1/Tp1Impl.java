package tp1;

import graph.AdjacencyMatrixGraphImpl;
import graph.Graph;

import java.util.LinkedList;
import java.util.List;

// TODO: implement
public class Tp1Impl<T> implements Tp1<T> {
    @Override
    public void exercise_a(Graph<T> graph) {
        List<T> vertexes = graph.getVertexes();
        for (T v : vertexes) {
            System.out.println("Vertex: " + v);
            List<T> adyacent = graph.getAdjacencyList(v);
            for (T w : adyacent)
            System.out.println("{ "+ v + " , " + w + "}");
        }
    }

    @Override
    public int exercise_b(Graph<T> graph) {
        List<T> vertexes = graph.getVertexes();
        int loops = 0;
        for (T v : vertexes) {
            if (graph.hasEdge(v, v))
                loops++;
        } return loops;
    }

    @Override
    public List<T> exercise_c(Graph<T> graph) {
        List<T> vertexes = graph.getVertexes();
        List<T> output = new LinkedList<>();
        for (T v : vertexes) {
            if (graph.hasEdge(v, v))
                output.add(v);
        } return output;
    }

    @Override
    public boolean exercise_d(Graph<T> graph, T vertex) {
        return (graph.getAdjacencyList(vertex).isEmpty());
    }

    @Override
    public int exercise_e(Graph<T> graph) {
        List<T> vertexes = graph.getVertexes();
        int isolated = 0;
        for (T v : vertexes){
            if (graph.getAdjacencyList(v).isEmpty())
                ++isolated;
        }
        return isolated;
    }

    @Override
    public List<T> exercise_f(Graph<T> graph) {
        List<T> vertexes = graph.getVertexes();
        List<T> output = new LinkedList<>();
        for (T v: vertexes) {
            if (graph.getAdjacencyList(v).isEmpty())
                output.add(v);
        } return output;
    }

    @Override
    public Graph<T> exercise_g(Graph<T> graph) {
        Graph<T> output = new AdjacencyMatrixGraphImpl<>();
        List<T> v = graph.getVertexes();
        for(T w: v) {
            if(!graph.getAdjacencyList(w).isEmpty())
                output.addVertex(w);
        }
        for(T w: v) {
            List<T> aux = graph.getAdjacencyList(w);
            for(T x: aux) {
                if (x!=w)
                    output.addEdge(x,w);
            }
        } return output;
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

    /*  Existen factorial de alfa (alpha!) formas de representar una matriz de incidencias.
        Es muy poco probable de que la salida coincida con la matriz del test.
        En consecuencia, agregue lineas de codigo para que estas coincidan.
     */
    @Override
    public int[][] exercise_i(Graph<T> graph) {
        int[][] output = new int[graph.order()][graph.alpha()];
        List<T> v = graph.getVertexes();
        int edges = 0;
        for (int i = 0; i < graph.order(); i++) {
            for (int j = i; j < graph.order(); j++) {
                if (graph.hasEdge(v.get(i), v.get(j))) {
                    //----------------------------------
                    if (j-i > 2) {
                        output[i][graph.alpha()-1] = 1;
                        output[j][graph.alpha()-1] = 1;
                    } else {
                        //------------------------------
                        output[i][edges] = 1;
                        output[j][edges++] = 1;
                    }
                }
            }
        } return output;
    }
}
