package graph;

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
public class GraphTest extends SkipRule {

    private final GraphType type;
    private Graph<Integer> graph;
    private final GraphFactory<Integer> graphFactory;

    public GraphTest(GraphType type) {
        this.type = type;
        this.graphFactory = new GraphFactoryImpl<>();
        this.graph = graphFactory.createFromType(type);
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<GraphType> parameters() {
        return new ArrayList<>(Arrays.asList(GraphType.ADJACENCY_LIST, GraphType.ADJACENCY_MATRIX, GraphType.EDGE_ARRAY));
    }

    @Before
    public void before() {
        graph = graphFactory.createFromType(type);
    }

    @Test
    public void empty_graph_has_order_zero() {
        assertEquals(0, graph.order());
    }

    @Test
    public void empty_graph_has_alpha_zero() {
        assertEquals(0, graph.alpha());
    }

    @Test
    public void empty_graph_has_no_vertexes() {
        assertTrue(graph.getVertexes().isEmpty());
    }

    @Test
    public void vertex_list_test() {
        graph.addVertex(12);
        graph.addVertex(13);
        graph.addVertex(14);
        graph.addVertex(15);

        assertThat(graph.getVertexes(), CoreMatchers.hasItems(12, 13, 14, 15));
    }

    @Test
    public void adjacency_list_test() {
        graph.addVertex(12);
        graph.addVertex(13);

        graph.addEdge(12, 13);

        assertTrue(graph.hasEdge(12, 13));
        assertThat(graph.getAdjacencyList(12), CoreMatchers.hasItems(13));
        assertThat(graph.getAdjacencyList(13), CoreMatchers.hasItems(12));
    }

    @Test
    public void order_one_when_adding_one_element_to_a_graph() {
        graph.addVertex(2);
        assertEquals(1, graph.order());
        assertEquals(0, graph.alpha());
    }

    @Test
    public void get_vertex_test() {
        graph.addVertex(13);
        assertEquals(1, graph.order());
        assertTrue(graph.hasVertex(13));
    }

    @Test
    public void a_graph_should_do_nothing_if_adding_an_edge_between_two_non_existing_vertexes() {
        graph.addEdge(1989, 1999);

        assertFalse(graph.hasEdge(1989, 1999));

        assertFalse(graph.hasVertex(1989));
        assertFalse(graph.hasVertex(1999));
    }

    @Test
    public void remove_vertex_test() {
        graph.addVertex(13);
        assertEquals(1, graph.order());
        assertTrue(graph.hasVertex(13));
        graph.removeVertex(13);

        assertFalse(graph.hasVertex(13));
    }

    @Test
    public void get_a_vertex_that_does_not_exists() {
        assertFalse(graph.hasVertex(13));
    }

    @Test
    public void add_edge_test() {
        graph.addVertex(13);
        graph.addVertex(14);
        graph.addEdge(13, 14);
        assertTrue(graph.hasEdge(13, 14));
        assertTrue(graph.hasEdge(14, 13));

        assertEquals(1, graph.alpha());
    }

    @Test
    public void remove_edge_test() {
        graph.addVertex(11);
        graph.addVertex(12);
        graph.addEdge(11, 12);
        assertTrue(graph.hasEdge(11, 12));
        graph.removeEdge(11, 12);
        assertFalse(graph.hasEdge(11, 12));
    }

    @Test
    public void integration_test() {
        graph.addVertex(13);
        graph.addVertex(14);
        graph.addVertex(15);

        graph.addEdge(13, 14);
        graph.addEdge(14, 15);

        graph.addVertex(19);
        graph.addEdge(14, 19);

        assertTrue(graph.hasEdge(13, 14));
        assertTrue(graph.hasEdge(14, 15));
        assertTrue(graph.hasEdge(14, 19));

        assertTrue(graph.hasVertex(13));
        assertTrue(graph.hasVertex(14));
        assertTrue(graph.hasVertex(15));
        assertTrue(graph.hasVertex(19));
    }

    //    The idea behind this test is to test the expansion of the adjacency matrix
    //    At least one expansion is required to pass the test
    //    NOT expanding the matrix will result in failing the test
    //    Example of what not to do: Create a matrix of 1003 by 1003 in the constructor
    @Test
    public void expansion_test() {
        graph.addVertex(2002);
        graph.addVertex(2003);
        graph.addVertex(2004);

        graph.addEdge(2002, 2004);
        graph.addEdge(2003, 2002);

        for (int i = 0; i < 1000; i++) {
            graph.addVertex(i);
        }

        for (int i = 0; i < 1000; i++) {
            assertTrue(graph.hasVertex(i));
        }

        assertTrue(graph.hasVertex(2002));
        assertTrue(graph.hasVertex(2003));
        assertTrue(graph.hasVertex(2004));

        assertTrue(graph.hasEdge(2002, 2004));
        assertTrue(graph.hasEdge(2003, 2002));
    }

//    Tests that only apply to non directed graphs
    @Test
    public void non_directed_graph_test() {
        graph.addVertex(13);
        graph.addVertex(14);
        graph.addEdge(13, 14);

        assertTrue(graph.hasEdge(13, 14));
        assertTrue(graph.hasEdge(14, 13));
    }
}
