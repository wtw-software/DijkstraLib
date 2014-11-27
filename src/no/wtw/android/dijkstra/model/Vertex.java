package no.wtw.android.dijkstra.model;

public class Vertex {
	private static final String TAG = Vertex.class.getSimpleName();
	final private int mId;
	final private String mName;
	
	public Vertex(int id, String name) {
		this.mId = id;
		this.mName = name;
	}
	 
	public int getId() {
		return mId;
	}

	public String getName() {
		return mName;
	}
	  
	@Override
	public int hashCode() {
	    return mId;
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
	    Vertex other = (Vertex) obj;
	    if (mId == -1) {
	    	if (other.mId != -1) {
	    		return false;
	    	}
	    } else if (mId != (other.mId)) {
	    	return false;
	    }
	    return true;
	}

	@Override
	public String toString() {
		return mName;
	}	  
} 
