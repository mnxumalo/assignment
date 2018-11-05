package org.mthu.interstellar.endpoint;

import java.util.List;

import org.mthu.interstellar.model.Vertex;
import org.mthu.interstellar.service.ShortestPathService;
import org.mthu.shortestpath.Planet;
import org.mthu.shortestpath.ShortestPathServiceRequest;
import org.mthu.shortestpath.ShortestPathServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ShortestPathEndpoint {

	private static final String NAMESPACE_URI = "http://www.mthu.org/ShortestPath";
	
	@Autowired
	private ShortestPathService service;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "ShortestPathServiceRequest")
	@ResponsePayload
	public ShortestPathServiceResponse getShortestPath(@RequestPayload ShortestPathServiceRequest request) {
		ShortestPathServiceResponse response = new ShortestPathServiceResponse();
		List<Vertex> path = service.getPath(request.getSource(), request.getDestination());
		
		for (Vertex vertex : path) {
			if(vertex == null)
				continue;
			
			Planet planet = new Planet();
			planet.setName(vertex.getName());
			planet.setNode(vertex.getId());
			response.getPlanets().add(planet);
		}	
		
		return response;
	}
}
