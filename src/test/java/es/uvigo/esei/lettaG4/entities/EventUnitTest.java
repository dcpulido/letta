package es.uvigo.esei.lettaG4.entities;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import es.uvigo.esei.lettaG4.dataset.EventsDataset;
import es.uvigo.esei.lettaG4.dataset.UsersDataset;

public class EventUnitTest {
	@Test
	public void testEventIntStringStringStringTimestamp() {
		final int id = 1;
		final String name = "John";
		final String description = "Doe";
		final String place = "Cafe \" O Cafe\" ";
		final Timestamp date = new Timestamp(1459852342);
		
		
		final Event event = new Event(id, name, description, place, date );
		
		assertThat(event.getId(), is(equalTo(id)));
		assertThat(event.getName(), is(equalTo(name)));
		assertThat(event.getDescription(), is(equalTo(description)));
		assertThat(event.getPlace(), is(equalTo(place)));
		assertThat(event.getTimestamp(), is(equalTo(date)));
		
	}

	@Test(expected = NullPointerException.class)
	public void testEventIntStringStringNullName() {
		new Event(1, null, "Buen evento");
	}
	
	@Test(expected = NullPointerException.class)
	public void testEventIntStringStringNullDescription() {
		new Event(1, "Reunión juego de tronos", null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testEventIntStringStringStringTimestampNullName() {
		final Timestamp date = new Timestamp(1459852342);
		new Event(1, null, "buen evento", "bar manolo", date );
	}
	
	@Test(expected = NullPointerException.class)
	public void testEventIntStringStringStringTimestampNullDescription() {
		final Timestamp date = new Timestamp(1459852342);
		new Event(1, "evento", null, "bar manolo", date );
	}


	@Test
	public void testSetName() {
		final int id = 1;
		final String description = "NombreOriginal";
		
		final Event event = new Event(id, "NombreOriginal", description);
		event.setName("NombreCambiado");
		
		assertThat(event.getId(), is(equalTo(id)));
		assertThat(event.getName(), is(equalTo("NombreCambiado")));
		assertThat(event.getDescription(), is(equalTo(description)));
	}

	@Test(expected = NullPointerException.class)
	public void testSetNullName() {
		final Event event = new Event(1, "NombreOriginal", "Buen evento");
		
		event.setName(null);
	}

	@Test
	public void testSetDescription() {
		final int id = 1;
		final String name = "El Evento";
		
		final Event event = new Event(id, name, "DescripcionOriginal");
		event.setDescription("DescripcionCambiada");
		
		assertThat(event.getId(), is(equalTo(id)));
		assertThat(event.getName(), is(equalTo(name)));
		assertThat(event.getDescription(), is(equalTo("DescripcionCambiada")));
	}

	@Test(expected = NullPointerException.class)
	public void testSetNullDescription() {
		final Event event = new Event(1, "El Evento", "BuenEvento");
		
		event.setDescription(null);
	}

	@Test
	public void testSetPlace(){
		
		final int id = 1;
		final String name = "El Evento";
		final String description = "Buen Evento";		
		final Timestamp date = new Timestamp(1459852342);
		
		final Event event = new Event(id, name , description, "bar manolo", date );
		event.setPlace("Otro Bar");
		
		assertThat(event.getId(), is(equalTo(id)));
		assertThat(event.getName(), is(equalTo(name)));
		assertThat(event.getDescription(), is(equalTo(description)));
		assertThat(event.getPlace(), is(equalTo("Otro Bar")));
		assertThat(event.getTimestamp(), is(equalTo(date)));		
		
	}
	
	@Test
	public void testSetTimestamp(){
		
		final int id = 1;
		final String name = "El Evento";
		final String description = "Buen Evento";		
		final String place = "bar manolo";		
		
		final Event event = new Event(id, name , description, place, new Timestamp(1459852342) );
		event.setTimestamp(new Timestamp(1459893453));
		
		assertThat(event.getId(), is(equalTo(id)));
		assertThat(event.getName(), is(equalTo(name)));
		assertThat(event.getDescription(), is(equalTo(description)));
		assertThat(event.getPlace(), is(equalTo(place)));
		assertThat(event.getTimestamp(), is(equalTo(new Timestamp(1459893453))));		
		
	}
	
	
	
	@Test
	public void testEqualsObject() {
		final Event eventA = new Event(1, "Name A", "Description A");
		final Event eventB = new Event(1, "Name B", "Description B");
		
		assertTrue(eventA.equals(eventB));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNameLenght() {
		String name=giveStringLenght(150);
		new Event(1, name, "Buen evento");
	}
	@Test(expected = IllegalArgumentException.class)
	public void testDescriptionLenght() {
		String des=giveStringLenght(600);
		new Event(1, "name", des);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testPlaceLenght() {
		String des=giveStringLenght(100);
		Event ev=new Event(1, "name", des);
		ev.setPlace(giveStringLenght(600));
	}

	public String giveStringLenght(int i){
		String toret="";
		for(int k=0;k<i;k++){
			toret+="i";
		} 
		return toret;
	}
	
	@Test
	public void testEventIntUserUserlistStringStringStringTimestamp(){
		
		final int id = 1;
		final String name = "El Evento";
		final String description = "Buen Evento";		
		final String place = "bar manolo";
		final Timestamp date = new Timestamp(1459852342);
		final User creador = UsersDataset.newUser();
		final List<User> users = Arrays.asList(UsersDataset.user());
		
		
		final Event event = new Event(id, creador, users, name , description, place, date );
		
		assertThat(event.getId(), is(equalTo(id)));
		assertThat(event.getCreador(), is(equalTo(creador)));
		assertThat(event.getAsistentes(), is(equalTo(users)));	
		assertThat(event.getName(), is(equalTo(name)));
		assertThat(event.getDescription(), is(equalTo(description)));
		assertThat(event.getPlace(), is(equalTo(place)));
		assertThat(event.getTimestamp(), is(equalTo(date)));		
		
	}
	
	@Test
	public void testSetCreador(){
		
		final int id = 1;
		final String name = "El Evento";
		final String description = "Buen Evento";		
		final String place = "bar manolo";
		final Timestamp date = new Timestamp(1459852342);		
		final List<User> users = Arrays.asList(UsersDataset.user());
		
		
		final Event event = new Event(id, users.get(1), users, name , description, place, date );
		event.setCreador(users.get(2));
		
		assertThat(event.getId(), is(equalTo(id)));
		assertThat(event.getCreador(), is(equalTo(users.get(2))));
		assertThat(event.getAsistentes(), is(equalTo(users)));		
		assertThat(event.getName(), is(equalTo(name)));
		assertThat(event.getDescription(), is(equalTo(description)));
		assertThat(event.getPlace(), is(equalTo(place)));
		assertThat(event.getTimestamp(), is(equalTo(date)));		
		
	}
	
	@Test
	public void testSetAsistentes(){
		
		final int id = 1;
		final String name = "El Evento";
		final String description = "Buen Evento";		
		final String place = "bar manolo";
		final Timestamp date = new Timestamp(1459852342);
		final User creador = UsersDataset.newUser();
		
		final List<User> users = Arrays.asList(UsersDataset.user());
		final List<User> newUsers = new LinkedList<User>(Arrays.asList(UsersDataset.user()));
		newUsers.add(UsersDataset.newUser());
		
		final Event event = new Event(id, creador, users, name , description, place, date );
		event.setAsistentes(newUsers);
		
		assertThat(event.getId(), is(equalTo(id)));
		assertThat(event.getCreador(), is(equalTo(creador)));
		assertThat(event.getAsistentes(), is(equalTo(newUsers)));
		assertThat(event.getName(), is(equalTo(name)));
		assertThat(event.getDescription(), is(equalTo(description)));
		assertThat(event.getPlace(), is(equalTo(place)));
		assertThat(event.getTimestamp(), is(equalTo(date)));		
		
	}
	
	@Test
	public void testEventIntUserUserlistStringStringStringStringTimestampString(){
		
		final int id = 1;
		final String name = "El Evento";
		final String description = "Buen Evento";		
		final String categoria = "Deportes";
		final String place = "bar manolo";
		final Timestamp date = new Timestamp(1459852342);
		final User creador = UsersDataset.newUser();
		final List<User> users = Arrays.asList(UsersDataset.user());
		final String foto = "imgur.com/foto";
		
		
		final Event event = new Event(id, creador, users, name , description, categoria, place, date, foto );
		
		assertThat(event.getId(), is(equalTo(id)));
		assertThat(event.getCreador(), is(equalTo(creador)));
		assertThat(event.getAsistentes(), is(equalTo(users)));	
		assertThat(event.getName(), is(equalTo(name)));
		assertThat(event.getDescription(), is(equalTo(description)));
		assertThat(event.getCategoria(), is(equalTo(categoria)));	
		assertThat(event.getPlace(), is(equalTo(place)));
		assertThat(event.getTimestamp(), is(equalTo(date)));
		assertThat(event.getFoto(), is(equalTo(foto)));

		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCategoriaInvalida() {
	
		String categoria="falsa";
		
		final Event event = new Event(1, UsersDataset.existentUser(),Arrays.asList(UsersDataset.user()), EventsDataset.newName() ,
				EventsDataset.newDescription(), categoria, EventsDataset.newPlace(), EventsDataset.newDate(), "imgur.com/photo" );
	}
	
	
	

	
	

}
