package es.uvigo.esei.lettaG4.entities;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class UserUnitTest {

	@Test
	public void testUserIntStringStringString() {
		final int id = 1;
		final String name = "John";
		final String email = "John90@hotmail.com";
		final String pass = "1234560";
		
		final User user = new User(id, name, email, pass);
		
		assertThat(user.getId(), is(equalTo(id)));
		assertThat(user.getName(), is(equalTo(name)));
		assertThat(user.getEmail(), is(equalTo(email)));
		assertThat(user.getPass(), is(equalTo(pass)));

	}

	@Test(expected = NullPointerException.class)
	public void testUserIntStringStringStringNullName() {
		new User(1, null, "doe@doe.doe", "Doepass");
	}
	
	@Test(expected = NullPointerException.class)
	public void testUserIntStringStringNullEmail() {
		new User(1, "John", null,"jhonpass");
	}

	@Test(expected = NullPointerException.class)
	public void testUserIntStringStringStringNullPass(){
		new User(1,"Jhon", "jhon@jhon.com", null);
	}
	
	@Test
	public void testSetName() {
		final int id = 1;
		final String email = "Doe";
		final String pass = "Doepass";
		
		final User user = new User(id, "John", email, pass);
		user.setName("Juan");
		
		assertThat(user.getId(), is(equalTo(id)));
		assertThat(user.getName(), is(equalTo("Juan")));
		assertThat(user.getEmail(), is(equalTo(email)));
		assertThat(user.getPass(), is(equalTo(pass)));
		
	}

	@Test(expected = NullPointerException.class)
	public void testSetNullName() {
		final User user = new User(1, "John", "Doe@doe.com","doepass");
		user.setName(null);
	}

	@Test
	public void testSetEmail() {
		final int id = 1;
		final String name = "John";
		final String pass= "Jhonpass";
		
		final User user = new User(id, name, "Doe@doe.com",pass);
		user.setEmail("Dolores@email.com");
		
		assertThat(user.getId(), is(equalTo(id)));
		assertThat(user.getName(), is(equalTo(name)));
		assertThat(user.getEmail(), is(equalTo("Dolores@email.com")));
		assertThat(user.getPass(), is(equalTo(pass)));

	}
	
	@Test(expected = NullPointerException.class)
	public void testSetNullEmail() {
		final User user = new User(1, "John", "Doe", "Doepas");		
		user.setEmail(null);
	}
	
	@Test
	public void testSetPass() {
		final int id = 1;
		final String name = "John";
		final String email= "joe@joe.com";
		
		final User user = new User(id, name, email, "joepass");
		user.setPass("Dolorespass");
		
		assertThat(user.getId(), is(equalTo(id)));
		assertThat(user.getName(), is(equalTo(name)));
		assertThat(user.getEmail(), is(equalTo(email)));
		assertThat(user.getPass(), is(equalTo("Dolorespass")));

	}
	
	@Test(expected = NullPointerException.class)
	public void testSetNullPass() {
		final User user = new User(1, "John", "joe@joe.com", "joepass");		
		user.setEmail(null);
	}
	

	@Test
	public void testEqualsObject() {
		final User userA = new User(1, "Name A", "Email A", "Pass A");
		final User userB = new User(1, "Name B", "Email B", "Pass B");
		
		assertTrue(userA.equals(userB));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetIllegalName() {
		final User user = new User(1, "Johnaaaaaaaaaaaaaaaaaaaaaaaaaaardddddddddddoooooooooo", "joe@joe.com", "joepass");		
		user.setEmail(null);
	}
	
}
