package br.com.elo7.planetarymission.model.equipment.impl;

import java.util.Collection;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

import br.com.elo7.planetarymission.exceptions.LandingException;
import br.com.elo7.planetarymission.exceptions.MovementException;
import br.com.elo7.planetarymission.exceptions.SerializePhotosException;
import br.com.elo7.planetarymission.model.directions.Movement;
import br.com.elo7.planetarymission.model.equipment.PlanetaryEquipment;
import br.com.elo7.planetarymission.model.universe.Planet;
import br.com.elo7.planetarymission.service.CamerasService;
import br.com.elo7.planetarymission.service.impl.ProbeCamerasService;
import br.com.elo7.planetarymission.service.response.Photos;

public final class Probe extends PlanetaryEquipment {
	
	private static final long serialVersionUID = -2920852042538670656L;
	
	private Queue<Photos> photosQueue = new LinkedBlockingQueue<>();
	private CamerasService camerasService;
	private String serializedPhotoToEarth;

	//
	// constructors
	//
	public Probe() {

	}
	
	public Probe(final String name) {
		super(name);
		
		camerasService = new ProbeCamerasService();
		
	}
	
	//
	// getters & setters
	//
	public String getSerializedPhotoToEarth() {
		return isLanded() ? serializedPhotoToEarth : "only landed equipments can take photos!";
	};
	
	@Override
	public void travelRoute(Collection<Movement> movements) throws MovementException {
		
		super.travelRoute(movements);
		
		//
		// se moveu com sucesso para a rota definida, tira uma foto da superficie de marte
		//
		takePhoto();
		
	}
	
	@Override
	public void land(Planet planet, int positionX, int positionY) throws LandingException {
		
		super.land(planet, positionX, positionY);
		
		//
		// apos pousar tira foto da aterrisagem
		//
		takePhoto();
		
	}
	
	private void takePhoto() {
		
		//
		// se estiver pousado pode tirar fotos da superficie
		//
		if (isLanded()) {
			try {
				
				//
				// se a fila de fotos estiver vazia, faz mais fotos (chama a API de fotos de rovers da nasa)
				//
				if (photosQueue.isEmpty()) {
					
					int martianRotation = new Random().nextInt(2000);
					
					photosQueue = camerasService.getPhotosQueryByMartianRotation(martianRotation);
					
					//
					// se a chamada a api nao retornou nenhuma foto, chamada recursivamente 
					//
					while (photosQueue.isEmpty()) {
						takePhoto();
					}
					
				}
			} catch (SerializePhotosException e) {
				serializedPhotoToEarth = e.getMessage();
				return;
			}
			
			serializedPhotoToEarth = photosQueue.poll().getUrl();
		}
		
	}
	
}
