package br.com.elo7.planetarymission.dao;

import java.util.List;

import br.com.elo7.planetarymission.exceptions.RegistrationException;
import br.com.elo7.planetarymission.model.equipment.PlanetaryEquipment;

public interface GenericEquipmentDAO<T extends PlanetaryEquipment, K> {

	T find(K equipmentId) throws RegistrationException;
	boolean register(T equipment);
	List<T> list();
	boolean remove(T equipment) throws RegistrationException;
	
}
