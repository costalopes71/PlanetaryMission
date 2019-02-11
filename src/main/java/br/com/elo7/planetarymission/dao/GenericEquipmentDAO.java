package br.com.elo7.planetarymission.dao;

import java.util.List;

import br.com.elo7.planetarymission.exceptions.LandingException;
import br.com.elo7.planetarymission.exceptions.RegistrationException;
import br.com.elo7.planetarymission.model.equipaments.PlanetaryEquipment;

public interface GenericEquipmentDAO<T extends PlanetaryEquipment, K> {

	T find(K equipmentId) throws RegistrationException;
	boolean register(T equipment);
	List<T> list();
	void land(K equipmentId, int planetId, int positionX, int positionY) throws LandingException;
	
}
