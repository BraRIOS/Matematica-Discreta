package tp2;

import graph.Graph;

import java.util.List;
import java.util.Map;

interface Tp2<T> {
    //    Part 0
//    i) Depth first search
//   ii) Breadth first search
    List<T> depth_first_search(Graph<T> graph);
    List<T> breadth_first_search(Graph<T> graph);

//    Part 1
//    a) Dado un grafo sin lazos y dos vértices v y w, verificar si hay un camino.
//    b) Dado un grafo sin lazos y un vértice v, verificar si hay un ciclo de v a v.
//    c) Verificar si un grafo tiene ciclos.
//    d) Verificar si un grafo es conexo.
//    e) Calcular la longitud de algún camino que une dos vértices dados. Si no hay camino entre los vértices debe retornar –1.
//    f) Calcular un camino que une dos vértices dados.
//    g) Calcular el camino mínimo que une dos vértices dados.
//    h) Calcular la cantidad de componentes conexas.

    boolean exercise_a(Graph<T> graph, T v, T w);

    boolean exercise_b(Graph<T> graph, T v);

    boolean exercise_c(Graph<T> graph);

    boolean exercise_d(Graph<T> graph);

    int exercise_e(Graph<T> graph, T v, T w);

    List<T> exercise_f(Graph<T> graph, T v, T w);

    List<T> exercise_g(Graph<T> graph, T v, T w);

    int exercise_h(Graph<T> graph);

//    Part 2:
//    i) Dado dos grafos g1 y g2, verifique si g1 es subgrafo de g2.
//    j) Dado dos grafos g1 y g2, verifique si g1 es subgrafo recubridor de g2.
//    k) Verificar si un grafo es completo.
//    l) Hallar el grafo complementario.
//    m) Calcular el grado de un vértice dado
//    n) Retornar un arreglo con el grado de cada vértice

    boolean exercise_i(Graph<T> g1, Graph<T> g2);

    boolean exercise_j(Graph<T> g1, Graph<T> g2);

    boolean exercise_k(Graph<T> g1);

    Graph<T> exercise_l(Graph<T> graph);

    int exercise_m(Graph<T> graph, T v);

    Map<T, Integer> exercise_n(Graph<T> graph);
}