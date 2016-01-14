package no.wtw.android.dijkstra.model;

public class Vertex<T> {

    final T payload;

    public Vertex(T payload) {
        this.payload = payload;
    }

    public T getPayload() {
        return payload;
    }

    @Override
    public boolean equals(Object other) {
        try {
            return equals((Vertex) other);
        } catch (ClassCastException e) {
            return false;
        }
    }

    public boolean equals(Vertex other) {
        return payload.equals(other.getPayload());
    }

    @Override
    public int hashCode() {
        return payload.hashCode();
    }

    @Override
    public String toString() {
        return payload.toString();
    }

} 
