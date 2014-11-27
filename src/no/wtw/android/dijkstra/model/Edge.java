package no.wtw.android.dijkstra.model;

public class Edge {
	private static final String TAG = Edge.class.getSimpleName();
	private final String mId;
	private final Vertex mSource;
	private final Vertex mDestination;
	private final int mWeight;
	  
	public Edge(String id, Vertex source, Vertex destination, int weight) {
		this.mId = id;
	    this.mSource = source;
	    this.mDestination = destination;
	    this.mWeight = weight;
	}
	  
	public String getId() {
		return mId;
	}
	  
	public Vertex getDestination() {
	    return mDestination;
	}

	public Vertex getSource() {
	    return mSource;
	}
	  
	public int getWeight() {
	    return mWeight;
	}
	  
	@Override
	public String toString() {
		return mSource + " " + mDestination;
	}  
}
