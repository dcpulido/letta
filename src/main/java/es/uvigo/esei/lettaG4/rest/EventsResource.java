package es.uvigo.esei.lettaG4.rest;

import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
import es.uvigo.esei.lettaG4.dao.EventsDAO;
import es.uvigo.esei.lettaG4.entities.Event;

/**
 * REST resource for managing people.
 * 
 * @author Miguel Reboiro Jato.
 */
@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
public class EventsResource {
	private final static Logger LOG = Logger.getLogger(EventsResource.class.getName());

	private final EventsDAO dao;

	/**
	 * Constructs a new instance of {@link EventsResource}.
	 */
	public EventsResource() {
		this(new EventsDAO());
	}

	// Needed for testing purposes
	EventsResource(EventsDAO dao) {
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
			final Event event = this.dao.get(id);

			return Response.ok(event).build();
		} catch (IllegalArgumentException iae) {
			LOG.log(Level.FINE, "Invalid event id in get method", iae);

			return Response.status(Response.Status.BAD_REQUEST).entity(iae.getMessage()).build();
		} catch (DAOException e) {
			LOG.log(Level.SEVERE, "Error getting a event", e);

			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	@GET
	public Response list(@QueryParam("name") String str) {
		try {
			if (str == "" || str == null) {
				return Response.ok(this.dao.list()).build();
			} else {
				return Response.ok(this.dao.getByName(str)).build();
			}
		} catch (DAOException e) {
			LOG.log(Level.SEVERE, "Error getting a event", e);
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
	@POST
	public Response addEvent(@FormParam("name") String name,
			@FormParam("descripcion") String description, @FormParam("place") String place,
			@FormParam("date") String date,@FormParam("foto") String foto,@FormParam("categoria") String categoria) {
		try {			
			
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
			Date fecha;
			try {
				fecha = format.parse(date);
			} catch (ParseException e) {				
				fecha = new Date(new java.util.Date().getTime() + (1000 * 60 * 60 * 24));				
			}
			final Event event = this.dao.add(name,description,place, fecha ,categoria,foto);

			return Response.ok(event).build();
		} catch (NullPointerException npe) {
			final String message = String.format(
					"Invalid data for Event (name: %s, description: %s,place: %s,date: %s)", name, description, place,
					date.toString());

			LOG.log(Level.FINE, message);

			return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
		} catch (IllegalArgumentException iae) {
			LOG.log(Level.FINE, "Invalid Event id in modify method", iae);

			return Response.status(Response.Status.BAD_REQUEST).entity(iae.getMessage()).build();
		} catch (DAOException e) {
			LOG.log(Level.SEVERE, "Error modifying a Event", e);

			return Response.serverError().entity(e.getMessage()).build();
		}
	}



	@PUT
	@Path("/{id}")
	public Response modify(@PathParam("id") int id, @FormParam("name") String name,
			@FormParam("description") String description, @FormParam("place") String place,
			@FormParam("date") Timestamp date) {
		try {
			final Event modifiedEvent = new Event(id, name, description, place, date);
			this.dao.modify(modifiedEvent);

			return Response.ok(modifiedEvent).build();
		} catch (NullPointerException npe) {
			final String message = String.format(
					"Invalid data for Event (name: %s, description: %s,place: %s,date: %s)", name, description, place,
					date.toString());

			LOG.log(Level.FINE, message);

			return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
		} catch (IllegalArgumentException iae) {
			LOG.log(Level.FINE, "Invalid Event id in modify method", iae);

			return Response.status(Response.Status.BAD_REQUEST).entity(iae.getMessage()).build();
		} catch (DAOException e) {
			LOG.log(Level.SEVERE, "Error modifying a Event", e);

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
			LOG.log(Level.FINE, "Invalid event id in delete method", iae);

			return Response.status(Response.Status.BAD_REQUEST).entity(iae.getMessage()).build();
		} catch (DAOException e) {
			LOG.log(Level.SEVERE, "Error deleting a event", e);

			return Response.serverError().entity(e.getMessage()).build();
		}
	}

}
