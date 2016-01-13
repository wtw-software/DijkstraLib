package no.wtw.android.dijkstra.model;

import java.util.List;

public class Graph {

    private final List<Edge> edges;

    public Graph(List<Edge> edges) {
        this.edges = edges;
    }

    public List<Edge> getEdges() {
        return edges;
    }

}
