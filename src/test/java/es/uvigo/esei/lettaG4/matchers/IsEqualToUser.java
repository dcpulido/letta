package es.uvigo.esei.lettaG4.matchers;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;

import es.uvigo.esei.lettaG4.entities.User;

public class IsEqualToUser extends IsEqualToEntity<User> {
	public IsEqualToUser(User entity) {
		super(entity);
	}

	@Override
	protected boolean matchesSafely(User actual) {
		this.clearDescribeTo();
		
		if (actual == null) {
			this.addTemplatedDescription("actual", expected.toString());
			return false;
		} else {
			return checkAttribute("id", User::getId, actual)
				&& checkAttribute("name", User::getName, actual)
				&& checkAttribute("email", User::getEmail, actual)
				&& checkAttribute("pass", User::getPass, actual);
			
		}
	}

	/**
	 * Factory method that creates a new {@link IsEqualToEntity} matcher with
	 * the provided {@link User} as the expected value.
	 * 
	 * @param person the expected person.
	 * @return a new {@link IsEqualToEntity} matcher with the provided
	 * {@link User} as the expected value.
	 */
	@Factory
	public static IsEqualToUser equalsToUser(User person) {
		return new IsEqualToUser(person);
	}
	
	/**
	 * Factory method that returns a new {@link Matcher} that includes several
	 * {@link IsEqualToUser} matchers, each one using an {@link User} of the
	 * provided ones as the expected value.
	 * 
	 * @param persons the persons to be used as the expected values.
	 * @return a new {@link Matcher} that includes several
	 * {@link IsEqualToUser} matchers, each one using an {@link User} of the
	 * provided ones as the expected value.
	 * @see IsEqualToEntity#containsEntityInAnyOrder(java.util.function.Function, Object...)
	 */
	@Factory
	public static Matcher<Iterable<? extends User>> containsPeopleInAnyOrder(User ... persons) {
		return containsEntityInAnyOrder(IsEqualToUser::equalsToUser, persons);
	}

}
