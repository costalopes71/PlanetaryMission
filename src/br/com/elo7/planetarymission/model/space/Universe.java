package br.com.elo7.planetarymission.model.space;

import java.util.HashSet;
import java.util.Set;

final class Universe {

	private static Universe space;
	private static Set<Planet> planets = new HashSet<>();
	
	//
	// static constructor
	//
	static {
		
		// earth is always here =D
		planets.add(new Earth());
		
		// adding Mars to space
		planets.add(new Planet(10, 10, "Mars"));
		
		// start bigbang
		
	}
	
	private Universe() { }
	
	static Universe getInstance() {
		
		if (space == null) {
			space = new Universe(); 
		}
		
		return space;
	}
	
	boolean addPlanetToSpace(Planet planet) {
		return planets.add(planet);
	}
	
	Planet getPlanet(int planetId) {
		
		for (Planet planet : planets) {
			if (planet.getPlanetId() == planetId)
				return planet;
		}
		
		return null;
	}
	
}
