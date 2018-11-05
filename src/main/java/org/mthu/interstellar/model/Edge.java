package org.mthu.interstellar.model;

public class Edge {

	private String id;
	private Vertex source;
	private Vertex destination;
	private float weight;
	
	
	public Edge(String id, Vertex source, Vertex destination, float weight) {
		super();
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Vertex getSource() {
		return source;
	}
	public void setSource(Vertex source) {
		this.source = source;
	}
	public Vertex getDestination() {
		return destination;
	}
	public void setDestination(Vertex destination) {
		this.destination = destination;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	
	
}
