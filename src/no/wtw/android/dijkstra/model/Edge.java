package no.wtw.android.dijkstra.model;

public class Edge<T> {

    private static final String TAG = Edge.class.getSimpleName();
    private final T id;
    private final Vertex source;
    private final Vertex destination;
    private final int weight;

    public Edge(T id, Vertex source, Vertex destination, int weight) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public T getId() {
        return id;
    }

    public Vertex getDestination() {
        return destination;
    }

    public Vertex getSource() {
        return source;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }
}
