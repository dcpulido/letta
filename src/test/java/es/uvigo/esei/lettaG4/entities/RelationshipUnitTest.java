package es.uvigo.esei.lettaG4.entities;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class RelationshipUnitTest {
	@Test
	public void testRelationShipIntInt() {
		final int idEvent = 1;
		final int idUser = 1;
		
		final Relationship rel = new Relationship(idEvent, idUser);
		
		assertThat(rel.getIdUser(), is(equalTo(idUser)));
		assertThat(rel.getidEvent(), is(equalTo(idEvent)));
		
		
	}
 
	
}
