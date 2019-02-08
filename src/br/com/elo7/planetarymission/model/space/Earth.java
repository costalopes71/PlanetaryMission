package br.com.elo7.planetarymission.model.space;

import java.util.HashSet;
import java.util.Set;

import br.com.elo7.planetarymission.model.equipaments.PlanetaryEquipment;

public class Earth extends Planet {

	Earth() {
		super(30, 30, "Earth");
	}

	public abstract static class Nasa {
		
		private static Set<PlanetaryEquipment> equipments = new HashSet<>();
		private static Set<PlanetaryEquipment> equipmentsInSpace = new HashSet<>();
		
		public static boolean registerEquipment(PlanetaryEquipment equipment) {
			return equipments.add(equipment);
		}
		
		public static boolean launchEquipment(PlanetaryEquipment equipment) {
			
			if (equipment.isInOrbit() || equipment.isLanded() || equipments.contains(equipment) == false) {
				return false;
			}
			
			if (equipmentsInSpace.add(equipment)) {
				equipment.setInOrbit(true);
				return true;
			}
			
			return false;
		}

		public static boolean isEquipmentInOrbit(PlanetaryEquipment planetaryEquipment) {
			return equipmentsInSpace.contains(planetaryEquipment);
		}

		public static void removeEquipmentFromOrbit(PlanetaryEquipment planetaryEquipment) {
			equipmentsInSpace.remove(planetaryEquipment);
		}
		
		public static PlanetaryEquipment comunicateWithEquipment(final int equipmentId) {
			PlanetaryEquipment equipment = equipments.stream().filter(eqp -> eqp.getEquipmentId() == equipmentId).findAny().orElse(null);
			return equipment;
		}
		
	}
	
}
