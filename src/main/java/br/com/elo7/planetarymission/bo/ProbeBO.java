package br.com.elo7.planetarymission.bo;

import java.util.Arrays;
import java.util.List;

import br.com.elo7.planetarymission.dao.ProbeDAO;
import br.com.elo7.planetarymission.dao.impl.ProbeDAOImpl;
import br.com.elo7.planetarymission.exceptions.LandingException;
import br.com.elo7.planetarymission.exceptions.MovementException;
import br.com.elo7.planetarymission.exceptions.RegistrationException;
import br.com.elo7.planetarymission.model.directions.Movement;
import br.com.elo7.planetarymission.model.equipment.impl.Probe;
import br.com.elo7.planetarymission.model.universe.Planet;

public class ProbeBO {

	private ProbeDAO dao = new ProbeDAOImpl();
	
	public boolean registerProbe(Probe equipment) throws RegistrationException {
		 
		boolean registered = dao.register(equipment);
		
		if (!registered) {
			throw new RegistrationException("Planetary Equipment [" + equipment.getName() + "] already exists!");
		}
		
		return registered;
	}
	
	public Probe find(final int equipmentId) throws RegistrationException {
		
		Probe probe = dao.find(equipmentId); 
		
		if (probe == null) {
			throw new RegistrationException();
		}
				
		return probe;
	}

	public List<Probe> list() {
		return dao.list();
	}
	
	public void land(final int equipmentId, final int planetId, final int positionX, final int positionY) throws LandingException {
		
		Planet planet = Planet.findPlanet(planetId);
		
		if (planet == null)
			throw new LandingException("Planet " + planetId + " does not exist! Cannot land!");
		
		Probe probe;
		try {
			probe = find(equipmentId);
		} catch (RegistrationException e) {
			throw new LandingException(e.getMessage());
		}
		
		if (probe.isLanded()) {
			throw new LandingException("Equipment [" + equipmentId + "] is already landed!");
		}
		
		probe.land(planet, positionX, positionY);
		
	}
	
	public void move(final Probe probe, final List<Movement> moves) throws MovementException {
		
		if (!probe.isLanded())
			throw new MovementException("Equipment is not landed, cannot move!");
		
		probe.travelRoute(moves);
		
	}

	public void move(final Probe probe, final Movement... moves) throws MovementException {
		move(probe, Arrays.asList(moves));
	}
	
}