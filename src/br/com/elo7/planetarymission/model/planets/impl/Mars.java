package br.com.elo7.planetarymission.model.planets.impl;

import br.com.elo7.planetarymission.model.planets.Planet;

public final class Mars extends Planet {
	
	private static Mars mars;
	private static final int DIMENSION_X = 10;
	private static final int DIMENSION_Y = 10;
	
	//
	// constructors
	//
	private Mars() {
		super(DIMENSION_X, DIMENSION_Y);
	}

	public static Mars getMars() {
		
		if (mars == null) {
			mars = new Mars();
		}
		
		return mars;
	}

}
