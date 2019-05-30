package br.com.elo7.planetarymission.model.equipment.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.elo7.planetarymission.bo.ProbeBO;
import br.com.elo7.planetarymission.exceptions.LandingException;
import br.com.elo7.planetarymission.exceptions.MovementException;
import br.com.elo7.planetarymission.exceptions.RegistrationException;
import br.com.elo7.planetarymission.model.directions.CardinalPoint;
import br.com.elo7.planetarymission.model.directions.Movement;
import br.com.elo7.planetarymission.model.equipment.impl.Probe;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProbeTestCase {

	private static ProbeBO probeBO = new ProbeBO();
	private Probe probe1;
	private Probe probe2;
	
	@Before
	public void setUp() throws Exception {
		
		probe1 = new Probe("BR029M Probe");
		probe2 = new Probe("USA9283M Probe");
		probeBO.registerProbe(probe1);
		probeBO.registerProbe(probe2);
		
	}

	@Test
	public void test01RegisterProbe() {
		
		try {
			assertTrue(probeBO.registerProbe(new Probe("New probe")));
		} catch (RegistrationException e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	
	}
	
	@Test(expected=RegistrationException.class)
	public void test02RegisterSameProbeTwice() throws RegistrationException {
		
		probeBO.registerProbe(probe1);
		fail("Same probe was registered twice!");
	
	}
	
	@Test
	public void test03LandProbe() {
		
		try {
			probeBO.land(probe1.getEquipmentId(), 1095, 5, 5);
		} catch (LandingException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

	@Test(expected=LandingException.class)
	public void test04LandSameProbeTwice() throws LandingException {
		
		probeBO.land(probe1.getEquipmentId(), 1003, 6, 6);
		probeBO.land(probe1.getEquipmentId(), 1003, 2, 2);
		fail("Test failed! Landed probe landed again!");
		
	}
	
	@Test(expected=LandingException.class)
	public void test05LandTwoProbesInSamePosition() throws LandingException {
		
		probeBO.land(probe1.getEquipmentId(), 1002, 5, 5);
		probeBO.land(probe2.getEquipmentId(), 1002, 5, 5);
		fail("Test failed! Two probes landed in same planet position!");
		
	}
	
	@Test(expected=LandingException.class)
	public void test06LandProbeOutOfPlanetSurface() throws LandingException {
		
		probeBO.land(probe1.getEquipmentId(), 1001, 20, 20);
		fail("Test failed! Probe landed out of planet surface!");
		
	}
	
	@Test
	public void test07RotateProbeToEast() {
		
		try {
			probeBO.land(probe1.getEquipmentId(), 1010, 5, 5);
			probeBO.move(probe1, Movement.RIGHT);
		} catch (LandingException | MovementException e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
		
		assertEquals(CardinalPoint.EAST, probe1.getCurrentDirection());
		
	}
	
	@Test
	public void test08RotateProbeToWest() {
		
		try {
			probeBO.land(probe1.getEquipmentId(), 1080, 5, 5);
			probeBO.move(probe1, Movement.LEFT);
		} catch (LandingException | MovementException e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
		
		assertEquals(CardinalPoint.WEST, probe1.getCurrentDirection());
		
	}
	
	@Test
	public void test09RotateProbeToSouth() {
		
		try {
			probeBO.land(probe1.getEquipmentId(), 1090, 5, 5);
			probeBO.move(probe1, Movement.LEFT, Movement.LEFT);
		} catch (LandingException | MovementException e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
		
		assertEquals(CardinalPoint.SOUTH, probe1.getCurrentDirection());
		
	}
	
	@Test
	public void test10MoveProbeOnYAxis() {
		
		try {
			probeBO.land(probe1.getEquipmentId(), 1900, 0, 0);
			probeBO.move(probe1, Movement.RIGHT, Movement.RIGHT, Movement.FORWARD, Movement.FORWARD);
		} catch (LandingException | MovementException e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
		
		assertEquals(2, probe1.getPositionY());
	}

	@Test
	public void test11MoveProbeOnXAxis() {
		
		try {
			probeBO.land(probe1.getEquipmentId(), 1911, 0, 0);
			probeBO.move(probe1, Movement.RIGHT, Movement.FORWARD, Movement.FORWARD);
		} catch (LandingException | MovementException e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
		
		assertEquals(2, probe1.getPositionX());
	}
	
	@Test(expected=MovementException.class)
	public void test12CollideProbes() throws MovementException {
		
		try {
			probeBO.land(probe1.getEquipmentId(), 2000, 0, 0);
			probeBO.move(probe1, Movement.RIGHT, Movement.FORWARD, Movement.FORWARD);
			probeBO.land(probe2.getEquipmentId(), 2000, 4, 0);
			probeBO.move(probe2, Movement.LEFT, Movement.FORWARD, Movement.FORWARD);
			fail("Probes did not collide!");
		} catch (LandingException e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
		
	}
	
	@Test
	public void test13ClearLandPositionAfterMovingForward() {
		
		try {
			probeBO.land(probe1.getEquipmentId(), 2040, 0, 0);
			probeBO.move(probe1, Movement.RIGHT, Movement.FORWARD, Movement.FORWARD);
			probeBO.land(probe2.getEquipmentId(), 2040, 0, 0);
		} catch (LandingException | MovementException e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
		
	}
	
	@Test(expected=LandingException.class)
	public void test14DoNotClearLandPositionIfDidNotMoveForward() throws LandingException {
		
		try {
			probeBO.land(probe1.getEquipmentId(), 2040, 0, 0);
			probeBO.move(probe1, Movement.RIGHT);
			probeBO.land(probe2.getEquipmentId(), 2040, 0, 0);
			
			fail("Land position was cleared without moving probe foward!");
			
		} catch (MovementException e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
		
	}
	
	@Test(expected=MovementException.class)
	public void test15MoveProbeBeyondPlanetSurface() throws MovementException {
		
		try {
			probeBO.land(probe1.getEquipmentId(), 1001, 0, 0);
			List<Movement> moves = new ArrayList<>();
			
			moves.add(Movement.RIGHT);
			for (int i = 0; i < 15; i++) {
				moves.add(Movement.FORWARD);
			}
			
			probeBO.move(probe1, moves);
			fail("Probe moved out of planet surface!");
			
		} catch (LandingException e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
		
	}
	
	@Test(expected=LandingException.class)
	public void test16LandInANonExistingPlanet() throws LandingException {
		
		probeBO.land(probe1.getEquipmentId(), 999999, 0, 0);
		fail("Probe landed in a non existing planet!");
	}
	
	@Test(expected=LandingException.class)
	public void test17LandANonRegisteredProbe() throws LandingException {
		Probe probe = new Probe("Not registered probe");
		probeBO.land(probe.getEquipmentId(), 1782, 0, 0);
		fail("A non registered probe was able to landed!");
	}
	
	@Test(expected=MovementException.class)
	public void test18MoveANonLandedProbe() throws MovementException {
		probeBO.move(probe1, Movement.RIGHT);
	}
	
}
