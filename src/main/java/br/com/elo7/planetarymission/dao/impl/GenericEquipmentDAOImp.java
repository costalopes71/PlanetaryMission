package br.com.elo7.planetarymission.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.elo7.planetarymission.dao.GenericEquipmentDAO;
import br.com.elo7.planetarymission.exceptions.LandingException;
import br.com.elo7.planetarymission.exceptions.RegistrationException;
import br.com.elo7.planetarymission.model.equipaments.PlanetaryEquipment;

public class GenericEquipmentDAOImp<T extends PlanetaryEquipment, K> implements GenericEquipmentDAO<T, K> {

	private static Set<PlanetaryEquipment> equipments = new HashSet<>();

	//.class da entidade (usado na busca)
	private Class<T> classe;
		
	//Construtor
	@SuppressWarnings("unchecked")
	public GenericEquipmentDAOImp() {		
		classe = (Class<T>) ((ParameterizedType)getClass()
			.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T find(K equipmentId) throws RegistrationException {
		
		PlanetaryEquipment equipment = equipments.stream()
				.filter(eqp -> eqp.getEquipmentId() == (int) equipmentId)
				.findAny()
				.orElse(null);

		if (equipment == null) {
			throw new RegistrationException();
		}

		return (T) equipment;
	}

	@Override
	public boolean register(T equipment) {
		return equipments.add(equipment);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> list() {
		
		List<T> list = new ArrayList<>();
		
		equipments.stream()
			.filter(eqp -> eqp.getClass() == classe)
			.forEach(eqp -> list.add((T) eqp));
		
		return list;
	}

	@Override
	public void land(K equipmentId, int planetId, int positionX, int positionY) throws LandingException {
		
		T equipment;
		try {
			equipment = find(equipmentId);
		} catch (RegistrationException e) {
			throw new LandingException(e.getMessage());
		}
		
		equipment.land(planetId, positionX, positionY);
		
	}
	
}