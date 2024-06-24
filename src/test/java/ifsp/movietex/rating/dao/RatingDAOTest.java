package ifsp.movietex.rating.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import ifsp.movietex.base.db.PostgresTestContainer;
import ifsp.movietex.rating.entity.Rating;

class RatingDAOTest {

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
	public void givenFindBy_whenIDExists_thenReturnAllRating() throws SQLException {
		RatingDAO dao = new RatingDAO(connection);
		List<Rating> ratings = dao.findBy(ID_EXISTS);

		ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(1) FROM Assessments WHERE id_movie = " + ID_EXISTS);
		rs.next();
		Integer rating_count = rs.getInt(1);
		System.out.println("ratings " + ratings.size());
		System.out.println("rating_count " + rating_count);
		assertTrue(ratings.size() == rating_count);
		assertFalse(ratings.isEmpty());
	}
	
	@Test
	public void givenFindBy_whenIDNotExist_thenReturnAllRating() throws SQLException {
		RatingDAO dao = new RatingDAO(connection);
		List<Rating> ratings = dao.findBy(ID_NOT_EXIST);

		ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(1) FROM Assessments  WHERE id_movie = " + ID_NOT_EXIST);
		rs.next();
		Integer rating_count = rs.getInt(1);

		assertTrue(ratings.size() == rating_count);
		assertTrue(ratings.isEmpty());
	}
	
	@Test
	public void givenInsert_whenAssesmentsNotExist_thenReturnFailedMessage() throws SQLException {
		RatingDAO dao = new RatingDAO(connection);
		String msg = dao.insert(1, 2, "Teste", 8.4, "Muito bom");

		assertEquals("Avaliação enviada com sucesso!", msg);
	}
	
	@Test
	public void givenInsert_whenAssesmentsAlreadyExistsWithUserToMovie_thenReturnFailedMessage() throws SQLException {
		RatingDAO dao = new RatingDAO(connection);
		String msg = dao.insert(1, 1, "Teste",8.4, "Muito bom");

		assertEquals("Falha ao enviar a avaliação!", msg);
	}
	
}
