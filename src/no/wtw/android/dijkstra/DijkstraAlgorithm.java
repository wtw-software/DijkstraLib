package no.wtw.android.dijkstra;

import no.wtw.android.dijkstra.model.Edge;
import no.wtw.android.dijkstra.model.Graph;
import no.wtw.android.dijkstra.model.Vertex;

import java.util.*;

public class DijkstraAlgorithm<T> {

    private static final String TAG = DijkstraAlgorithm.class.getSimpleName();
    private final List<Vertex<T>> nodes;
    private final List<Edge<T>> edges;
    private Set<Vertex<T>> settledNodes;
    private Set<Vertex<T>> unSettledNodes;
    private Map<Vertex<T>, Vertex<T>> predecessors;
    private Map<Vertex<T>, Integer> distance;
    private Vertex<T> source;

    public DijkstraAlgorithm(Graph<T> graph) {
        this.nodes = new ArrayList<>(graph.getVertexes());
        this.edges = new ArrayList<>(graph.getEdges());
    }

    public void execute(Vertex<T> source) {
        this.source = source;
        settledNodes = new HashSet<>();
        unSettledNodes = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Vertex<T> node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Vertex<T> node) {
        List<Vertex<T>> adjacentNodes = getNeighbors(node);
        for (Vertex target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node) + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }
    }

    private int getDistance(Vertex<T> node, Vertex<T> target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node) && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<Vertex<T>> getNeighbors(Vertex<T> node) {
        List<Vertex<T>> neighbors = new ArrayList<>();
        for (Edge<T> edge : edges) {
            if (edge.getSource().equals(node) && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private Vertex<T> getMinimum(Set<Vertex<T>> vertexes) {
        Vertex<T> minimum = null;
        for (Vertex<T> vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(Vertex<T> vertex) {
        return settledNodes.contains(vertex);
    }

    private int getShortestDistance(Vertex<T> destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    public LinkedList<Vertex<T>> getPath(Vertex<T> target) {
        if (target != null) {
            LinkedList<Vertex<T>> path = new LinkedList<>();
            if (target.equals(source)) {
                path.add(target);
                return path;
            }

            Vertex<T> step = target;
            if (predecessors.get(step) == null) {
                return null;
            }

            path.add(step);
            while (predecessors.get(step) != null) {
                step = predecessors.get(step);
                path.add(step);
            }

            Collections.reverse(path);
            return path;
        }
        return null;
    }

    public int getDistance(Vertex<T> vertex) {
        if(vertex != null && distance != null) {
            return distance.get(vertex);
        }
        return -1;
    }
}