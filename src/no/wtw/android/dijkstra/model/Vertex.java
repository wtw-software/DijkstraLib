package no.wtw.android.dijkstra.model;

public class Vertex<T> {

    private static final String TAG = Vertex.class.getSimpleName();
    final private T id;
    final private String name;

    public Vertex(T id, String name) {
        this.id = id;
        this.name = name;
    }

    public T getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Vertex<T> other = (Vertex<T>) obj;
        if (id != (other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
} 
