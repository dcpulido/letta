package es.uvigo.esei.lettaG4.rest;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Timestamp;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.uvigo.esei.lettaG4.dao.DAOException;
import es.uvigo.esei.lettaG4.dao.UsersDAO;
import es.uvigo.esei.lettaG4.entities.User;

/**
 * REST resource for managing people.
 * 
 * @author Miguel Reboiro Jato.
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {
	private final static Logger LOG = Logger.getLogger(UsersResource.class.getName());

	private final UsersDAO dao;

	/**
	 * Constructs a new instance of {@link UsersResource}.
	 */
	public UsersResource() {
		this(new UsersDAO());
	}

	// Needed for testing purposes
	UsersResource(UsersDAO dao) {
		this.dao = dao;
	}

	/**
	 * Returns a person with the provided identifier.
	 * 
	 * @param id
	 *            the identifier of the person to retrieve.
	 * @return a 200 OK response with a person that has the provided identifier.
	 *         If the identifier does not corresponds with any user, a 400 Bad
	 *         Request response with an error message will be returned. If an
	 *         error happens while retrieving the list, a 500 Internal Server
	 *         Error response with an error message will be returned.
	 */
	@GET
	@Path("/{id}")
	public Response get(@PathParam("id") int id) {
		try {
			final User user = this.dao.get(id);

			return Response.ok(user).build();
		} catch (IllegalArgumentException iae) {
			LOG.log(Level.FINE, "Invalid user id in get method", iae);

			return Response.status(Response.Status.BAD_REQUEST).entity(iae.getMessage()).build();
		} catch (DAOException e) {
			LOG.log(Level.SEVERE, "Error getting a user", e);

			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	@GET
	public Response list() {
		try {
			
			return Response.ok(this.dao.list()).build();
			
		} catch (DAOException e) {
			LOG.log(Level.SEVERE, "Error getting a user", e);
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	
	/**
	 * Creates a new person in the system.
	 * 
	 * @param name
	 *            the name of the new person.
	 * @param surname
	 *            the surname of the new person.
	 * @return a 200 OK response with a person that has been created. If the
	 *         name or the surname are not provided, a 400 Bad Request response
	 *         with an error message will be returned. If an error happens while
	 *         retrieving the list, a 500 Internal Server Error response with an
	 *         error message will be returned.
	 */
	@POST
	public Response add(@FormParam("name") String name, @FormParam("email") String email,
			@FormParam("pass") String pass) {
		try {
			final User newUser = this.dao.add(name, email, pass);

			return Response.ok(newUser).build();
		} catch (IllegalArgumentException iae) {
			LOG.log(Level.FINE, "Invalid User id in add method", iae);

			return Response.status(Response.Status.BAD_REQUEST).entity(iae.getMessage()).build();
		} catch (DAOException e) {
			LOG.log(Level.SEVERE, "Error adding a User", e);

			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	/**
	 * Modifies the data of a Event.
	 * 
	 * @param id
	 *            identifier of the person to modify.
	 * @param name
	 *            the new name of the person.
	 * @param surname
	 *            the new surname of the person.
	 * @return a 200 OK response with a person that has been modified. If the
	 *         identifier does not corresponds with any user or the name or
	 *         surname are not provided, a 400 Bad Request response with an
	 *         error message will be returned. If an error happens while
	 *         retrieving the list, a 500 Internal Server Error response with an
	 *         error message will be returned.
	 */
	@PUT
	@Path("/{id}")
	public Response modify(@PathParam("id") int id, @FormParam("name") String name,
			@FormParam("email") String email, @FormParam("pass") String pass) {
		try {
			final User modifiedUser = new User(id, name, email, pass);
			this.dao.modify(modifiedUser);

			return Response.ok(modifiedUser).build();
		} catch (NullPointerException npe) {
			final String message = String.format(
					"Invalid data for User (name: %s, email: %s,pass: %s)", name, email, pass);

			LOG.log(Level.FINE, message);

			return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
		} catch (IllegalArgumentException iae) {
			LOG.log(Level.FINE, "Invalid User id in modify method", iae);

			return Response.status(Response.Status.BAD_REQUEST).entity(iae.getMessage()).build();
		} catch (DAOException e) {
			LOG.log(Level.SEVERE, "Error modifying a User", e);

			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	/**
	 * Deletes a Event from the system.
	 * 
	 * @param id
	 *            the identifier of the person to be deleted.
	 * @return a 200 OK response with the identifier of the person that has been
	 *         deleted. If the identifier does not corresponds with any user, a
	 *         400 Bad Request response with an error message will be returned.
	 *         If an error happens while retrieving the list, a 500 Internal
	 *         Server Error response with an error message will be returned.
	 */
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id) {
		try {
			this.dao.delete(id);

			return Response.ok(id).build();
		} catch (IllegalArgumentException iae) {
			LOG.log(Level.FINE, "Invalid user id in delete method", iae);

			return Response.status(Response.Status.BAD_REQUEST).entity(iae.getMessage()).build();
		} catch (DAOException e) {
			LOG.log(Level.SEVERE, "Error deleting a user", e);

			return Response.serverError().entity(e.getMessage()).build();
		}
	}

}