package br.com.elo7.planetarymission.model.equipaments.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.elo7.planetarymission.exceptions.SerializePhotosException;
import br.com.elo7.planetarymission.model.equipaments.PlanetaryEquipment;
import br.com.elo7.planetarymission.service.impl.ProbeCameras;
import br.com.elo7.planetarymission.service.response.ProbePhotos;

public final class Probe extends PlanetaryEquipment {
	
	private static final long serialVersionUID = 1473730384674867126L;
	
	private List<ProbePhotos> probePhotos = new ArrayList<>();
	private ProbeCameras cameras = new ProbeCameras();
	private int i;
	
	//
	// constructors
	//
	public Probe(final String name) {
		super(name);
	}
	
	@Override
	protected void takePhoto() {
		
		if (isLanded()) {
			try {
				if (probePhotos.size() == 0) {
					int martianRotation = new Random().nextInt(2000);
					
					probePhotos = cameras.getPhotosQueryByMartianRotation(martianRotation);
					
					while (probePhotos.size() == 0) {
						takePhoto();
					}
					
				}
			} catch (SerializePhotosException e) {
				serializedPhotoToEarth = e.getMessage();
				return;
			}
			
			if (i == probePhotos.size() - 1)
				i = 0;
				
			serializedPhotoToEarth = probePhotos.get(i++).getUrl();
		}
		
	}
	
	@Override
	public String getSerializedPhotoToEarth() {
		return isLanded() ? serializedPhotoToEarth : "only landed equipments can take photos!";
	}
	
}
