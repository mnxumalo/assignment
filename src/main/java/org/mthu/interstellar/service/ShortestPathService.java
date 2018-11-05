package org.mthu.interstellar.service;

import java.util.List;

import org.mthu.interstellar.model.Vertex;

public interface ShortestPathService {

	List<Vertex> getPath(String source, String destination);
}
