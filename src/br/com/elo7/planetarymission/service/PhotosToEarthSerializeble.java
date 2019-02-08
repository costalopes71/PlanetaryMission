package br.com.elo7.planetarymission.service;

import java.util.Queue;

import br.com.elo7.planetarymission.exceptions.SerializePhotosException;
import br.com.elo7.planetarymission.service.response.ProbePhotos;

interface PhotosToEarthSerializeble {

	Queue<ProbePhotos> getPhotosQueryByMartianRotation(int martianRotation) throws SerializePhotosException;
	
}
