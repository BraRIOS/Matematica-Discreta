package tp1;

import graph.Graph;
import graph.GraphType;
import graph.factory.GraphFactory;
import graph.factory.GraphFactoryImpl;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import utils.SkipRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class Tp1Test extends SkipRule {

    private final GraphType type;
    private Graph<Integer> graph;
    private final GraphFactory<Integer> graphFactory;
    private final Tp1<Integer> tp1;

    public Tp1Test(GraphType type) {
        this.type = type;
        this.tp1 = new Tp1Impl<>();
        this.graphFactory = new GraphFactoryImpl<>();
        graph = graphFactory.createFromType(type);
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<GraphType> parameters() {
        return new ArrayList<>(Arrays.asList(GraphType.ADJACENCY_LIST, GraphType.ADJACENCY_MATRIX, GraphType.EDGE_ARRAY));
    }

    @Before
    public void before() {
        graph = graphFactory.createFromType(type);
    }

    //    a) Mostrar el grafo.
    @Test
    public void exercise_a_test_print() {
        tp1.exercise_a(graph);
    }

    //    b) Retornar la cantidad de lazos de un grafo.
    @Test
    public void exercise_b_test_graph_with_no_loops() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(2, 3);
        graph.addEdge(4, 5);

        assertEquals(0, tp1.exercise_b(graph));
    }

    @Test
    public void exercise_b_test_graph_with_loops() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(2, 3);
        graph.addEdge(4, 5);
        graph.addEdge(1, 1);
        graph.addEdge(4, 4);
        graph.addEdge(2, 2);

        assertEquals(3, tp1.exercise_b(graph));
    }

    //    c) Retornar un arreglo con los vértices que tienen lazos.
    @Test
    public void exercise_c_test_graph_with_no_loops() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(2, 3);
        graph.addEdge(4, 5);

        assertTrue(tp1.exercise_c(graph).isEmpty());
    }

    @Test
    public void exercise_c_test_graph_with_loops() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(2, 3);
        graph.addEdge(4, 5);
        graph.addEdge(1, 1);
        graph.addEdge(4, 4);
        graph.addEdge(2, 2);

        assertEquals(3, tp1.exercise_c(graph).size());
        assertThat(tp1.exercise_c(graph), CoreMatchers.hasItems(1, 2, 4));
    }

    //    d) Dado un vértice informar si es aislado.
    @Test
    public void exercise_d_test_a_graph_with_only_one_vertex_is_isolated() {
        graph.addVertex(2);
        assertTrue(tp1.exercise_d(graph, 2));
    }

    @Test
    public void exercise_d_some_isolated_vertexes() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(2, 3);
        graph.addEdge(4, 5);

        assertTrue(tp1.exercise_d(graph, 1));
        assertFalse(tp1.exercise_d(graph, 2));
        assertFalse(tp1.exercise_d(graph, 3));
        assertFalse(tp1.exercise_d(graph, 4));
        assertFalse(tp1.exercise_d(graph, 5));
    }

    //    e) Calcular cuantos vértices son aislados.
    @Test
    public void exercise_e_test_if_a_graph_has_no_edges_all_vertexes_are_isolated() {
        graph.addVertex(2);
        graph.addVertex(3);
        assertEquals(2, tp1.exercise_e(graph));
    }

    @Test
    public void exercise_e_test_no_isolated_vertexes() {
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(2, 3);
        graph.addEdge(4, 5);
        assertEquals(0, tp1.exercise_e(graph));
    }

    @Test
    public void exercise_e_some_isolated_vertexes() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 2);
        assertEquals(2, tp1.exercise_e(graph));
    }

    //    f) Retornar todos los vértices aislados.
    @Test
    public void exercise_f_test_if_a_graph_has_no_edges_all_vertexes_are_isolated() {
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        assertThat(tp1.exercise_f(graph), CoreMatchers.hasItems(2, 3, 4, 5));
    }

    @Test
    public void exercise_f_test_no_isolated_vertexes() {
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(2, 3);
        graph.addEdge(4, 5);
        assertTrue(tp1.exercise_f(graph).isEmpty());
    }

    @Test
    public void exercise_f_some_isolated_vertexes() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 2);
        assertThat(tp1.exercise_f(graph), CoreMatchers.hasItems(1, 5));
    }

    //    g) Dado un grafo debe retornar otro grafo sin lazos y sin vértices aislados.
    @Test
    public void exercise_g_test_graph_with_loops() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1,1);
        graph.addEdge(2,2);
        graph.addEdge(3,3);

        graph.addEdge(1,3);

        var graphWithNoLoops = tp1.exercise_g(graph);

        assertTrue(graphWithNoLoops.hasEdge(1,3));

        assertFalse(graphWithNoLoops.hasEdge(1,1));
        assertFalse(graphWithNoLoops.hasEdge(2,2));
        assertFalse(graphWithNoLoops.hasEdge(3,3));

        assertTrue(graph.hasVertex(1));
        assertTrue(graph.hasVertex(2));
        assertTrue(graph.hasVertex(3));
    }

    //    h) Calcular y mostrar la matriz de adyacencia.
    @Test
    public void exercise_h_test_graph_with_no_edges() {
        graph.addVertex(13);
        graph.addVertex(14);
        graph.addVertex(15);

        var adjacencyMatrix = tp1.exercise_h(graph);

        assertArrayEquals(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, adjacencyMatrix);
    }

    @Test
    public void exercise_h_test_graph_with_edges() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(5, 1);

        assertArrayEquals(new int[][]{{0, 0, 1, 0, 1}, {0, 0, 0, 1, 0}, {1, 0, 0, 0, 0}, {0, 1, 0, 0, 0}, {1, 0, 0, 0, 0}}, tp1.exercise_h(graph));
    }

    @Test
    public void exercise_h_test_from_the_presentation() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(1, 4);
        graph.addEdge(4, 5);

        assertArrayEquals(new int[][]{{0, 1, 0, 1, 0, 0}, {1, 0, 1, 0, 0, 0}, {0, 1, 0, 0, 0, 0}, {1, 0, 0, 0, 1, 0}, {0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0}}, tp1.exercise_h(graph));
    }

    //    i) Calcular y mostrar la matriz de incidencia.
    @Test
    public void exercise_i_test_from_the_presentation() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(5, 4);
        graph.addEdge(4, 6);
        graph.addEdge(3, 6);

        assertArrayEquals(new int[][]{{1,0,0,0,0,0,0,0}, {1,1,1,0,0,0,0,0}, {0,1,0,1,1,0,0,1}, {0,0,1,1,0,1,1,0}, {0,0,0,0,1,1,0,0}, {0,0,0,0,0,0,1,1}}, tp1.exercise_i(graph));
    }
}
