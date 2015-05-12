package no.wtw.android.dijkstra;

import android.util.Log;
import no.wtw.android.dijkstra.model.Edge;
import no.wtw.android.dijkstra.model.Graph;
import no.wtw.android.dijkstra.model.Vertex;

import java.util.LinkedList;
import java.util.List;

public abstract class DijkstraCalculation {

    private static final String TAG = DijkstraCalculation.class.getSimpleName();

    protected List<Vertex> mNodes;
    protected List<Edge> mEdges;

    public abstract void setUpDataFromDatabase();

    public abstract void setUpData();

    protected DijkstraCalculation() {
    }

    protected Vertex getVertexFromListById(int id) {
        for (Vertex vertex : mNodes) {
            if (vertex.getId() == id) {
                return vertex;
            }
        }
        return null;
    }

    protected int getDistanceBetweenZones(int from, int to) {
        int distance = 0;
        for (Edge edge : mEdges) {
            if ((edge.getSource().getId() == from) && (edge.getDestination().getId() == to)) {
                distance = edge.getWeight();
            }
        }
        return distance;
    }

    public int calculateShortestPathBetween(int zoneFrom, int zoneTo) {
        int distance = -1;
        Graph graph = new Graph(mNodes, mEdges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        Vertex vertex = getVertexFromListById(zoneFrom);
        LinkedList<Vertex> path = null;
        if (vertex != null) {
            dijkstra.execute(vertex);
            path = dijkstra.getPath(getVertexFromListById(zoneTo));
        }

        if ((path != null) && (path.size() > 0)) {
            distance = path.size();
        } else {
            Log.e(TAG, "path is null");
        }

        return distance;
    }

    public int calculateShortestPathWithWeight(int zoneFrom, int zoneTo) {
        int distance = -1;
        Graph graph = new Graph(mNodes, mEdges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        Vertex vertex = getVertexFromListById(zoneFrom);
        LinkedList<Vertex> path = null;

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
