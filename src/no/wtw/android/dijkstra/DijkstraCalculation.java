package no.wtw.android.dijkstra;

import android.util.Log;
import no.wtw.android.dijkstra.model.Edge;
import no.wtw.android.dijkstra.model.Graph;
import no.wtw.android.dijkstra.model.Vertex;

import java.util.LinkedList;
import java.util.List;

public abstract class DijkstraCalculation<T> {

    private static final String TAG = DijkstraCalculation.class.getSimpleName();

    protected List<Vertex<T>> nodes;
    protected List<Edge<T>> edges;

    public abstract void setUpDataFromDatabase();

    protected DijkstraCalculation() {
    }

    protected Vertex<T> getVertexFromListById(String id) {
        for (Vertex<T> vertex : nodes) {
            if (vertex.getId() == id) {
                return vertex;
            }
        }
        return null;
    }

    protected Vertex<T> getVertexFromListById(T id) {
        for (Vertex<T> vertex : nodes) {
            if (vertex.getId() == id) {
                return vertex;
            }
        }
        return null;
    }

    protected int getDistanceBetweenZones(T from, T to) {
        int distance = 0;
        for (Edge<T> edge : edges) {
            if ((edge.getSource().getId() == from) && (edge.getDestination().getId() == to)) {
                distance = edge.getWeight();
            }
        }
        return distance;
    }

    public int calculateShortestPathBetween(T zoneFromId, T zoneToId) {
        return calculateDistance(getVertexFromListById(zoneFromId), getVertexFromListById(zoneToId));
    }

    private int calculateDistance(Vertex<T> zoneFrom, Vertex<T> zoneTo){
        int distance = -1;
        Graph<T> graph = new Graph(nodes, edges);
        DijkstraAlgorithm<T> dijkstra = new DijkstraAlgorithm<T>(graph);
        LinkedList<Vertex<T>> path = null;
        if (zoneFrom != null) {
            dijkstra.execute(zoneFrom);
            path = dijkstra.getPath(zoneTo);
        }

        if ((path != null) && (path.size() > 0)) {
            distance = path.size();
        } else {
            Log.e(TAG, "path is null");
        }

        return distance;

    }

    public int calculateShortestPathWithWeight(T zoneFrom, T zoneTo) {
        int distance = -1;
        Graph<T> graph = new Graph(nodes, edges);
        DijkstraAlgorithm<T> dijkstra = new DijkstraAlgorithm(graph);
        Vertex<T> vertex = getVertexFromListById(zoneFrom);
        LinkedList<Vertex<T>> path = null;

        if (vertex != null) {
            dijkstra.execute(vertex);
            path = dijkstra.getPath(getVertexFromListById(zoneTo));
        }

        if ((path != null) && (path.size() > 0)) {
            Log.d(TAG, "estimated path");
            for (int distanceCounter = 1; distanceCounter < path.size(); distanceCounter++) {
                distance += getDistanceBetweenZones(path.get(distanceCounter - 1).getId(), path.get(distanceCounter).getId());
            }
        } else {
            Log.e(TAG, "path is null");
        }

        return distance;
    }
}
