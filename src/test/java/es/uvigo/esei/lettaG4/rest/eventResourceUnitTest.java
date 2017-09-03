package es.uvigo.esei.lettaG4.rest;

import es.uvigo.esei.lettaG4.dao.*;
import static org.easymock.EasyMock.anyInt;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.uvigo.esei.lettaG4.dao.DAOException;
import es.uvigo.esei.lettaG4.dao.EventsDAO;
import es.uvigo.esei.lettaG4.dataset.EventsDataset;
import es.uvigo.esei.lettaG4.entities.Event;

public class eventResourceUnitTest {
	private EventsDAO daoEventMock;

	private EventsResource resource;

	@Before
	public void setUp() throws Exception {
		daoEventMock = createMock(EventsDAO.class);
		resource = new EventsResource(daoEventMock);
	}

	@After
	public void tearDown() throws Exception {
		try {
			verify(daoEventMock);
		} finally {
			daoEventMock = null;
			resource = null;
		}
	}

	@Test
	public void testList() throws Exception {
		final List<Event> events = Arrays.asList(EventsDataset.event());
		
		expect(daoEventMock.list()).andReturn(events);
		replay(daoEventMock);
		
		final Response response = resource.list("");
		assertEquals(events, response.getEntity());
		assertEquals(Status.OK, response.getStatusInfo());
	}

	@Test
	public void testListDAOException() throws Exception {
		expect(daoEventMock.list()).andThrow(new DAOException());
		replay(daoEventMock);
		
		final Response response = resource.list("");
		assertEquals(Status.INTERNAL_SERVER_ERROR, response.getStatusInfo());
	}
	@Test
	public void testGet() throws Exception {
		final Event event = new Event(1, "Series", "Evento de series", "Bar series", new Timestamp(1459852342));
		
		expect(daoEventMock.get(event.getId())).andReturn(event);
		replay(daoEventMock);

		final Response response = resource.get(event.getId());
		assertEquals(event, response.getEntity());
		assertEquals(Status.OK, response.getStatusInfo());
	}

	@Test
	public void testGetDAOException() throws Exception {
		expect(daoEventMock.get(anyInt())).andThrow(new DAOException());
		replay(daoEventMock);
		

		final Response response = resource.get(1);
		assertEquals(Status.INTERNAL_SERVER_ERROR, response.getStatusInfo());
	}

	@Test
	public void testGetIllegalArgumentException() throws Exception {
		expect(daoEventMock.get(anyInt())).andThrow(new IllegalArgumentException());
		replay(daoEventMock);
		
		final Response response = resource.get(1);
		assertEquals(Status.BAD_REQUEST, response.getStatusInfo());
	}
	
	@Test
	public void testDelete() throws Exception {
		daoEventMock.delete(anyInt());
		replay(daoEventMock);
		

		final Response response = resource.delete(1);
		assertEquals(Status.OK, response.getStatusInfo());
	}

	@Test
	public void testDeleteDAOException() throws Exception {
		daoEventMock.delete(anyInt());
		expectLastCall().andThrow(new DAOException());
		replay(daoEventMock);
		
		
		final Response response = resource.delete(1);
		assertEquals(Status.INTERNAL_SERVER_ERROR, response.getStatusInfo());
	}

	@Test
	public void testDeleteIllegalArgumentException() throws Exception {
		daoEventMock.delete(anyInt());
		expectLastCall().andThrow(new IllegalArgumentException());
		replay(daoEventMock);
		
		
		final Response response = resource.delete(1);
		assertEquals(Status.BAD_REQUEST, response.getStatusInfo());
	}

	@Test
	public void testModify() throws Exception {
		final Event event = new Event(1, "Series", "Evento de series", "Bar series", new Timestamp(1459852342));
		
		daoEventMock.modify(event);
		
		replay(daoEventMock);
		

		final Response response = resource.modify(
			event.getId(), event.getName(), event.getDescription(), event.getPlace(), event.getTimestamp());
		
		assertEquals(event, response.getEntity());
		assertEquals(Status.OK, response.getStatusInfo());
	}

	@Test
	public void testModifyDAOException() throws Exception {
		daoEventMock.modify(anyObject());
		expectLastCall().andThrow(new DAOException());
		
		replay(daoEventMock);

		final Response response = resource.modify(1, "Series", "Evento de series", "Bar series", new Timestamp(1459852342));
		assertEquals(Status.INTERNAL_SERVER_ERROR, response.getStatusInfo());
	}

	@Test
	public void testModifyIllegalArgumentException() throws Exception {
		daoEventMock.modify(anyObject());
		expectLastCall().andThrow(new IllegalArgumentException());
		
		replay(daoEventMock);
		
		final Response response = resource.modify(1, "Series", "Evento de series", "Bar series", new Timestamp(1459852342));
		assertEquals(Status.BAD_REQUEST, response.getStatusInfo());
	}

	@Test
	public void testAdd() throws Exception {
		final Event event = EventsDataset.existentEvent();
		
		String myFecha="2016-11-08 20:20:20";
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
		Date fecha;
		try {
			fecha = format.parse(myFecha);
		} catch (ParseException e) {				
			fecha = new Date(new java.util.Date().getTime() + (1000 * 60 * 60 * 24));				
		}




		expect(daoEventMock.add(event.getName(),event.getDescription(), event.getPlace(), fecha,event.getCategoria(),event.getFoto()))
			.andReturn(event);
		replay(daoEventMock);
		
		

		final Response response = resource.addEvent(
			event.getName(), event.getDescription(), event.getPlace(), myFecha,event.getFoto(),event.getCategoria());
		assertEquals(event, response.getEntity());
		assertEquals(Status.OK, response.getStatusInfo());
	}
	
	@Test
	public void testBusquedaBasica() throws Exception {
		final List<Event> events = Arrays.asList(
			new Event(1, "Series", "Evento de series", "Bar series", new Timestamp(1459852342)),
			new Event(2, "Series2", "Evento de series2", "Bar series2", new Timestamp(1459854342)),
			new Event(3, "Series3", "Evento de series3", "Bar series3", new Timestamp(1459852542))
		);
		
		expect(daoEventMock.getByName("Series2")).andReturn(events);
		replay(daoEventMock);
		
		final Response response = resource.list("Series2");
		assertEquals(events, response.getEntity());
		assertEquals(Status.OK, response.getStatusInfo());
	}

	@Test
	public void testListconCreador() throws Exception {
		final List<Event> events = Arrays.asList(EventsDataset.events());
		
		expect(daoEventMock.list()).andReturn(events);
		replay(daoEventMock);
		
		final Response response = resource.list("");
		assertEquals(events, response.getEntity());
		assertEquals(Status.OK, response.getStatusInfo());
	}

	
}
