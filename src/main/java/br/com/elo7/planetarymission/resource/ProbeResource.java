package br.com.elo7.planetarymission.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import br.com.elo7.planetarymission.bo.ProbeBO;
import br.com.elo7.planetarymission.exceptions.LandingException;
import br.com.elo7.planetarymission.exceptions.MovementException;
import br.com.elo7.planetarymission.exceptions.RegistrationException;
import br.com.elo7.planetarymission.model.directions.Movement;
import br.com.elo7.planetarymission.model.equipaments.impl.Probe;
import br.com.elo7.planetarymission.resource.to.LandTO;
import br.com.elo7.planetarymission.resource.to.MovementTO;

@Path("/probe")
public class ProbeResource {

	private ProbeBO probeBO = new ProbeBO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Probe> list() {
		return probeBO.list();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response register(String name, @Context UriInfo uriInfo) {
		
		Probe probe = new Probe(name);
		probeBO.registerProbe(probe);
		
		UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Integer.toString(probe.getEquipmentId()));
		return Response.created(builder.build()).build();
	}

	@POST
	@Path("/land")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response land(LandTO probeLand, @Context UriInfo uri) {
		
		try {
			probeBO.land(probeLand.getEquipmentId(), probeLand.getPlanetId(), probeLand.getPositionX(), probeLand.getPositionY());
		} catch (LandingException e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		
		return Response.ok("Equipment landed successfully!").build();
	}
	
	@POST
	@Path("/move")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response move(MovementTO request, @Context UriInfo uri) {
		
		
		List<Movement> movs;
		Probe probe;
		try {
			probe = probeBO.find(request.getEquipmentId());
			movs = parseMovements(request.getMovement());
			probe.travelRoute(movs);
		} catch (RegistrationException | MovementException e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build(); 
		}
		
		return Response.ok("Route sucessfully travelled!").build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar(@PathParam("id") int equipmentId) {
		
		try {
			return Response.accepted(probeBO.find(equipmentId)).build();
		} catch (RegistrationException e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		
	}

	private List<Movement> parseMovements(String movement) throws MovementException {
		
		List<Movement> movementsList = new ArrayList<>();
		String[] tokens = movement.split("\\|");
		
		for (String move : tokens) {
			
			switch (move.toUpperCase()) {
				
				case "L":
					movementsList.add(Movement.LEFT);
					break;
				case "R":
					movementsList.add(Movement.RIGHT);
					break;
				case "M":
					movementsList.add(Movement.FORWARD);
					break;
				default:
					throw new MovementException("Movement [" + move + "] is not valid. Only F, R or M are valid movements!");
					
			}
			
		}
		
		return movementsList;
	}
	
	
	
}
