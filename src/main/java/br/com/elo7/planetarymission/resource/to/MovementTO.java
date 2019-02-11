package br.com.elo7.planetarymission.resource.to;

import java.io.Serializable;

public class MovementTO implements Serializable {

	private static final long serialVersionUID = -5807742172283419253L;
	
	private int equipmentId;
	private String movement;

	public String getMovement() {
		return movement;
	}

	public void setMovement(String movement) {
		this.movement = movement;
	}

	public int getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}

}
