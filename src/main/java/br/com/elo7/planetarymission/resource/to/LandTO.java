package br.com.elo7.planetarymission.resource.to;

import java.io.Serializable;

public class LandTO implements Serializable {

	private static final long serialVersionUID = 2663949368534130235L;
	
	private int equipmentId;
	private int planetId;
	private int positionX;
	private int positionY;

	public int getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}

	public int getPlanetId() {
		return planetId;
	}

	public void setPlanetId(int planetId) {
		this.planetId = planetId;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

}