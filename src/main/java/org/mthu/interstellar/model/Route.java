package org.mthu.interstellar.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Route {

	@Id
	private int id;
	private String origin;
	private String destination;
	private float distance;
	//TODO Merge Route and Edge classes
	
	public Route() {
		super();
	}
	public Route(int id, String origin, String destination, float distance) {
		super();
		this.id = id;
		this.origin = origin;
		this.destination = destination;
		this.distance = distance;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public float getDistance() {
		return distance;
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}
	
	
	
}
