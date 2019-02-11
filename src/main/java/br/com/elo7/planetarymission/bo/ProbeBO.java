package br.com.elo7.planetarymission.bo;

import java.util.List;

import br.com.elo7.planetarymission.dao.ProbeDAO;
import br.com.elo7.planetarymission.dao.impl.ProbeDAOImpl;
import br.com.elo7.planetarymission.exceptions.LandingException;
import br.com.elo7.planetarymission.exceptions.RegistrationException;
import br.com.elo7.planetarymission.model.equipaments.impl.Probe;

public class ProbeBO {

	private ProbeDAO dao = new ProbeDAOImpl();
	
	public boolean registerProbe(Probe equipment) {
		return dao.register(equipment);
	}
	
	public Probe find(final int equipmentId) throws RegistrationException {
		return dao.find(equipmentId);
	}

	public List<Probe> list() {
		return dao.list();
	}
	
	public void land(int equipmentId, int planetId, int positionX, int positionY) throws LandingException {
		dao.land(equipmentId, planetId, positionX, positionY);
	}
	
}
