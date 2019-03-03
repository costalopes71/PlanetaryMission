package br.com.elo7.planetarymission.model.equipment;

import java.util.Collection;

import br.com.elo7.planetarymission.exceptions.MovementException;
import br.com.elo7.planetarymission.model.directions.Movement;

public interface Directional {

	void travelRoute(Collection<Movement> movements) throws MovementException;
	void travelRoute(Movement... movements) throws MovementException;
	
}
