package br.com.elo7.planetarymission.model.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import br.com.elo7.planetarymission.model.test.testcases.PlanetTestCase;
import br.com.elo7.planetarymission.model.test.testcases.ProbeTestCase;

@RunWith(DescribedSuiteRunner.class)
@SuiteDescription("Roteiro de testes - Planetary Mission")
@SuiteClasses({ 
	ProbeTestCase.class,
	PlanetTestCase.class
})
public final class PlanetaryMissionTestSuite {

	
}
