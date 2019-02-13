package br.com.elo7.planetarymission.model.universe;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

final class Universe {

	private static Universe universe;
	private static Set<Planet> planets = new HashSet<>();
	
	private Universe() { 
		BigBang.start();
	}
	
	static Universe getInstance() {
		
		if (universe == null) {
			universe = new Universe(); 
		}
		
		return universe;
	}
	
	boolean addPlanetToSpace(Planet planet) {
		return planets.add(planet);
	}
	
	Planet findPlanet(int planetId) {
		
		for (Planet planet : planets) {
			if (planet.getPlanetId() == planetId)
				return planet;
		}
		
		return null;
	}

	Set<Planet> getPlanets() {
		return planets;
	}
	
	private static abstract class BigBang {

		private static void start() {
			
			Random random = new Random();
			
			// earth is always here =D
			planets.add(new Planet(10, 10, "Earth"));
			
			// adding Mars to space
			planets.add(new Planet(10, 10, "Mars"));
			
			try (InputStream is = BigBang.class.getResourceAsStream("planets");
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
				Stream<String> stream = reader.lines();)
			{
				
				int x = 0;
				while (x < 5) {
					x = random.nextInt(100);
				}
				
				int dim = x;
				stream.forEach(line -> planets.add(new Planet(dim, dim, line)));
				
			} catch (Exception e) { 
				e.printStackTrace();
			}
			
		}
		
	}

}
