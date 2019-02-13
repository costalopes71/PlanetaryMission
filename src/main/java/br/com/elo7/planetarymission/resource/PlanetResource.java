package br.com.elo7.planetarymission.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.elo7.planetarymission.model.universe.Planet;
import br.com.elo7.planetarymission.resource.to.PlanetResponse;

@Path("/planet")
public class PlanetResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PlanetResponse> getMars() {
		
		List<PlanetResponse> response = new ArrayList<>();
		
		//
		// buscando os planetas
		//
		Planet.listPlanets().forEach(p -> {
			response.add(new PlanetResponse(p.getName(), p.getPlanetId()));
		});
		
		return response;
	}
	
}
