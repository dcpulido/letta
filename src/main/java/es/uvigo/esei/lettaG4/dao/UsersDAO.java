package es.uvigo.esei.lettaG4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Timestamp;
import es.uvigo.esei.lettaG4.entities.User;

/**
 * DAO class for the {@link Person} entities.
 * 
 * @author Miguel Reboiro Jato
 *
 */
public class UsersDAO extends DAO {
	private final static Logger LOG = Logger.getLogger(UsersDAO.class.getName());
	
	/**
	 * Returns a person stored persisted in the system.
	 * 
	 * @param id identifier of the person.
	 * @return a person with the provided identifier.
	 * @throws DAOException if an error happens while retrieving the person.
	 * @throws IllegalArgumentException if the provided id does not corresponds
	 * with any persisted person.
	 */
	public User get(int id)
	throws DAOException, IllegalArgumentException {
		try (final Connection conn = this.getConnection()) {
			final String query = "SELECT * FROM users WHERE id=?";
			
			try (final PreparedStatement statement = conn.prepareStatement(query)) {
				statement.setInt(1, id);
				
				try (final ResultSet result = statement.executeQuery()) {
					if (result.next()) {
						return rowToEntity(result);
					} else {
						throw new IllegalArgumentException("Invalid id");
					}
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error getting a User", e);
			throw new DAOException(e);
		}
	}
	
	/**
	 * Returns a list with all the events persisted in the system.
	 * 
	 * @return a list with all the events persisted in the system.
	 * @throws DAOException if an error happens while retrieving the events.
	 */
	public List<User> list() throws DAOException {
		try (final Connection conn = this.getConnection()) {
			final String query = "SELECT * FROM users";
			
			try (final PreparedStatement statement = conn.prepareStatement(query)) {
				try (final ResultSet result = statement.executeQuery()) {
					final List<User> users = new LinkedList<>();
					
					while (result.next()) {
						users.add(rowToEntity(result));
					}
					
					return users;
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error listing users", e);
			throw new DAOException(e);
		}
	}
	
	/**
	 * Persists a new Event in the system. An identifier will be assigned
	 * automatically to the new Event.
	 * 
	 * @param name name of the new Event. Can't be {@code null}.
	 * @param description description of the new Event. Can't be {@code null}.
	 * @return a {@link Event} entity representing the persisted Event.
	 * @throws DAOException if an error happens while persisting the new Event.
	 * @throws IllegalArgumentException if the name or description are {@code null}.
	 */
	public User add(String name, String email,String pass)
	throws DAOException, IllegalArgumentException {
		if (name == null || email == null || pass==null) {
			throw new IllegalArgumentException("name email and pass can't be null");
		}
		
		try (Connection conn = this.getConnection()) {
			final String query = "INSERT INTO Users VALUES(null, ?, ?, ?)";
			
			try (PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, name);
				statement.setString(2, email);
				statement.setString(3, pass);
				
				if (statement.executeUpdate() == 1) {
					try (ResultSet resultKeys = statement.getGeneratedKeys()) {
						if (resultKeys.next()) {
							return new User(resultKeys.getInt(1), name, email,pass);
						} else {
							LOG.log(Level.SEVERE, "Error retrieving inserted id");
							throw new SQLException("Error retrieving inserted id");
						}
					}
				} else {
					LOG.log(Level.SEVERE, "Error inserting value");
					throw new SQLException("Error inserting value");
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error adding a User", e);
			throw new DAOException(e);
		}
	}
	
	/**
	 * Modifies a Event previously persisted in the system. The Event will be
	 * retrieved by the provided id and its current name and surname will be
	 * repassd with the provided.
	 * 
	 * @param Event a {@link Event} entity with the new data.
	 * @throws DAOException if an error happens while modifying the new Event.
	 * @throws IllegalArgumentException if the Event is {@code null}.
	 */
	public void modify(User user)
	throws DAOException, IllegalArgumentException {
		if (user == null) {
			throw new IllegalArgumentException("user can't be null");
		}
		
		try (Connection conn = this.getConnection()) {
			final String query = "UPDATE users SET name=?, email=?, pass=? WHERE id=?";
			
			try (PreparedStatement statement = conn.prepareStatement(query)) {
				statement.setString(1, user.getName());
				statement.setString(2, user.getEmail());
				statement.setString(3, user.getPass());
				statement.setInt(4, user.getId());
				
				if (statement.executeUpdate() != 1) {
					throw new IllegalArgumentException("name and email can't be null");
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error modifying a user", e);
			throw new DAOException();
		}
	}
	
	/**
	 * Removes a persisted event from the system.
	 * 
	 * @param id identifier of the event to be deleted.
	 * @throws DAOException if an error happens while deleting the event.
	 * @throws IllegalArgumentException if the provided id does not corresponds
	 * with any persisted event.
	 */
	public void delete(int id)
	throws DAOException, IllegalArgumentException {
		try (final Connection conn = this.getConnection()) {
			final String query = "DELETE FROM users WHERE id=?";
			
			try (final PreparedStatement statement = conn.prepareStatement(query)) {
				statement.setInt(1, id);
				
				if (statement.executeUpdate() != 1) {
					throw new IllegalArgumentException("Invalid id");
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error deleting a user", e);
			throw new DAOException(e);
		}
	}
	
	private User rowToEntity(ResultSet row) throws SQLException {
		return new User(
			row.getInt("id"),
			row.getString("name"),
			row.getString("email"),
			row.getString("pass")
		);
	}

}