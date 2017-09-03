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
import java.util.Date;
import java.sql.Timestamp;
import es.uvigo.esei.lettaG4.entities.Event;
import es.uvigo.esei.lettaG4.entities.User;


/**
 * DAO class for the {@link Person} entities.
 * 
 * @author Miguel Reboiro Jato
 *
 */
public class EventsDAO extends DAO {
	private final static Logger LOG = Logger.getLogger(EventsDAO.class.getName());
	
	/**
	 * Returns a person stored persisted in the system.
	 * 
	 * @param id identifier of the person.
	 * @return a person with the provided identifier.
	 * @throws DAOException if an error happens while retrieving the person.
	 * @throws IllegalArgumentException if the provided id does not corresponds
	 * with any persisted person.
	 */
	public Event get(int id)
	throws DAOException, IllegalArgumentException {
		try (final Connection conn = this.getConnection()) {
			final String query = "SELECT * FROM events WHERE id=?";
			
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
			LOG.log(Level.SEVERE, "Error getting a Event", e);
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
	
	public Event add(String name,String description, String place, Date date)
	throws DAOException, IllegalArgumentException {
	    return add(name,  description, place, date, null,null);
	}
	
	
	public Event add(String name,String description, String place, Date date,String categoria,String foto)
	throws DAOException, IllegalArgumentException {
		if (name == null || description == null) {
			throw new IllegalArgumentException("name and description can't be null");
		}
		
		try (Connection conn = this.getConnection()) {
			final String query = "INSERT INTO events (`name`,`description`,`place`,`date`,`idCreador`,`categoria`,`foto`) "
					+ "VALUES(?, ?, ?, ?,?,?,?)";
			
			try (PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, name);
				statement.setString(2, description);
				statement.setString(3, place);
				statement.setTimestamp(4, new Timestamp(date.getTime()));
				statement.setInt(5,-1);
				statement.setString(6, categoria);
				statement.setString(7, foto);
				
				if (statement.executeUpdate() == 1) {
					try (ResultSet resultKeys = statement.getGeneratedKeys()) {
						if (resultKeys.next()) {
							return new Event(resultKeys.getInt(1), name, description,place,new Timestamp(date.getTime()));
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
			LOG.log(Level.SEVERE, "Error adding a Event", e);
			throw new DAOException(e);
		}
	}
	
	/**
	 * Modifies a Event previously persisted in the system. The Event will be
	 * retrieved by the provided id and its current name and surname will be
	 * replaced with the provided.
	 * 
	 * @param Event a {@link Event} entity with the new data.
	 * @throws DAOException if an error happens while modifying the new Event.
	 * @throws IllegalArgumentException if the Event is {@code null}.
	 */
	public void modify(Event event)
	throws DAOException, IllegalArgumentException {
		if (event == null) {
			throw new IllegalArgumentException("event can't be null");
		}
		
		try (Connection conn = this.getConnection()) {
			final String query = "UPDATE events SET name=?, description=?, place=?,date=?,idCreador=?, WHERE id=?";
			
			try (PreparedStatement statement = conn.prepareStatement(query)) {
				statement.setString(1, event.getName());
				statement.setString(2, event.getDescription());
				statement.setString(3, event.getPlace());
				statement.setTimestamp(4, event.getTimestamp());
				statement.setInt(6, event.getId());
				statement.setInt(5,event.getCreador().getId());
				
				if (statement.executeUpdate() != 1) {
					throw new IllegalArgumentException("name and description can't be null");
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error modifying a event", e);
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
			final String query = "DELETE FROM events WHERE id=?";
			
			try (final PreparedStatement statement = conn.prepareStatement(query)) {
				statement.setInt(1, id);
				
				if (statement.executeUpdate() != 1) {
					throw new IllegalArgumentException("Invalid id");
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error deleting a event", e);
			throw new DAOException(e);
		}
	}
	

	/**
	 * Returns a list with all the events persisted in the system.
	 * 
	 * @return a list with all the events persisted in the system.
	 * @throws DAOException if an error happens while retrieving the events.
	 */
	public List<Event> list() throws DAOException {
		try (final Connection conn = this.getConnection()) {
			final String query = "SELECT * FROM events ORDER BY date ASC";
			
			try (final PreparedStatement statement = conn.prepareStatement(query)) {
				try (final ResultSet result = statement.executeQuery()) {
					final List<Event> events = new LinkedList<>();
					
					while (result.next()) {
						events.add(rowToEntity(result));
					}
					
					return events;
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error listing events", e);
			throw new DAOException(e);
		}
	}
	
	
	public List<Event> getByName(String str)
	throws DAOException, IllegalArgumentException {
		try (final Connection conn = this.getConnection()) {
			final String query = "SELECT * FROM events WHERE name LIKE ? OR description LIKE ?  ORDER BY date ASC";
			
			try (final PreparedStatement statement = conn.prepareStatement(query)) {
				statement.setString(1, '%'+str+'%');
				statement.setString(2, '%'+str+'%');
				try (final ResultSet result = statement.executeQuery()) {
					final List<Event> events = new LinkedList<>();
					
					while (result.next()) {
						events.add(rowToEntity(result));
					}
					
					return events;
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error showing events by name", e);
			throw new DAOException(e);
		}
	}
	
	private Event rowToEntity(ResultSet row) throws SQLException, IllegalArgumentException, DAOException {
		int id;
		if(new UsersDAO().get(row.getInt("idCreador"))==null){
			id=-1;
		}
		else{
			id=row.getInt("idCreador");
		}
		return new Event(
			row.getInt("id"),
			new UsersDAO().get(id),			
		    null,
			row.getString("name"),
			row.getString("description"),
		   (row.getString("categoria") == null) ? "Otros" : row.getString("categoria"),
			row.getString("place"),
			row.getTimestamp("date"),
			row.getString("foto")
		);		
		
	}

}