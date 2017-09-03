package es.uvigo.esei.lettaG4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Timestamp;
import es.uvigo.esei.lettaG4.entities.Event;
import es.uvigo.esei.lettaG4.entities.User;
import es.uvigo.esei.lettaG4.entities.Relationship;

/**
 * DAO class for the {@link Person} entities.
 * 
 * @author Miguel Reboiro Jato
 *
 */
public class UsersOnEventsDAO extends DAO {
	private final static Logger LOG = Logger.getLogger(UsersOnEventsDAO.class.getName());
	private final UsersDAO userDao = new UsersDAO();
	/**
	 * Returns a person stored persisted in the system.
	 * 
	 * @param id identifier of the person.
	 * @return a person with the provided identifier.
	 * @throws DAOException if an error happens while retrieving the person.
	 * @throws IllegalArgumentException if the provided id does not corresponds
	 * with any persisted person.
	 */
	public List<User> getByEventId(int id)
	throws DAOException, IllegalArgumentException {
		try (final Connection conn = this.getConnection()) {
			final String query = "SELECT * FROM users_on_events WHERE eventId=?";
			
			try (final PreparedStatement statement = conn.prepareStatement(query)) {
				try (final ResultSet result = statement.executeQuery()) {
					final List<Relationship> relations = new LinkedList<>();
					final List<User> users= new LinkedList<>();
					
					while (result.next()) {
						relations.add(rowToEntity(result));
					}
					Iterator<Relationship> it = relations.iterator();
					while (it.hasNext()) {
						users.add(userDao.get(it.next().getIdUser()));
					}
					
					return users;
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error listing users", e);
			throw new DAOException(e);
		}
	}
	
	
	private Relationship rowToEntity(ResultSet row) throws SQLException {
		return new Relationship(
			row.getInt("userId"),
			row.getInt("eventId")
		);
	}

}