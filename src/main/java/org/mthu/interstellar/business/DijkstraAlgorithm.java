package org.mthu.interstellar.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import org.mthu.interstellar.model.Edge;
import org.mthu.interstellar.model.Graph;
import org.mthu.interstellar.model.Vertex;

public class DijkstraAlgorithm {

	
	private List<Edge> edges;
    private Set<Vertex> settledNodes;
    private Set<Vertex> unSettledNodes;
    private Map<Vertex, Vertex> predecessors;
    private PriorityQueue<Vertex> vertexPriorityQueue;
    public DijkstraAlgorithm(Graph graph) {
        
    	this.edges = new ArrayList<>(graph.getEdges());
        settledNodes = new HashSet<>();
        unSettledNodes = new HashSet<>();        
        predecessors = new HashMap<>();
        vertexPriorityQueue = new PriorityQueue<>(10, new PriorityQueueComparator());
    }
    
    public void buildPath(Vertex source) {
        
        source.setDistance(0.0f);
        vertexPriorityQueue.add(source);
        unSettledNodes.add(source);

        while (unSettledNodes.size() > 0) {
        	
            Vertex SourceNode = vertexPriorityQueue.poll();
            settledNodes.add(SourceNode);
            unSettledNodes.remove(SourceNode);

            Set<Vertex> adjacentNodes = getAdjacentNodes(SourceNode);

            for (Vertex adjacentNode : adjacentNodes) {

                float adjacentNodeDistance = adjacentNode.getDistance();
                float sourceNodeDistance = source.getDistance();
                float adjacentNodeWeight = getDistanceBetweenAdjacentNodes(SourceNode, adjacentNode);

                if (adjacentNodeDistance > sourceNodeDistance + adjacentNodeWeight) {
                    //update adjacent node distance
                    adjacentNodeDistance = sourceNodeDistance + adjacentNodeWeight;                    
                    adjacentNode.setDistance(adjacentNodeDistance);
                    vertexPriorityQueue.add(adjacentNode);
                    predecessors.put(adjacentNode, SourceNode);
                    unSettledNodes.add(adjacentNode);
                }
            }

        }
    }
    
    private float getDistanceBetweenAdjacentNodes(Vertex sourceNode, Vertex destinationNode) {
        float weight = 0.0f;
        for (Edge edge : edges) {
            if (edge.getSource().equals(sourceNode)
                    && edge.getDestination().equals(destinationNode)) {
                weight = edge.getWeight();
            }
        }
        return weight;
    }
    
    
    private Set<Vertex> getAdjacentNodes(Vertex sourceNode) {
        Set<Vertex> adjacencySet = new HashSet<>();
        for (Edge edge : edges)
            if (edge.getSource().equals(sourceNode) && edge.getDestination() != null && !settledNodes.contains(edge.getDestination())) {
                adjacencySet.add(edge.getDestination());
            }
        return adjacencySet;
    }
    
    public LinkedList<Vertex> getPath(Vertex destinationNode) {
        LinkedList<Vertex> path = new LinkedList<>();
        Vertex step = destinationNode;

        if (predecessors.get(step) == null) {
            return null;//path does not exist, return null
        }
        path.add(step);
        //traverse the list backwards from the destination to the source
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }

        Collections.reverse(path);
        return path;
    }    
    
    
}
