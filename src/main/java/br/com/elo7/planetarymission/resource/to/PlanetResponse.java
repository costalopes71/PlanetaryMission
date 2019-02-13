package br.com.elo7.planetarymission.resource.to;

import java.io.Serializable;

public class PlanetResponse implements Serializable {

	private static final long serialVersionUID = 4387748959407579061L;
	
	private String name;
	private int planetId;

	public PlanetResponse() {

	}
	
	public PlanetResponse(String name, int planetId) {
		this.name = name;
		this.planetId = planetId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPlanetId() {
		return planetId;
	}

	public void setPlanetId(int planetId) {
		this.planetId = planetId;
	}

}
