package graph.factory;

import graph.*;

public class GraphFactoryImpl<T> implements GraphFactory<T> {
    @Override
    public Graph<T> createFromType(GraphType type) {
        return switch (type) {
            case ADJACENCY_MATRIX -> new AdjacencyMatrixGraphImpl<>();
            case ADJACENCY_LIST -> new AdjacencyListGraphImpl<>();
            case EDGE_ARRAY -> new EdgeArrayGraphImpl<>();
        };
    }

    //    Returns a random graph
    @Override
    public Graph<T> getGraph() {
//        return new EdgeArrayGraphImpl<>();
        return new AdjacencyMatrixGraphImpl<>();
//        return new AdjacencyListGraphImpl<>();
    }
}