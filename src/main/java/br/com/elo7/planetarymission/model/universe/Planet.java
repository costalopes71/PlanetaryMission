package br.com.elo7.planetarymission.model.universe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import br.com.elo7.planetarymission.exceptions.MovementException;

public class Planet implements Comparable<Planet> {
	
	//
	// instance attributes
	//
	private Surface surface;
	private String name;
	private final int planetId;
	
	//
	// class attributes
	//
	private static AtomicInteger autoincremetedId = new AtomicInteger(1000);
	
	//
	// default constructor
	//
	public Planet(int dimensionX, int dimensionY, String name) {
		surface = new Surface(dimensionX, dimensionY);
		this.name = name;
		this.planetId = autoincremetedId.getAndIncrement();
	}
	
	//
	// getters & setters
	//
	
	public String getName() {
		return name;
	}
	
	public int getPlanetId() {
		return planetId;
	}
	
	public static Planet findPlanet(int planetId) {
		return Universe.getInstance().findPlanet(planetId);
	}
	
	public static List<Planet> listPlanets() {
		ArrayList<Planet> planets = new ArrayList<>(Universe.getInstance().getPlanets());
		Collections.sort(planets);
		return Collections.unmodifiableList(planets);
	}
	
	//
	// methods
	//
	
	public void occupySurfacePosition(final int positionX, final int positionY) throws MovementException {
		surface.occupyPosition(positionX, positionY);
	}
	
	public void clearSurfacePosition(final int positionX, final int positionY) {
		surface.clearOutPosition(positionX, positionY);
	}
	
	public boolean isSurfacePositionOccupied(final int positionX, final int positionY) throws MovementException {
		return surface.isPositionOccupied(positionX, positionY);
	}
	
	public void clearOutSurfacePosition(final int positionX, final int positionY) {
		surface.clearOutPosition(positionX, positionY);
	}
	
	public boolean[][] getSurfaceGround() {
		return surface.getGround();
	}
	
	//
	// equals & hashCode
	//
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planet other = (Planet) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	//
	// surface
	//
	
	private class Surface {
		
		private boolean[][] ground;
		
		private Surface(final int x, final int y) {
			this.ground = new boolean[x][y];
		}
		
		public void clearOutPosition(int positionX, int positionY) {
			ground[positionX][positionY] = false;
		}

		private boolean[][] getGround() {
			return ground.clone();
		}
		
		private boolean isPositionOccupied(final int positionX, final int positionY) throws MovementException {
			try {
				return ground[positionX][positionY];
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new MovementException("positions [" + positionX + "/" + positionY + "] are out of surface!");
			}
		}
		
		private void occupyPosition(final int positionX, final int positionY) throws MovementException {
			
			if (!isPositionOccupied(positionX, positionY)) {
				ground[positionX][positionY] = true;
			} else {
				throw new MovementException("Surface position is already occupied!");
			}
			
		}
		
	}

	@Override
	public int compareTo(Planet o) {
		return this.getName().compareTo(o.getName());
	}

}
