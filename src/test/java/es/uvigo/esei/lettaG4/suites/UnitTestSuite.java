package es.uvigo.esei.lettaG4.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import es.uvigo.esei.lettaG4.entities.EventUnitTest;
import es.uvigo.esei.lettaG4.entities.RelationshipUnitTest;
import es.uvigo.esei.lettaG4.entities.UserUnitTest;

@SuiteClasses({
	UserUnitTest.class,
	EventUnitTest.class	,
	RelationshipUnitTest.class
})

@RunWith(Suite.class)
public class UnitTestSuite {	
	
}
