package no.wtw.android.dijkstra.exception;

public class PathNotFoundException extends Exception {

    public PathNotFoundException() {
        super("Path from source to destination vertex was not found");
    }

    public PathNotFoundException(String msg) {
        super(msg);
    }

}
