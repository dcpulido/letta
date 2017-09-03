package es.uvigo.esei.lettaG4.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import es.uvigo.esei.lettaG4.rest.eventResourceUnitTest;
import es.uvigo.esei.lettaG4.rest.userResourceUnitTest;

@SuiteClasses({ 	
	userResourceUnitTest.class,
	eventResourceUnitTest.class
})

@RunWith(Suite.class)
public class IntegrationTestSuite {
 
}
