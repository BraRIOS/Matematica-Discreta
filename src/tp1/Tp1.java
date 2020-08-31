package tp1;

import graph.Graph;

import java.util.List;

public interface Tp1<T> {
//    a) Mostrar el grafo.
//    b) Retornar la cantidad de lazos de un grafo.
//    c) Retornar un arreglo con los vértices que tienen lazos.
//    d) Dado un vértice informar si es aislado.
//    e) Calcular cuantos vértices son aislados.
//    f) Retornar todos los vértices aislados.
//    g) Dado un grafo debe retornar otro grafo sin lazos y sin vértices aislados.
//    h) Calcular y mostrar la matriz de adyacencia.
//    i) Calcular y mostrar la matriz de incidencia.

    void exercise_a(Graph<T> graph);

    int exercise_b(Graph<T> graph);

    List<T> exercise_c(Graph<T> graph);

    boolean exercise_d(Graph<T> graph, T vertex);

    int exercise_e(Graph<T> graph);

    List<T> exercise_f(Graph<T> graph);

    Graph<T> exercise_g(Graph<T> graph);

    int[][] exercise_h(Graph<T> graph);

    int[][] exercise_i(Graph<T> graph);
}
