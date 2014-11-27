package no.wtw.android.dijkstra.model;

import java.util.List;

public class Graph {
	private static final String TAG = Graph.class.getSimpleName();
	private final List<Vertex> mVertexes;
	private final List<Edge> mEdges;

	public Graph(List<Vertex> vertexes, List<Edge> edges) {
		this.mVertexes = vertexes;
	    this.mEdges = edges;
	}

	public List<Vertex> getVertexes() {
		return mVertexes;
	}

	public List<Edge> getEdges() {
	    return mEdges;
	}
}
