package br.com.elo7.planetarymission.tests;

import br.com.elo7.planetarymission.exceptions.LandingException;
import br.com.elo7.planetarymission.exceptions.MovementException;
import br.com.elo7.planetarymission.model.Movement;
import br.com.elo7.planetarymission.model.equipaments.impl.Probe;
import br.com.elo7.planetarymission.model.space.Earth.Nasa;

public class Main {

	public static void main(String[] args) {
		
		Probe probe1 = new Probe("Brazil´s Probe");
		
		Nasa.registerEquipment(probe1);
		Nasa.launchEquipment(probe1);
		
		try {
			probe1.land(1000, 9, 9);
		} catch (LandingException e1) {
			e1.printStackTrace();
		}
		
		try {
			probe1.travelRoute(Movement.RIGHT, Movement.RIGHT, Movement.RIGHT, Movement.LEFT);
		} catch (MovementException e) {
			e.printStackTrace();
		}
		
		System.out.println(probe1.getCurrentDirection().toString());
		System.out.println("Probe " + probe1.getEquipmentId() + " at X: "+ probe1.getPositionX());
		System.out.println("Probe " + probe1.getEquipmentId() + " at Y: "+ probe1.getPositionY());
		System.out.println("Probe photo " + probe1.getSerializedPhotoToEarth());
		
	}
	
}
