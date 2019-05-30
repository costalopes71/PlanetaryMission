package br.com.elo7.planetarymission.model.universe;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.elo7.planetarymission.exceptions.MovementException;
import br.com.elo7.planetarymission.model.universe.Planet;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlanetTestCase {

	private Planet planet;
	
	@Before
	public void setUp() throws Exception {
		planet = new Planet(5, 5, "Jupiter");
	}


	@Test
	public void test01PositionIsNotOccupied() {
		
		try {
			assertTrue(planet.isSurfacePositionOccupied(0, 0) == false);
		} catch (MovementException e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	
	}

	@Test
	public void test02PositionIsOccupied() {
		
		try {
			planet.occupySurfacePosition(0, 0);
			assertTrue(planet.isSurfacePositionOccupied(0, 0));
		} catch (MovementException e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
		
	}
	
	@Test(expected=MovementException.class)
	public void test03IsPositionOccupiedOutOfPlanetSurface() throws MovementException {
		planet.isSurfacePositionOccupied(1000, 1000);
		fail("Position is out of planet surface!");
	}

	@Test
	public void test04OccupyPlanetSurfacePosition() {
		
		try {
			planet.occupySurfacePosition(0, 0);
		} catch (MovementException e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
		
	}
	
	@Test(expected=MovementException.class)
	public void test05OccupyPositionAlreadyOccupied() throws MovementException {
		planet.occupySurfacePosition(0, 0);
		planet.occupySurfacePosition(0, 0);
		fail("Surface position was occupied twice!");
	}
	
	@Test
	public void test06ClearSurfacePosition() {
		
		try {
			planet.occupySurfacePosition(0, 0);
		} catch (MovementException e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
		
		planet.clearOutSurfacePosition(0, 0);
		
		boolean occupied = true;
		try {
			occupied = planet.isSurfacePositionOccupied(0, 0);
		} catch (MovementException e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
		
		assertTrue(occupied == false);
		
	}
	
}
