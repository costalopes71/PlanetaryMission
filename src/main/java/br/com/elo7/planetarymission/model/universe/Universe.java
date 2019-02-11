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

	private static Universe space;
	private static Set<Planet> planets = new HashSet<>();
	
	private Universe() { 
		BigBang.start();
	}
	
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
				
				int x = random.nextInt(100); 
				stream.forEach(line -> planets.add(new Planet(x, x, line)));
				
			} catch (Exception e) { 
				e.printStackTrace();
			}
			
		}
		
	}
	
}
