package es.uvigo.esei.lettaG4.entities;

import static java.util.Objects.requireNonNull;
import java.lang.IllegalArgumentException;


/**
 * An entity that represents a user.
 * 
*/
public class User {
	private int id;
	private String name;
	private String email;
	private String pass;

	// Constructor needed for the JSON conversion
	User() {
	}

	/**
	 *
	 * @param id
	 *            identifier of the User.
	 * @param name
	 *            name of the User.
	 * @param email
	 *            email of the User.
	 */

	public User(int id, String name, String email, String pass) {
		this.id = id;
		this.setName(name);
		this.setEmail(email);
		this.setPass(pass);
		
	}

	/**
	 * Returns the identifier of the User.
	 * 
	 * @return the identifier of the User.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns the name of the User.
	 * 
	 * @return the name of the User.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of this User.
	 * 
	 * @param name
	 *            the new name of the User.
	 * @throws IllegalArgumentException
	 *             if the {@code name} length is not between 0 and 140.
	 */
	public void setName(String name) {
		if(name.length()<50 && name.length()>0){
			this.name = requireNonNull(name,"Name can't be null");
		}
		else{
			throw new IllegalArgumentException("Illegal length of name");
		}
	}

	/**
	 * Returns the email of the User.
	 * 
	 * @return the email of the User.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set the email of this User.
	 * 
	 * @param email
	 *            the new email of the User.
	 * @throws IllegalArgumentException
	 *             if the {@code email} length is not between 0 and 500.
	 */
	public void setEmail(String email) {
		if(email.length()<100 && email.length()>0){
			this.email = requireNonNull(email, "email can't be null");
		}
		else{
			throw new IllegalArgumentException();
		}
	}

	public String getPass() {
		return pass;
	}

	/**
	 * Set the pass of this User.
	 * 
	 * @param pass
	 *            the new pass of the User.
	 * @throws  IllegalArgumentException
	 *             if the {@code pass} length is longer than 140.
	 */
	public void setPass(String pass) {
		if(pass.length()<140){
			this.pass = requireNonNull(pass, "Pass can't be null");		
		}
		else{
			throw new IllegalArgumentException();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
}