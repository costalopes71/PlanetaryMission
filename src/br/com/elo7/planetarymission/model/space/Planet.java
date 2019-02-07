package br.com.elo7.planetarymission.model.space;

import java.util.concurrent.atomic.AtomicInteger;

import br.com.elo7.planetarymission.exceptions.MovementException;

public class Planet {

	private Surface surface;
	private String name;
	private final int planetId;
	private static AtomicInteger autoincremetedId = new AtomicInteger(1000);
	
	//
	// default constructor
	//
	Planet(int dimensionX, int dimensionY, String name) {
		surface = new Surface(dimensionX, dimensionY);
		this.name = name;
		this.planetId = autoincremetedId.getAndIncrement();
	}
	
	/**
	 * Creates a <code>Planet</code> and put it in the <code>Space</code>. 
	 * If the planet already exists in the space it won´t add it again.
	 * Will return true if the planet was created and added to space, and false if planet was not added to Space.
	 * @param dimensionX, X abscissa dimension of planet surface 
	 * @param dimensionY, Y abscissa dimension of planet surface
	 * @param <code>String</code> name, planet´s name 
	 * @return <code>boolean</code>, true if <code>Planet</code> was created and added to <code>Space</code>, false if it was not.
	 */
	public static boolean createPlanet(int dimensionX, int dimensionY, String name) {
		return Space.getInstance().addPlanetToSpace(new Planet(dimensionX, dimensionY, name));
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
	
	public static Planet getPlanet(int planetId) {
		return Space.getInstance().getPlanet(planetId);
	}
	
	private synchronized Surface getSurface() {
		return surface;
	}
	
	//
	// methods
	//
	
	public boolean isSurfacePositionOcuppied(final int positionX, final int positionY) throws MovementException {
		return getSurface().isPositionOccupied(positionX, positionY);
	}
	
	public void occupySurfacePosition(final int positionX, final int positionY) throws MovementException {
		getSurface().occupyPosition(positionX, positionY);
	}
	
	public boolean[][] getSurfaceGround() {
		return getSurface().getGround();
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
		
		Surface(int x, int y) {
			this.ground = new boolean[x][y];
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
	
}
