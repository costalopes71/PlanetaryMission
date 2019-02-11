package br.com.elo7.planetarymission.service;

import java.util.Queue;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.elo7.planetarymission.exceptions.SerializePhotosException;
import br.com.elo7.planetarymission.service.response.Photos;

public abstract class CamerasService {

	protected Client client = Client.create();
	protected WebResource resource;
	protected ClientResponse response;
	
	public abstract Queue<Photos> getPhotosQueryByMartianRotation(int martianRotation) throws SerializePhotosException;
	
}
