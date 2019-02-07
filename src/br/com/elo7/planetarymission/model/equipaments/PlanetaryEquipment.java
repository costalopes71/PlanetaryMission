package br.com.elo7.planetarymission.model.equipaments;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

import br.com.elo7.planetarymission.exceptions.LandingException;
import br.com.elo7.planetarymission.exceptions.MovementException;
import br.com.elo7.planetarymission.model.CardinalPoint;
import br.com.elo7.planetarymission.model.Movement;
import br.com.elo7.planetarymission.model.space.Earth.Nasa;
import br.com.elo7.planetarymission.model.space.Planet;

public abstract class PlanetaryEquipment implements Directional, Serializable {
	
	//
	// class attributes
	//
	private static final long serialVersionUID = 3196043454664309755L;
	private static AtomicInteger autoincremetedId = new AtomicInteger(187236235);
	
	//
	// instace attributes
	//
	private CardinalPoint currentDirection = CardinalPoint.NORTH;
	private Planet planet;
	private LocalDateTime creationDate;
	private boolean landed;
	private boolean inOrbit;
	private int positionX;
	private int positionY;
	private final String name;
	private final int equipmentId;

	//
	// contructors
	//
	
	protected PlanetaryEquipment(final String name) {
		this.name = name;
		this.equipmentId = autoincremetedId.getAndIncrement();
		this.creationDate = LocalDateTime.now();
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

	public boolean isInOrbit() {
		return inOrbit;
	}

	public void setInOrbit(boolean inOrbit) {
		if (Nasa.isEquipmentInOrbit(this))
			this.inOrbit = inOrbit;
	}

	public String getName() {
		return name;
	}
	
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	
	public boolean isLanded() {
		return landed;
	}
	
	//
	// methods
	//

	public synchronized void land(int planetId, int positionX, int positionY) throws LandingException {
		
		if (Nasa.isEquipmentInOrbit(this) == false)
			throw new LandingException("Cannot land " + this.getClass().getSimpleName() + " [" + equipmentId + "] because it has not been launched yet !");
		
		Planet planet = Planet.getPlanet(planetId);
		
		if (planet == null)
			throw new LandingException("Planet " + planetId + "does not exist! Cannot land!");
		
		if (landed == false) {
			
			try {
				planet.occupySurfacePosition(positionX, positionY);
				this.positionX = positionX;
				this.positionY = positionY;
			} catch (MovementException e) {
				e.printStackTrace();
				throw new LandingException(e);
			}
			
			landed = true;
			this.planet = planet;
			Nasa.removeEquipmentFromOrbit(this);
			
		} else
			throw new LandingException(this.getClass().getSimpleName() + " [" + equipmentId + "] is already landed!");
		
	}
	
	@Override
	public synchronized void travelRoute(Movement... movements) throws MovementException {

		if (!landed)
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
