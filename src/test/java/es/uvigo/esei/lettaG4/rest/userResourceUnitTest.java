package es.uvigo.esei.lettaG4.rest;

import static org.easymock.EasyMock.anyInt;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;

import es.uvigo.esei.lettaG4.dao.DAOException;
import es.uvigo.esei.lettaG4.dao.UsersDAO;
import es.uvigo.esei.lettaG4.dataset.UsersDataset;
import es.uvigo.esei.lettaG4.entities.User;
import es.uvigo.esei.lettaG4.matchers.HasHttpStatus;
import es.uvigo.esei.lettaG4.matchers.IsEqualToUser;




public class userResourceUnitTest {
	private UsersDAO daoUserMock;
	private UsersResource resource;

	@Before
	public void setUp() throws Exception {
		daoUserMock = createMock(UsersDAO.class);
		resource = new UsersResource(daoUserMock);
	}

	@After
	public void tearDown() throws Exception {
		try {
			verify(daoUserMock);
		} finally {
			daoUserMock = null;
			resource = null;
		}
	}

	@Test
	public void testList() throws Exception {		
		
		final List<User> users = Arrays.asList(UsersDataset.user());		
		
		expect(daoUserMock.list()).andReturn(users);
		replay(daoUserMock);
		
		final Response response = resource.list();
		assertEquals(users, response.getEntity());
		assertEquals(Status.OK, response.getStatusInfo());
	}

	@Test
	public void testListDAOException() throws Exception {
		expect(daoUserMock.list()).andThrow(new DAOException());
		replay(daoUserMock);
		
		final Response response = resource.list();
		assertEquals(Status.INTERNAL_SERVER_ERROR, response.getStatusInfo());
	}
	@Test
	public void testGet() throws Exception {
		final User user = UsersDataset.newUser();
		
		expect(daoUserMock.get(user.getId())).andReturn(user);
		replay(daoUserMock);

		final Response response = resource.get(user.getId());
		assertEquals(user, response.getEntity());
		assertEquals(Status.OK, response.getStatusInfo());
	}

	@Test
	public void testGetDAOException() throws Exception {
		expect(daoUserMock.get(anyInt())).andThrow(new DAOException());
		replay(daoUserMock);
		

		final Response response = resource.get(1);
		assertEquals(Status.INTERNAL_SERVER_ERROR, response.getStatusInfo());
	}

	@Test
	public void testGetIllegalArgumentException() throws Exception {
		expect(daoUserMock.get(anyInt())).andThrow(new IllegalArgumentException());
		replay(daoUserMock);
		
		final Response response = resource.get(1);
		assertEquals(Status.BAD_REQUEST, response.getStatusInfo());
	}
	
	@Test
	public void testDelete() throws Exception {
		daoUserMock.delete(anyInt());
		replay(daoUserMock);
		

		final Response response = resource.delete(1);
		assertEquals(Status.OK, response.getStatusInfo());
	}

	@Test
	public void testDeleteDAOException() throws Exception {
		daoUserMock.delete(anyInt());
		expectLastCall().andThrow(new DAOException());
		replay(daoUserMock);
		
		
		final Response response = resource.delete(1);
		assertEquals(Status.INTERNAL_SERVER_ERROR, response.getStatusInfo());
	}

	@Test
	public void testDeleteIllegalArgumentException() throws Exception {
		daoUserMock.delete(anyInt());
		expectLastCall().andThrow(new IllegalArgumentException());
		replay(daoUserMock);
		
		
		final Response response = resource.delete(1);
		assertEquals(Status.BAD_REQUEST, response.getStatusInfo());
	}

	@Test
	public void testModify() throws Exception {
		final User user = UsersDataset.newUser();
		
		daoUserMock.modify(user);
		
		replay(daoUserMock);
		

		final Response response = resource.modify(
			user.getId(), user.getName(), user.getEmail(), user.getPass());
		
		assertEquals(user, response.getEntity());
		assertEquals(Status.OK, response.getStatusInfo());
	}

	@Test
	public void testModifyDAOException() throws Exception {
		daoUserMock.modify(anyObject());
		expectLastCall().andThrow(new DAOException());
		
		replay(daoUserMock);

		final Response response = resource.modify(UsersDataset.existentId(),UsersDataset.newName(), UsersDataset.newEmail(), UsersDataset.newPass());
		assertEquals(Status.INTERNAL_SERVER_ERROR, response.getStatusInfo());
	}

	@Test
	public void testModifyIllegalArgumentException() throws Exception {
		daoUserMock.modify(anyObject());
		expectLastCall().andThrow(new IllegalArgumentException());
		
		replay(daoUserMock);
		
		final Response response =resource.modify(UsersDataset.existentId(), UsersDataset.newName(), UsersDataset.newEmail(), UsersDataset.newPass());
		assertEquals(Status.BAD_REQUEST, response.getStatusInfo());
	}

	@Test
	public void testAdd() throws Exception {
		
		
		expect(daoUserMock.add(UsersDataset.newName(), UsersDataset.newEmail(), UsersDataset.newPass()))
			.andReturn(UsersDataset.newUser());
		replay(daoUserMock);
		
		

		final Response response = resource.add(UsersDataset.newName(), UsersDataset.newEmail(), UsersDataset.newPass());
		assertThat(response, HasHttpStatus.hasOkStatus());
		assertEquals(Status.OK, response.getStatusInfo());
		assertThat((User)response.getEntity(), IsEqualToUser.equalsToUser(UsersDataset.newUser()));
	}
			
}