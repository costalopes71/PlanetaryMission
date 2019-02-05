package br.com.elo7.planetarymission.tests;

import br.com.elo7.planetarymission.exceptions.LandingException;
import br.com.elo7.planetarymission.exceptions.MovementException;
import br.com.elo7.planetarymission.model.Movement;
import br.com.elo7.planetarymission.model.equipaments.impl.Probe;
import br.com.elo7.planetarymission.model.planets.Planet;
import br.com.elo7.planetarymission.model.planets.impl.Mars;

public class Main {

	public static void main(String[] args) {
		
		Planet mars = Mars.getMars();
		
		//TODO fazer o id do equipamento ser unico (um Set de ints na classe equipamento?)
		Probe probe1 = new Probe(1133);
		
		try {
			probe1.land(mars, 9, 9);
		} catch (LandingException e) {
			e.printStackTrace();
		}
		
		try {
			probe1.travelRoute(Movement.RIGHT, Movement.RIGHT, Movement.RIGHT, Movement.LEFT);
		} catch (MovementException e) {
			e.printStackTrace();
		}
		
		System.out.println(probe1.getCurrentDirection().toString());
		System.out.println("Probe " + probe1.getEquipmentId() + " at X: "+ probe1.getPositionX());
		System.out.println("Probe " + probe1.getEquipmentId() + " at Y: "+ probe1.getPositionY());
		
	}
	
}
