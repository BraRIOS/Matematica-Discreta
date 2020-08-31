package graph.factory;

import graph.Graph;
import graph.GraphType;

public interface GraphFactory<T> {
    Graph<T> createFromType(GraphType type);
}
