package br.com.elo7.planetarymission.service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public abstract class EquipmentCameras implements PhotosToEarthSerializeble {

	protected Client client = Client.create();
	protected WebResource resource;
	protected ClientResponse response;
	
}
