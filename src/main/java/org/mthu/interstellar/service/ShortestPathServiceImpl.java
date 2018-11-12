package org.mthu.interstellar.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.mthu.interstellar.business.DijkstraAlgorithm;
import org.mthu.interstellar.model.Edge;
import org.mthu.interstellar.model.Graph;
import org.mthu.interstellar.model.Planet;
import org.mthu.interstellar.model.Route;
import org.mthu.interstellar.model.Vertex;
import org.mthu.interstellar.repository.PlanetJpaRepository;
import org.mthu.interstellar.repository.RouteJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShortestPathServiceImpl implements ShortestPathService{

	@Autowired
	RouteJpaRepository routeRepository;
	
	@Autowired
	PlanetJpaRepository planetRepository;
	
	@Override
	public List<Vertex> getPath(String sourceNode, String destinationNode) {
		Map<String,Vertex> nodes = new HashMap<>();
		List<Edge> edges = new ArrayList<Edge>();
		
		List<Planet> planets = planetRepository.findAll();
		
		for (Planet planet : planets) {
			Vertex vertex = new Vertex(planet.getNode(), planet.getName());
			nodes.put(planet.getNode(), vertex);
		}
		
		List<Route> routes = routeRepository.findAll();
		
		for (Route route : routes) {
			Vertex origin = nodes.get(route.getOrigin());
			Vertex destination = nodes.get(route.getDestination());
			
			if(origin != null && destination != null) {
				Edge edge = new Edge(String.valueOf(route.getId()), origin, destination, route.getDistance());
				edges.add(edge);
			}
		}
		List<Vertex> vertexList = new ArrayList<>();
		for (Vertex vertex : nodes.values()) {
			vertexList.add(vertex);
		}
		 	Graph graph = new Graph(vertexList, edges);
	        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
	        dijkstra.buildPath(nodes.get("A"));
	        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(destinationNode));
	        return path;
	}

}
