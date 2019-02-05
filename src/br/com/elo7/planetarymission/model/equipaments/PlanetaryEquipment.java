package br.com.elo7.planetarymission.model.equipaments;

import java.io.Serializable;

import br.com.elo7.planetarymission.exceptions.LandingException;
import br.com.elo7.planetarymission.exceptions.MovementException;
import br.com.elo7.planetarymission.model.CardinalPoint;
import br.com.elo7.planetarymission.model.Movement;
import br.com.elo7.planetarymission.model.planets.Planet;

public abstract class PlanetaryEquipment implements Directional, Serializable {

	private static final long serialVersionUID = 3196043454664309755L;
	
	private CardinalPoint currentDirection = CardinalPoint.NORTH;
	private Planet planet;
	private boolean isLanded;
	private final int equipmentId;
	private int positionX;
	private int positionY;

	//
	// contructors
	//
	protected PlanetaryEquipment(final int equipmentId) {
		this.equipmentId = equipmentId; 
	}
	
	protected PlanetaryEquipment(final int equipmentId, final CardinalPoint landingCardinalDirection) {
		this.currentDirection = landingCardinalDirection;
		this.equipmentId = equipmentId;
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

	//
	// methods
	//
	public void land(Planet planet, int positionX, int positionY) throws LandingException {
		
		if (!isLanded) {
			isLanded = true;
			this.planet = planet;
		} else
			throw new LandingException(this.getClass().getSimpleName() + " [" + equipmentId + "] is already landed!");
		
		if (planet.isPositionOccupied(positionX, positionY) == false) {
			try {
				planet.occupyPosition(positionX, positionY);
				this.positionX = positionX;
				this.positionY = positionY;
			} catch (MovementException e) {
				e.printStackTrace();
				throw new LandingException(e.getMessage(), e);
			}
		} else {
			throw new LandingException();
		}
		
	}
	
	@Override
	public void travelRoute(Movement... movements) throws MovementException {

		if (!isLanded)
			throw new MovementException("Equipment is not landed, cannot move!");
		
		for (Movement movement : movements) {
			
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
				throw new IllegalArgumentException("Invalid movement!");
			}
			
		}
		
	}
	
	private final void turnLeft() {

		switch (currentDirection) {
			case NORTH:
				currentDirection = CardinalPoint.EAST;
				break;
			case EAST:
				currentDirection = CardinalPoint.SOUTH;
				break;
			case SOUTH:
				currentDirection = CardinalPoint.WEST;
				break;
			case WEST:
				currentDirection = CardinalPoint.NORTH;
				break;
			default:
				throw new IllegalArgumentException("Invalid direction!");
		}

	}

	private final void turnRight() {

		switch (currentDirection) {
			case NORTH:
				currentDirection = CardinalPoint.WEST;
				break;
			case WEST:
				currentDirection = CardinalPoint.SOUTH;
				break;
			case SOUTH:
				currentDirection = CardinalPoint.EAST;
				break;
			case EAST:
				currentDirection = CardinalPoint.NORTH;
				break;
			default:
				throw new IllegalArgumentException("Invalid direction!");
		}
		
	}

	private final void moveForward() {

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
