package tp2;

import graph.Graph;
import graph.factory.GraphFactory;
import graph.factory.GraphFactoryImpl;
import org.junit.Before;
import org.junit.Test;
import utils.SkipRule;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class Tp2Test extends SkipRule {

    Graph<String> graph;
    Tp2<String> tp2;
    GraphFactory<String> graphFactory;

    public Tp2Test() {
        this.tp2 = new Tp2Impl<>();
        this.graphFactory = new GraphFactoryImpl<>();
    }

    @Before
    public void before() {
        graph = graphFactory.getGraph();
    }

    //    Part 0
    //    i) Depth first search
    @Test
    public void depth_first_search_test() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");
        graph.addVertex("I");

        graph.addEdge("A","B");
        graph.addEdge("A","I");
        graph.addEdge("G","I");
        graph.addEdge("C","I");
        graph.addEdge("C","D");
        graph.addEdge("C","E");
        graph.addEdge("C","F");
        graph.addEdge("G","F");
        graph.addEdge("G","H");
        graph.addEdge("E","H");
        testSearches(graph, tp2.depth_first_search(graph));
    }

    //   ii) Breadth first search
    @Test
    public void breadth_first_search_test() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");
        graph.addVertex("I");

        graph.addEdge("A","B");
        graph.addEdge("A","I");
        graph.addEdge("G","I");
        graph.addEdge("C","I");
        graph.addEdge("C","D");
        graph.addEdge("C","E");
        graph.addEdge("C","F");
        graph.addEdge("G","F");
        graph.addEdge("G","H");
        graph.addEdge("E","H");
        testSearches(graph, tp2.breadth_first_search(graph));
    }

    public void testSearches(Graph<String> graph, List<String> output) {
        assertEquals(graph.order(), output.size());

        assertEquals(output.stream().distinct().count(), output.size());

        for (String vertex : output) {
            assertTrue(graph.hasVertex(vertex));
        }
    }

    //    Part 1
    //    a) Dado un grafo sin lazos y dos vértices v y w, verificar si hay un camino.
    @Test
    public void exercise_a_a_graph_with_no_edges_has_no_paths() {
        assertFalse(tp2.exercise_a(graph, "A", "B"));

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        assertFalse(tp2.exercise_a(graph, "A", "C"));
        assertFalse(tp2.exercise_a(graph, "A", "B"));
        assertFalse(tp2.exercise_a(graph, "A", "D"));
        assertFalse(tp2.exercise_a(graph, "C", "D"));
        assertFalse(tp2.exercise_a(graph, "D", "A"));
    }

    @Test
    public void exercise_a_path_test() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");

        graph.addEdge("A", "C");
        graph.addEdge("C", "B");
        graph.addEdge("B", "D");

        assertTrue(tp2.exercise_a(graph, "A", "C"));
        assertTrue(tp2.exercise_a(graph, "A", "B"));
        assertTrue(tp2.exercise_a(graph, "A", "D"));
        assertTrue(tp2.exercise_a(graph, "C", "D"));
        assertTrue(tp2.exercise_a(graph, "D", "A"));

        assertFalse(tp2.exercise_a(graph, "E", "A"));
        assertFalse(tp2.exercise_a(graph, "B", "E"));
        assertFalse(tp2.exercise_a(graph, "C", "E"));
    }

    //    b) Dado un grafo sin lazos y un vértice v, verificar si hay un ciclo de v a v.
    @Test
    public void exercise_b_a_graph_with_no_edges_has_no_cycles() {
        assertFalse(tp2.exercise_b(graph, "Z"));

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        assertFalse(tp2.exercise_b(graph, "A"));
        assertFalse(tp2.exercise_b(graph, "B"));
        assertFalse(tp2.exercise_b(graph, "C"));
        assertFalse(tp2.exercise_b(graph, "D"));
    }

    @Test
    public void exercise_b_cycle_test() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("C", "B");
        graph.addEdge("C", "D");

        assertFalse(tp2.exercise_b(graph, "D"));

        assertTrue(tp2.exercise_b(graph, "A"));
        assertTrue(tp2.exercise_b(graph, "B"));
        assertTrue(tp2.exercise_b(graph, "C"));
    }

    //    c) Verificar si un grafo tiene ciclos.
    @Test
    public void exercise_c_a_graph_with_no_edges_has_no_cycles() {
        assertFalse(tp2.exercise_c(graph));

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        assertFalse(tp2.exercise_c(graph));
    }

    @Test
    public void exercise_c_graph_with_no_cycles_test() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");

        graph.addEdge("A", "B");
        graph.addEdge("C", "B");
        graph.addEdge("C", "D");
        graph.addEdge("E", "D");

        assertFalse(tp2.exercise_c(graph));
    }

    @Test
    public void exercise_c_graph_with_cycles_test() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");

        graph.addEdge("A", "B");
        graph.addEdge("C", "B");
        graph.addEdge("C", "D");
        graph.addEdge("E", "D");
        graph.addEdge("E", "C");

        assertTrue(tp2.exercise_c(graph));
    }

    //    d) Verificar si un grafo es conexo.
    @Test
    public void exercise_d_a_graph_with_no_edges_is_not_related() {
        assertFalse(tp2.exercise_d(graph));

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        assertFalse(tp2.exercise_d(graph));
    }

    @Test
    public void exercise_d_non_related_graph_test() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge("A", "B");
        graph.addEdge("C", "D");

        assertFalse(tp2.exercise_d(graph));
    }

    @Test
    public void exercise_d_related_graph_test() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge("A", "B");
        graph.addEdge("C", "A");
        graph.addEdge("C", "D");

        assertTrue(tp2.exercise_d(graph));
    }

    //    e) Calcular la longitud de algún camino que une dos vértices dados. Si no hay camino entre los vértices debe retornar –1.
    @Test
    public void empty_graph_has_no_paths() {
        assertEquals(-1, tp2.exercise_e(graph, "A", "B"));

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        assertEquals(-1, tp2.exercise_e(graph, "A", "B"));
        assertEquals(-1, tp2.exercise_e(graph, "A", "C"));
        assertEquals(-1, tp2.exercise_e(graph, "C", "B"));
    }

    @Test
    public void exercise_e_test() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");

        assertEquals(3, tp2.exercise_e(graph, "A", "D"));
        assertEquals(2, tp2.exercise_e(graph, "A", "C"));
    }

    //    f) Calcular un camino que une dos vértices dados.
    @Test
    public void exercise_f_test() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");

        graph.addEdge("A", "B");
        graph.addEdge("A", "D");
        graph.addEdge("B", "D");
        graph.addEdge("E", "D");
        graph.addEdge("B", "C");
        graph.addEdge("E", "C");
        graph.addEdge("C", "F");

        testPath(graph, tp2.exercise_f(graph, "A", "B"));
        testPath(graph, tp2.exercise_f(graph, "F", "B"));
        testPath(graph, tp2.exercise_f(graph, "A", "D"));
    }

    private void testPath(Graph<String> graph, List<String> path) {
        for (int i = 0; i < path.size()-1; i++) {
            assertTrue(graph.hasEdge(path.get(i), path.get(i + 1)));
        }
    }

    @Test
    public void testPath_method_test() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");

        testPath(graph,Arrays.asList("A","B","C","D"));
        testPath(graph,Arrays.asList("A","B","C"));
    }

    //    g) Calcular el camino mínimo que une dos vértices dados.
    @Test
    public void exercise_g_an_empty_graph_has_no_paths() {
        assertTrue(tp2.exercise_g(graph, "A", "B").isEmpty());

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        assertTrue(tp2.exercise_g(graph, "A", "B").isEmpty());
        assertTrue(tp2.exercise_g(graph, "A", "C").isEmpty());
        assertTrue(tp2.exercise_g(graph, "C", "B").isEmpty());
    }

    @Test
    public void exercise_g_test() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");

        graph.addEdge("A", "B");
        graph.addEdge("C", "B");
        graph.addEdge("A", "D");
        graph.addEdge("D", "H");
        graph.addEdge("D", "E");
        graph.addEdge("H", "E");
        graph.addEdge("F", "E");
        graph.addEdge("F", "G");
        graph.addEdge("E", "G");
        graph.addEdge("H", "G");

        List<String> expected1 = Arrays.asList("A", "D", "H");
        assertThat(tp2.exercise_g(graph, "A", "H"), is(expected1));

        List<String> expected2 = Arrays.asList("C", "B", "A", "D", "E", "G");
        assertThat(tp2.exercise_g(graph, "C", "G"), is(expected2));
    }

    //    h) Calcular la cantidad de componentes conexas.
    @Test
    public void exercise_h_a_graph_with_no_edges_is_not_related() {
        assertEquals(0, tp2.exercise_h(graph));

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        assertEquals(0, tp2.exercise_h(graph));
    }

    @Test
    public void exercise_h_non_related_graph_test() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge("A", "B");
        graph.addEdge("C", "D");

        assertEquals(0, tp2.exercise_h(graph));
    }

    @Test
    public void exercise_h_related_graph_test() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge("A", "B");
        graph.addEdge("C", "A");
        graph.addEdge("C", "D");

        assertEquals(2, tp2.exercise_h(graph));
    }

    //    Part 2:
    //    i) Dado dos grafos g1 y g2, verifique si g1 es subgrafo de g2.
    @Test
    public void exercise_j_sub_graph_test() {
        var graph = graphFactory.getGraph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addEdge("A", "D");
        graph.addEdge("C", "D");
        graph.addEdge("E", "D");
        graph.addEdge("E", "B");

        var graph1 = graphFactory.getGraph();
        graph1.addVertex("A");
        graph1.addVertex("C");
        graph1.addVertex("D");

        graph1.addEdge("A", "D");
        graph1.addEdge("C", "D");

        var graph2 = graphFactory.getGraph();
        graph2.addVertex("A");
        graph2.addVertex("C");
        graph2.addVertex("D");
        graph2.addVertex("B");
        graph2.addEdge("A", "D");
        graph2.addEdge("C", "D");

        var graph3 = graphFactory.getGraph();
        graph3.addVertex("A");

        var graph4 = graphFactory.getGraph();
        graph4.addVertex("A");
        graph4.addVertex("B");
        graph4.addEdge("C", "D");

        var graph5 = graphFactory.getGraph();
        graph5.addVertex("A");
        graph5.addVertex("B");
        graph5.addVertex("E");
        graph5.addEdge("A", "B");
        graph5.addEdge("B", "E");

        assertTrue(tp2.exercise_i(graph, graph1));
        assertTrue(tp2.exercise_i(graph, graph2));
        assertTrue(tp2.exercise_i(graph, graph3));
        assertFalse(tp2.exercise_i(graph, graph4));
        assertFalse(tp2.exercise_i(graph, graph5));
    }

    //    j) Dado dos grafos g1 y g2, verifique si g1 es subgrafo recubridor de g2.
    @Test
    public void exercise_j_covering_subgraph_test() {
        var graph_1 = graphFactory.getGraph();
        graph_1.addVertex("A");
        graph_1.addVertex("B");
        graph_1.addVertex("C");
        graph_1.addEdge("A", "B");
        graph_1.addEdge("A", "C");

        var graph_2 = graphFactory.getGraph();
        graph_1.addVertex("A");
        graph_1.addVertex("B");
        graph_1.addVertex("C");
        graph_1.addEdge("A", "B");
        graph_1.addEdge("A", "C");

        var graph_3 = graphFactory.getGraph();
        graph_1.addVertex("A");
        graph_1.addVertex("B");
        graph_1.addVertex("C");

        var graph_4 = graphFactory.getGraph();
        graph_1.addVertex("A");
        graph_1.addVertex("B");
        graph_1.addVertex("C");
        graph_1.addEdge("B", "C");
        graph_1.addEdge("A", "C");
        graph_1.addEdge("A", "B");

        var graph_5 = graphFactory.getGraph();
        graph_1.addVertex("A");
        graph_1.addVertex("B");
        graph_1.addVertex("D");
        graph_1.addEdge("A", "B");
        graph_1.addEdge("A", "D");

        var graph_6 = graphFactory.getGraph();
        graph_1.addVertex("A");
        graph_1.addVertex("B");
        graph_1.addEdge("A", "B");

        assertTrue(tp2.exercise_j(graph_1, graph_2));
        assertTrue(tp2.exercise_j(graph_1, graph_3));
        assertTrue(tp2.exercise_j(graph_1, graph_4));

        assertFalse(tp2.exercise_j(graph_1, graph_5));
        assertFalse(tp2.exercise_j(graph_1, graph_6));
    }

    //    k) Verificar si un grafo es completo.
    @Test
    public void exercise_k_test_an_empty_graph_is_not_complete() {
        assertFalse(tp2.exercise_k(graph));
    }

    @Test
    public void exercise_k_non_complete_graph() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge("A", "B");
        graph.addEdge("A", "B");
        graph.addEdge("A", "D");
        graph.addEdge("B", "C");
        graph.addEdge("B", "D");
        graph.addEdge("A", "A");

        assertFalse(tp2.exercise_k(graph));
    }

    @Test
    public void exercise_k_complete_graph() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge("A", "B");
        graph.addEdge("A", "B");
        graph.addEdge("A", "D");
        graph.addEdge("B", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "D");

        assertTrue(tp2.exercise_k(graph));
    }

    //    l) Hallar el grafo complementario.
    @Test
    public void exercise_l_complementary_graph_test_1() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge("A", "C");
        graph.addEdge("A", "D");

        var complementary_graph_1 = tp2.exercise_l(graph);

        assertTrue(complementary_graph_1.hasVertex("A"));
        assertTrue(complementary_graph_1.hasVertex("B"));
        assertTrue(complementary_graph_1.hasVertex("C"));
        assertTrue(complementary_graph_1.hasVertex("D"));

        assertFalse(complementary_graph_1.hasEdge("A", "C"));
        assertFalse(complementary_graph_1.hasEdge("A", "D"));

        assertTrue(complementary_graph_1.hasEdge("A", "B"));
        assertTrue(complementary_graph_1.hasEdge("B", "C"));
        assertTrue(complementary_graph_1.hasEdge("B", "D"));
        assertTrue(complementary_graph_1.hasEdge("C", "D"));

        assertEquals(4, complementary_graph_1.order());
        assertEquals(5, complementary_graph_1.alpha());
    }

    @Test
    public void exercise_l_complementary_graph_test_2() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("A", "D");
        graph.addEdge("B", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "D");

        var complementary_graph_2 = tp2.exercise_l(graph);

        assertTrue(complementary_graph_2.hasVertex("A"));
        assertTrue(complementary_graph_2.hasVertex("B"));
        assertTrue(complementary_graph_2.hasVertex("C"));
        assertTrue(complementary_graph_2.hasVertex("D"));

        assertFalse(complementary_graph_2.hasEdge("A", "B"));
        assertFalse(complementary_graph_2.hasEdge("A", "C"));
        assertFalse(complementary_graph_2.hasEdge("A", "D"));
        assertFalse(complementary_graph_2.hasEdge("B", "C"));
        assertFalse(complementary_graph_2.hasEdge("B", "D"));
        assertFalse(complementary_graph_2.hasEdge("C", "D"));

        assertEquals(4, complementary_graph_2.order());
        assertEquals(0, complementary_graph_2.alpha());
    }

    //    m) Calcular el grado de un vértice dado
    @Test
    public void exercise_m_test() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");

        graph.addEdge("A", "A");
        graph.addEdge("A", "C");
        graph.addEdge("B", "C");
        graph.addEdge("F", "C");
        graph.addEdge("D", "C");
        graph.addEdge("D", "B");
        graph.addEdge("F", "G");
        graph.addEdge("G", "H");

        assertEquals(3, tp2.exercise_m(graph, "A"));
        assertEquals(2, tp2.exercise_m(graph, "B"));
        assertEquals(4, tp2.exercise_m(graph, "C"));
        assertEquals(2, tp2.exercise_m(graph, "D"));
        assertEquals(0, tp2.exercise_m(graph, "E"));
        assertEquals(2, tp2.exercise_m(graph, "F"));
        assertEquals(2, tp2.exercise_m(graph, "G"));
        assertEquals(1, tp2.exercise_m(graph, "H"));
    }

    //    n) Retornar un arreglo con el grado de cada vértice
    @Test
    public void exercise_n_test() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");

        graph.addEdge("A", "A");
        graph.addEdge("A", "C");
        graph.addEdge("B", "C");
        graph.addEdge("F", "C");
        graph.addEdge("D", "C");
        graph.addEdge("D", "B");
        graph.addEdge("F", "G");
        graph.addEdge("G", "H");

        var result = tp2.exercise_n(graph);
        assertEquals(Integer.valueOf(3), result.get("A"));
        assertEquals(Integer.valueOf(2), result.get("B"));
        assertEquals(Integer.valueOf(4), result.get("C"));
        assertEquals(Integer.valueOf(2), result.get("D"));
        assertEquals(Integer.valueOf(0), result.get("E"));
        assertEquals(Integer.valueOf(2), result.get("F"));
        assertEquals(Integer.valueOf(2), result.get("G"));
        assertEquals(Integer.valueOf(1), result.get("H"));
    }

}