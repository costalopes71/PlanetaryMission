package br.com.elo7.planetarymission.model.planets;

import br.com.elo7.planetarymission.exceptions.MovementException;

public abstract class Planet {

	//TODO pensar em tornar a superficie um objeto e nao diretamente uma matriz
	private boolean[][] surface;
	
	//
	// constructors
	//
	protected Planet(int dimensionX, int dimensionY) {
		surface = new boolean[dimensionX][dimensionY];
	}
	
	//
	// getters & setters
	//
	public synchronized boolean[][] getSurface() {
		return surface.clone();
	}

	//
	// getters & setters
	//
	public String getName() {
		return this.getClass().getSimpleName();
	}
	
	public synchronized boolean isPositionOccupied(final int positionX, final int positionY) {
		return surface[positionX][positionY];
	}
	
	public synchronized void occupyPosition(final int positionX, final int positionY) throws MovementException {
		if (!isPositionOccupied(positionX, positionY)) {
			try {
				surface[positionX][positionY] = true;
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new MovementException("positions [" + positionX + "/" + positionY + "] are out of " + this.getName() + " surface!");
			}
		} else {
			throw new MovementException("Surface position is already occupied!");
		}
	}
	
}
