package br.com.elo7.planetarymission.model.equipaments;

import br.com.elo7.planetarymission.exceptions.MovementException;
import br.com.elo7.planetarymission.model.Movement;

public interface Directional {

	void travelRoute(Movement... movements) throws MovementException;

}
