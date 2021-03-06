package org.mthu.interstellar.business;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.mthu.interstellar.model.Edge;
import org.mthu.interstellar.model.Graph;
import org.mthu.interstellar.model.Vertex;

public class TestDijkstraAlgorithm {

	private List<Vertex> nodes;
    private List<Edge> edges;

    
    
    @Test
    public void testBuildPath() {
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        
        Vertex vertex = new Vertex("A", "Earth");
        Vertex vertex2 = new Vertex("B", "Moon");
        Vertex vertex3 = new Vertex("C", "Jupiter");
        Vertex vertex4 = new Vertex("D", "Venus");
        Vertex vertex5 = new Vertex("E", "Mars");
        Vertex vertex6 = new Vertex("F", "Saturn");
        Vertex vertex7 = new Vertex("G", "Uranus");
        Vertex vertex8 = new Vertex("H", "Pluto");
        Vertex vertex9 = new Vertex("I", "Neptune");
        Vertex vertex10 = new Vertex("J", "Mercury");
        Vertex vertex11 = new Vertex("K", "Alpha Centauri");
        nodes.add(vertex);
        nodes.add(vertex2);
        nodes.add(vertex3);
        nodes.add(vertex4);
        nodes.add(vertex5);
        nodes.add(vertex5);
        nodes.add(vertex6);
        nodes.add(vertex7);
        nodes.add(vertex8);
        nodes.add(vertex9);
        nodes.add(vertex10);
        nodes.add(vertex11);
        
        
        addLane("Edge_0", vertex, vertex2, 0.44f);
        addLane("Edge_1", vertex, vertex2, 217f);
        addLane("Edge_2", vertex, vertex5, 173f);
        addLane("Edge_3", vertex3, vertex7, 186f);
        addLane("Edge_4", vertex3, vertex8, 103f);
        addLane("Edge_5", vertex4, vertex8, 183f);
        addLane("Edge_6", vertex6, vertex9, 250f);
        addLane("Edge_7", vertex9, vertex10, 84f);
        addLane("Edge_8", vertex8, vertex10, 167f);
        addLane("Edge_9", vertex5, vertex10, 502f);
        addLane("Edge_10", vertex10, vertex11, 40f);
        addLane("Edge_11", vertex2, vertex11, 600f);

        // Lets check from location Loc_1 to Loc_10
        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.buildPath(nodes.get(0));
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(10));
        assertNotNull(path);
        assertTrue(path.size() > 0);

        for (Vertex verte : path) {
            System.out.println(verte);
        }       

    }
    
    
    
    private void addLane(String laneId, Vertex source, Vertex dest,
            Float duration) {
        Edge lane = new Edge(laneId,source, dest, duration );
        edges.add(lane);
    }
}
