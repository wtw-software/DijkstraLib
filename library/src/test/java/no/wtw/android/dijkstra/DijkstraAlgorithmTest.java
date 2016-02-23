package no.wtw.android.dijkstra;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import no.wtw.android.dijkstra.exception.PathNotFoundException;
import no.wtw.android.dijkstra.model.Edge;
import no.wtw.android.dijkstra.model.Graph;
import no.wtw.android.dijkstra.model.Vertex;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class DijkstraAlgorithmTest {

    private DijkstraAlgorithm da;
    private Graph graph;
    private List<Edge> model;

    @Before
    public void setUp() throws Exception {
        buildModel();
    }

    private void buildModel() throws PathNotFoundException {
        model = new ArrayList<Edge>();
        model.add(new Edge(new Vertex<>("1"), new Vertex<>("2"), 1));
        model.add(new Edge(new Vertex<>("2"), new Vertex<>("3"), 1));
        model.add(new Edge(new Vertex<>("1"), new Vertex<>("3"), 1));
        graph = new Graph(model);
        da = new DijkstraAlgorithm(graph);
        da.execute(new Vertex<>("1"));
    }

    @Test
    public void testAllObjectsCreated() throws Exception {
        assertNotNull(da);
        assertNotNull(graph);
        assertNotNull(model);
    }

    @Test
    public void testGetPath_validInput() throws Exception {
        LinkedList<Vertex> path = da.getPath(new Vertex<>("3"));
        assertTrue(path.size() == 2);
        assertTrue("1".equals(String.valueOf(path.get(0))));
        assertTrue("3".equals(String.valueOf(path.get(1))));
    }

    @Test
    public void testGetDistance_validInput() throws Exception {
        assertTrue(da.getDistance(new Vertex<>("1")) == 0);
        assertTrue(da.getDistance(new Vertex<>("2")) == 1);
        assertTrue(da.getDistance(new Vertex<>("3")) == 1);
    }

    @Test
    public void testGetShortestPathLength_validInput() throws Exception {
        assertTrue(da.getShortestPathLength(new Vertex<>("1"), new Vertex<>("1")) == 1);
        assertTrue(da.getShortestPathLength(new Vertex<>("1"), new Vertex<>("2")) == 2);
        assertTrue(da.getShortestPathLength(new Vertex<>("1"), new Vertex<>("3")) == 2);
    }

    @Test
    public void testGetShortestDistance_validInput() throws Exception {
        assertTrue(da.getShortestDistance(new Vertex<>("1"), new Vertex<>("1")) == -1);
        assertTrue(da.getShortestDistance(new Vertex<>("2"), new Vertex<>("2")) == 0);
        assertTrue(da.getShortestDistance(new Vertex<>("3"), new Vertex<>("3")) == 0);
    }

    @Test(expected = PathNotFoundException.class)
    public void testGetDistance_OneInvalidInput() throws PathNotFoundException {
        da.getDistance(new Vertex<>("5"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDistance_NullInput() throws PathNotFoundException {
        da.getDistance(null);
    }

    @Test(expected = PathNotFoundException.class)
    public void testGetShortestPathLength_TwoInvalidInputs() throws PathNotFoundException {
        da.getShortestPathLength(new Vertex<>("14"), new Vertex<>("Kåre"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShortestPathLength_NullVertices() throws PathNotFoundException {
        da.getShortestPathLength(null, null);
    }

    @Test(expected = PathNotFoundException.class)
    public void testGetShortestPathLength_OneInvalidInput() throws PathNotFoundException {
        da.getShortestPathLength(new Vertex<>("1"), new Vertex<>("5"));
    }

    @Test(expected = PathNotFoundException.class)
    public void testGetShortestDistance_OneInvalidInput() throws PathNotFoundException {
        da.getShortestDistance(new Vertex<>("1"), new Vertex<>("5"));
    }

    @Test(expected = PathNotFoundException.class)
    public void testGetShortestDistance_TwoInvalidInputs() throws PathNotFoundException {
        da.getShortestDistance(new Vertex<>("14"), new Vertex<>("Kåre"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetShortestDistance_NullVertices() throws PathNotFoundException {
        da.getShortestDistance(null, null);
    }

}