package no.wtw.android.dijkstra;

import no.wtw.android.dijkstra.model.Edge;
import no.wtw.android.dijkstra.model.Graph;
import no.wtw.android.dijkstra.model.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DijkstraAlgorithm {
	private static final String TAG = DijkstraAlgorithm.class.getSimpleName();
	private final List<Vertex> mNodes;
	private final List<Edge> mEdges;
	private Set<Vertex> mSettledNodes;
	private Set<Vertex> mUnSettledNodes;
	private Map<Vertex, Vertex> mPredecessors;
	private Map<Vertex, Integer> mDistance;
	private Vertex mSource;

	public DijkstraAlgorithm(Graph graph) {
		this.mNodes = new ArrayList<Vertex>(graph.getVertexes());
	    this.mEdges = new ArrayList<Edge>(graph.getEdges());
	}

	public void execute(Vertex source) {
		mSource = source;
        mSettledNodes = new HashSet<Vertex>();
        mUnSettledNodes = new HashSet<Vertex>();
        mDistance = new HashMap<Vertex, Integer>();
        mPredecessors = new HashMap<Vertex, Vertex>();
        mDistance.put(source, 0);
        mUnSettledNodes.add(source);
	    while (mUnSettledNodes.size() > 0) {
	    	Vertex node = getMinimum(mUnSettledNodes);
            mSettledNodes.add(node);
            mUnSettledNodes.remove(node);
	    	findMinimalDistances(node);
	    }
	}

	private void findMinimalDistances(Vertex node) {
		List<Vertex> adjacentNodes = getNeighbors(node);
		for (Vertex target : adjacentNodes) {
			if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
                mDistance.put(target, getShortestDistance(node) + getDistance(node, target));
                mPredecessors.put(target, node);
                mUnSettledNodes.add(target);
			}
		}
	}

	private int getDistance(Vertex node, Vertex target) {
		for (Edge edge : mEdges) {
			if (edge.getSource().equals(node) && edge.getDestination().equals(target)) {
				return edge.getWeight();
			}
	    }
		throw new RuntimeException("Should not happen");
	}

	private List<Vertex> getNeighbors(Vertex node) {
		List<Vertex> neighbors = new ArrayList<Vertex>();
		for (Edge edge : mEdges) {
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
	    return mSettledNodes.contains(vertex);
	}

	private int getShortestDistance(Vertex destination) {
	    Integer d = mDistance.get(destination);
	    if (d == null) {
	    	return Integer.MAX_VALUE;
	    } else {
	    	return d;
	    }
	}

	public LinkedList<Vertex> getPath(Vertex target) {
		if(target != null) {
			LinkedList<Vertex> path = new LinkedList<Vertex>();
			if(target.equals(mSource)) {
				path.add(target);
				return path;
			}

		    Vertex step = target;
		    if (mPredecessors.get(step) == null) {
		      return null;
		    }

		    path.add(step);
		    while (mPredecessors.get(step) != null) {
		      step = mPredecessors.get(step);
		      path.add(step);
		    }

		    Collections.reverse(path);
		    return path;
		}
		return null;
	}
}