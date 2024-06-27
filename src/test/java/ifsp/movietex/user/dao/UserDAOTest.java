package ifsp.movietex.user.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import ifsp.movietex.base.db.PostgresTestContainer;
import ifsp.movietex.user.entity.DTOUser;
import ifsp.movietex.user.entity.User;

class UserDAOTest {

	private static final Integer ID_EXISTS = 1;
	private static final Integer ID_NOT_EXIST = 0;


	@Container
	public static PostgreSQLContainer<?> postgresContainer = PostgresTestContainer.getContainer();

	private static Connection connection;

	@BeforeAll
	public static void setUp() {
		postgresContainer.start();
		connection = PostgresTestContainer.createConnection(postgresContainer);
	}
	
	@Test
	public void givenRegister_whenUserDoesNotExists_thenReturnTrue() throws SQLException {
		UserDAO dao = new UserDAO(connection);
		Boolean success = dao.register(new DTOUser("Amanda", "email.ifsp@aluno.ifsp.edu.br", "senha"));
		
		assertTrue(success);
	}
	
	@Test
	public void givenRegister_whenUserAlreadyExists_thenReturnTrue() throws SQLException {
		UserDAO dao = new UserDAO(connection);
		Boolean success = dao.register(new DTOUser("Alice Smith", "admin@example.com", "password2"));
		
		assertFalse(success);
	}
	

	
	@Test
	public void givenLogin_whenUserAlreadyExistsAndIsNotAdminAndPasswordCorrect_thenReturnStringAdmin() throws SQLException {
		UserDAO dao = new UserDAO(connection);
		User user = dao.login(new DTOUser("Charlie Brown", "user1@example.com", "password1"));
		
		assertFalse(user.isAdmin());
	}
	
	@Test
	public void givenLogin_whenUserAlreadyExistsAndPasswordIncorrect_thenReturnNull() throws SQLException {
		UserDAO dao = new UserDAO(connection);
		User role = dao.login(new DTOUser("Alice Smith", "admin@example.com", "password3"));
		
		assertEquals(null, role);
	}

	
	@Test
	public void givenDeleteUser_whenUserIDExists_thenReturnTrue() throws SQLException {
		UserDAO dao = new UserDAO(connection);
		Boolean success = dao.deleteUser(ID_EXISTS);
		
		assertTrue(success);
	}
	
	@Test
	public void givenDeleteUser_whenUserIDNotExist_thenReturnFalse() throws SQLException {
		UserDAO dao = new UserDAO(connection);
		Boolean success = dao.deleteUser(ID_NOT_EXIST);
		
		assertFalse(success);
	}
	
	
	@Test
	public void givenUpdateUser_whenUserIDNotExist_thenReturnFalse() throws SQLException {
		UserDAO dao = new UserDAO(connection);
		Boolean success = dao.updateUser(new User(70, "Gabriel", "email@aluno.ifsp.edu.br", "senha", true));
		
		assertFalse(success);
	}
	
	@Test
	public void givenUpdateUser_whenUserIDExists_thenReturnTrue() throws SQLException {
		UserDAO dao = new UserDAO(connection);
		Boolean success = dao.updateUser(new User(5, "Gabriel", "email@aluno.ifsp.edu.br", "senha", true));
		
		assertTrue(success);
	}
	
	
	

}
