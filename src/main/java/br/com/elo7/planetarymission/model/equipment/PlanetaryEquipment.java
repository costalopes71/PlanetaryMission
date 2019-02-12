package br.com.elo7.planetarymission.model.equipment;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

import br.com.elo7.planetarymission.exceptions.LandingException;
import br.com.elo7.planetarymission.exceptions.MovementException;
import br.com.elo7.planetarymission.model.directions.CardinalPoint;
import br.com.elo7.planetarymission.model.directions.Movement;
import br.com.elo7.planetarymission.model.universe.Planet;

public abstract class PlanetaryEquipment implements Directional, Serializable {
	
	//
	// class attributes
	//
	private static final long serialVersionUID = -3610425362812275159L;
	private static AtomicInteger autoincremetedId = new AtomicInteger(187236235);
	
	//
	// instance attributes
	//
	private CardinalPoint currentDirection = CardinalPoint.NORTH;
	private Planet planet;
	private boolean landed;
	private int positionX;
	private int positionY;
	private String name;
	private final LocalDateTime creationDate;
	private final int equipmentId;
	
	//
	// constructors
	//
	public PlanetaryEquipment() {
		equipmentId = autoincremetedId.getAndIncrement();
		creationDate = LocalDateTime.now();
	}
	
	protected PlanetaryEquipment(final String name) {
		this();
		this.name = name;
	}
	
	//
	// getters & setters
	//
	
	public CardinalPoint getCurrentDirection() {
		return currentDirection;
	}
	
	public int getEquipmentId() {
		return equipmentId;
	}

	public int getPositionX() {
		return positionX;
	}
	
	public int getPositionY() {
		return positionY;
	}
	
	public boolean isLanded() {
		return landed;
	}
	
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	
	public String getName() {
		return name;
	}
	
	//
	// methods
	//

	public void land(Planet planet, int positionX, int positionY) throws LandingException {
		
		try {
			planet.occupySurfacePosition(positionX, positionY);
			this.positionX = positionX;
			this.positionY = positionY;
		} catch (MovementException e) {
			throw new LandingException(e);
		}
		
		landed = true;
		this.planet = planet;
		
	}
	
	@Override
	public void travelRoute(Movement movements) throws MovementException {
		travelRoute(Arrays.asList(movements));
	}
	
	@Override
	public void travelRoute(Collection<Movement> movements) throws MovementException {
		
		CardinalPoint rollbackDirection = currentDirection;
		
		int roolbackX = this.positionX;
		int roolbackY = this.positionY;
		
		for (Movement movement : movements) {
			
			try {
				switch (movement) {
					case RIGHT:
						turnRight();
						break;
					case LEFT:
						turnLeft();
						break;
					case FORWARD:
						moveForward();
						break;
					default:
						throw new MovementException();
					}
				
			} catch (MovementException e) {
				rollbackPositions(rollbackDirection, roolbackX, roolbackY);
				throw new MovementException(e);
			}
			
		}
		
		//
		// se a rota for validada, ocupar a posicao na superficie
		//
		try {
			if (movements.contains(Movement.FORWARD)) {
				planet.occupySurfacePosition(positionX, positionY);
				
				//
				// se a sonda moveu pra frente, desocupar a posicao de pouso
				//
				planet.clearOutSurfacePosition(roolbackX, roolbackY);
				
			}
		} catch (MovementException e) {
			rollbackPositions(rollbackDirection, roolbackX, roolbackY);
			throw new MovementException(e);
		}
		
	}

	private void rollbackPositions(CardinalPoint rollbackDirection, int roolbackX, int roolbackY) {
		currentDirection = rollbackDirection;
		positionX = roolbackX;
		positionY = roolbackY;
	}
	
	private final void turnLeft() {

		switch (currentDirection) {
			case NORTH:
				currentDirection = CardinalPoint.WEST;
				break;
			case EAST:
				currentDirection = CardinalPoint.NORTH;
				break;
			case SOUTH:
				currentDirection = CardinalPoint.EAST;
				break;
			case WEST:
				currentDirection = CardinalPoint.SOUTH;
				break;
			default:
				throw new IllegalArgumentException("Invalid direction!");
		}

	}

	private final void turnRight() {

		switch (currentDirection) {
			case NORTH:
				currentDirection = CardinalPoint.EAST;
				break;
			case WEST:
				currentDirection = CardinalPoint.NORTH;
				break;
			case SOUTH:
				currentDirection = CardinalPoint.WEST;
				break;
			case EAST:
				currentDirection = CardinalPoint.SOUTH;
				break;
			default:
				throw new IllegalArgumentException("Invalid direction!");
		}
		
	}

	private final void moveForward() throws MovementException {
		
		switch (currentDirection) {
			case NORTH:
				planet.isSurfacePositionOccupied(positionX, --positionY);
				break;
			case EAST:
				planet.isSurfacePositionOccupied(++positionX, positionY);
				break;
			case SOUTH:
				planet.isSurfacePositionOccupied(positionX, ++positionY);
				break;
			case WEST:
				planet.isSurfacePositionOccupied(--positionX, positionY);
				break;
			default:
				throw new MovementException("Invalid movement!");
		}
		
	}
	
	//
	// equals & hash code
	//
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + equipmentId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlanetaryEquipment other = (PlanetaryEquipment) obj;
		if (equipmentId != other.equipmentId)
			return false;
		return true;
	}

}
