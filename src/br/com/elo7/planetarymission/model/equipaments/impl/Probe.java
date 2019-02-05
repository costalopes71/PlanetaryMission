package br.com.elo7.planetarymission.model.equipaments.impl;

import br.com.elo7.planetarymission.model.CardinalPoint;
import br.com.elo7.planetarymission.model.equipaments.PlanetaryEquipment;

public final class Probe extends PlanetaryEquipment {
	
	private static final long serialVersionUID = 1473730384674867126L;

	//
	// constructors
	//
	public Probe(final int probeId) {
		super(probeId);
	}
	
	public Probe(final int probeId, final CardinalPoint landingCardinalDirection) {
		super(probeId, landingCardinalDirection);
	}

	//
	// probe methods
	//
	public String takePhoto() {
		
		
		
		return null;
	}
	
}
