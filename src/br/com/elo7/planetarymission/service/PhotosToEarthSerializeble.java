package br.com.elo7.planetarymission.service;

import java.util.List;

import br.com.elo7.planetarymission.exceptions.SerializePhotosException;
import br.com.elo7.planetarymission.service.response.ProbePhotos;

interface PhotosToEarthSerializeble {

	List<ProbePhotos> getPhotosQueryByMartianRotation(int martianRotation) throws SerializePhotosException;
	
}
