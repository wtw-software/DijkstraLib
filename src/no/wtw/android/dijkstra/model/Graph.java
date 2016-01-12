package no.wtw.android.dijkstra.model;

import java.util.List;

public class Graph<T> {

    private static final String TAG = Graph.class.getSimpleName();
    private final List<Vertex<T>> vertexes;
    private final List<Edge<T>> edges;

    public Graph(List<Vertex<T>> vertexes, List<Edge<T>> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
    }

    public List<Vertex<T>> getVertexes() {
        return vertexes;
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }
}
