package es.uvigo.esei.lettaG4.dataset;

import static java.util.Arrays.binarySearch;
import static java.util.Arrays.stream;

import java.util.Arrays;
import java.util.function.Predicate;

import es.uvigo.esei.lettaG4.entities.User;

public final class UsersDataset {
	private UsersDataset() {}
	
	public static User[] user() {
		return new User[] {
				new User(1, "Diego", "diego@esei.uvigo.es", "diegopass"),
				new User(2, "Juan", "juan@esei.uvigo.es", "juanpass"),
				new User(3, "Pedro", "pedro@esei.uvigo.es", "pedropass"),
				new User(4, "Maria", "maria@esei.uvigo.es", "mariapass"),
				new User(5, "Lucia", "lucia@esei.uvigo.es", "luciapass"),
				new User(6, "Monica", "monica@esei.uvigo.es", "monicapass"),
				new User(7, "Silvia", "silvia@esei.uvigo.es", "silviapass")
		};
	}
	
	public static User[] usersWithout(int ... ids) {
		Arrays.sort(ids);
		
		final Predicate<User> hasValidId = user ->
			binarySearch(ids, user.getId()) < 0;
		
		return stream(user())
			.filter(hasValidId)
		.toArray(User[]::new);
	}
	
	public static User user(int id) {
		return stream(user())
			.filter(user -> user.getId() == id)
			.findAny()
		.orElseThrow(IllegalArgumentException::new);
	}
	
	public static int existentId() {
		return 5;
	}
	
	public static int nonExistentId() {
		return 1234;
	}

	public static User existentUser() {
		return user(existentId());
	}
	
	public static User nonExistentUser() {
		return new User(nonExistentId(), "Aurelio", "Aurelio@esei.uvigo.es","aureliopass");
	}
	
	public static String newName() {
		return "Jonas";
	}
	
	public static String newEmail() {
		return "Jonas@esei.uvigo.es";
	}
	
	public static String newPass() {
		return "jonaspass";
	}	
	
	
	public static User newUser() {
		return new User(user().length + 1, newName(), newEmail(), newPass());
	}
}
