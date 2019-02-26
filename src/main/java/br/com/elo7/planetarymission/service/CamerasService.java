package br.com.elo7.planetarymission.service;

import java.util.Queue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import br.com.elo7.planetarymission.exceptions.SerializePhotosException;
import br.com.elo7.planetarymission.service.response.Photos;

public abstract class CamerasService {

	protected Client client = ClientBuilder.newClient();
	protected WebTarget webTarget;
	protected Response response;
	
	public abstract Queue<Photos> getPhotosQueryByMartianRotation(int martianRotation) throws SerializePhotosException;
	
}
