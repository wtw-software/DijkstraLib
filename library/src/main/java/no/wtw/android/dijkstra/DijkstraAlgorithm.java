package no.wtw.android.dijkstra;

import no.wtw.android.dijkstra.exception.PathNotFoundException;
import no.wtw.android.dijkstra.model.Edge;
import no.wtw.android.dijkstra.model.Graph;
import no.wtw.android.dijkstra.model.Vertex;

import java.util.*;

public class DijkstraAlgorithm {

    private final List<Edge> edges;
    private Set<Vertex> settledNodes;
    private Set<Vertex> unSettledNodes;
    private Map<Vertex, Vertex> predecessors;
    private Map<Vertex, Integer> distance;
    private Vertex source;

    public DijkstraAlgorithm(Graph graph) {
        this.edges = new ArrayList<>(graph.getEdges());
    }

    public DijkstraAlgorithm execute(Vertex source) throws PathNotFoundException {
        this.source = source;
        settledNodes = new HashSet<>();
        unSettledNodes = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Vertex node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
        return this;
    }

    private void findMinimalDistances(Vertex node) throws PathNotFoundException {
        List<Vertex> adjacentNodes = getNeighbors(node);
        for (Vertex target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node) + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }
    }

    private List<Vertex> getNeighbors(Vertex node) {
        List<Vertex> neighbors = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node) && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private Vertex getMinimum(Set<Vertex> vertexes) {
        Vertex minimum = null;
        for (Vertex vertex : vertexes) {
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

    private boolean isSettled(Vertex vertex) {
        return settledNodes.contains(vertex);
    }

    private int getShortestDistance(Vertex destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    public LinkedList<Vertex> getPath(Vertex target) throws PathNotFoundException {
        if (target != null) {
            LinkedList<Vertex> path = new LinkedList<>();
            if (target.equals(source)) {
                path.add(target);
                return path;
            }

            Vertex step = target;
            if (predecessors.get(step) == null)
                throw new PathNotFoundException();

            path.add(step);
            while (predecessors.get(step) != null) {
                step = predecessors.get(step);
                path.add(step);
            }

            Collections.reverse(path);
            return path;
        }
        throw new PathNotFoundException();
    }

    public int getDistance(Vertex vertex) throws PathNotFoundException {
        if (vertex == null || distance == null)
            throw new IllegalArgumentException("Destination vertex can not be null");
        if(distance.get(vertex) == null) {
            throw new PathNotFoundException();
        }
        return distance.get(vertex);
    }

    private int getDistance(Vertex from, Vertex to) throws PathNotFoundException {
        for (Edge edge : edges) {
            if (edge.getSource().equals(from) && edge.getDestination().equals(to)) {
                return edge.getWeight();
            }
        }
        throw new PathNotFoundException();
    }

    public int getShortestPathLength(Vertex from, Vertex to) throws PathNotFoundException {
        if (from == null || to == null)
            throw new IllegalArgumentException("Source and destination vertices can not be null");
        return getPath(to).size();
    }

    public int getShortestDistance(Vertex from, Vertex to) throws PathNotFoundException {
        if (from == null || to == null)
            throw new IllegalArgumentException("Source and destination vertices can not be null");
        LinkedList<Vertex> path = getPath(to);
        int distance = -1;
        for (int i = 1; i < path.size(); i++)
            distance += getDistance(path.get(i - 1), path.get(i));
        return distance;
    }

}