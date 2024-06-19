package ifsp.movietex.movie.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import ifsp.movietex.base.db.ConnectionPostgress;
import ifsp.movietex.base.db.PostgresTestContainer;
import ifsp.movietex.movie.entity.Movie;

@Testcontainers
public class MovieDAOTest {

	@Container
	public static PostgreSQLContainer<?> postgresContainer = PostgresTestContainer.getContainer();

	private static Connection connection;

	@BeforeAll
	public static void setUp() {
		postgresContainer.start();
		connection = PostgresTestContainer.createConnection(postgresContainer);
	}

	@Test
	public void givenFindBy_whenAllParametersIsNull_thenAllMovies() throws SQLException {
		MovieDAO dao = new MovieDAO(connection);
		List<Movie> movies = dao.findBy(null, null, null, null, null);

		ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(1) FROM movies");
		rs.next();
		Integer movies_count = rs.getInt(1);

		assertTrue(movies.size() == movies_count);
		assertFalse(movies.isEmpty());
	}

	@Test
	public void givenFindBy_whenParameterTItleIsInception_thenReturnOneMovie() throws SQLException {
		MovieDAO dao = new MovieDAO(connection);
		List<Movie> movies = dao.findBy("Inception", null, null, null, null);

		int ONE_MOVIE_COUNT = 1;
		assertTrue(movies.size() == ONE_MOVIE_COUNT);
		assertFalse(movies.isEmpty());
	}

	@Test
	public void givenFindBy_whenParameterGenreIsThriller_thenReturnAllMoviesWithGenreThriller() throws SQLException {
		MovieDAO dao = new MovieDAO(connection);
		List<Movie> movies = dao.findBy(null, "Thriller", null, null, null);

		ResultSet rs = connection.createStatement()
				.executeQuery("SELECT COUNT(1) FROM movies WHERE genre = 'Thriller'");
		rs.next();
		Integer movies_count = rs.getInt(1);

		assertTrue(movies.size() == movies_count);
		assertFalse(movies.isEmpty());
	}

	@Test
	public void givenFindBy_whenParameterDirectorIsRobertZemeckis_thenReturnAllMoviesWithDirectorRobertZemeckis()
			throws SQLException {
		MovieDAO dao = new MovieDAO(connection);
		List<Movie> movies = dao.findBy(null, null, "Robert Zemeckis", null, null);

		ResultSet rs = connection.createStatement()
				.executeQuery("SELECT COUNT(1) FROM movies WHERE director = 'Robert Zemeckis'");

		rs.next();
		Integer movies_count = rs.getInt(1);

		assertTrue(movies.size() == movies_count);
		assertFalse(movies.isEmpty());
	}

	@Test
	public void givenFindBy_whenParameterYearIs1999_thenReturnAllMoviesWithYear1999() throws SQLException {
		MovieDAO dao = new MovieDAO(connection);
		List<Movie> movies = dao.findBy(null, null, null, 1999, null);

		ResultSet rs = connection.createStatement()
				.executeQuery("SELECT COUNT(1) FROM movies WHERE year = 1999");

		rs.next();
		Integer movies_count = rs.getInt(1);

		assertTrue(movies.size() == movies_count);
		assertFalse(movies.isEmpty());
	}

	@Test
	public void givenFindBy_whenParameterRatingAverageIs9_thenReturnAllMoviesWithratingAverage9() throws SQLException {
		MovieDAO dao = new MovieDAO(connection);
		List<Movie> movies = dao.findBy(null, null, null, null, 9.0);

		ResultSet rs = connection.createStatement()
				.executeQuery("SELECT COUNT(1) FROM movies WHERE rating_average = 9.0");
		rs.next();
		Integer movies_count = rs.getInt(1);

		assertTrue(movies.size() == movies_count);
		assertFalse(movies.isEmpty());
	}


	@Test
	public void givenFindBy_whenParameterMinRatingAverageIs9_thenReturnAllMoviesWithMinRatingAverage9() throws SQLException {
		MovieDAO dao = new MovieDAO(connection);
		List<Movie> movies = dao.findBy(null, null, null, null, 9.0, null);

		ResultSet rs = connection.createStatement()
				.executeQuery("SELECT COUNT(1) FROM movies WHERE rating_average >= 9.0");
		rs.next();
		Integer movies_count = rs.getInt(1);

		assertTrue(movies.size() == movies_count);
		assertFalse(movies.isEmpty());
	}

	@Test
	public void givenFindBy_whenParameterMaxRatingAverageIs9_thenReturnAllMoviesWithMaxRatingAverage9() throws SQLException {
		MovieDAO dao = new MovieDAO(connection);
		List<Movie> movies = dao.findBy(null, null, null, null, null, 9.0);

		ResultSet rs = connection.createStatement()
				.executeQuery("SELECT COUNT(1) FROM movies WHERE rating_average <= 9.0");
		rs.next();
		Integer movies_count = rs.getInt(1);

		assertTrue(movies.size() == movies_count);
		assertFalse(movies.isEmpty());
	}

	@Test
	public void givenFindBy_whenParameterMinRatingAverageIs8AndMaxRatingAverageIs9_thenReturnAllMoviesWithMinRatingAverage8AndMaxRatingAverage9() throws SQLException {
		MovieDAO dao = new MovieDAO(connection);
		List<Movie> movies = dao.findBy(null, null, null, null, 8.0, 9.0);

		ResultSet rs = connection.createStatement()
				.executeQuery("SELECT COUNT(1) FROM movies WHERE rating_average >= 8.0 AND rating_average <= 9.0");
		rs.next();
		Integer movies_count = rs.getInt(1);

		assertTrue(movies.size() == movies_count);
		assertFalse(movies.isEmpty());
	}
	
	@Test
	public void givenFindBy_whenAllParameterWithInformation_thenReturnAllWithThisInformations() throws SQLException {
		MovieDAO dao = new MovieDAO(connection);
		List<Movie> movies = dao.findBy("Inception", "Christopher Nolan", "Sci-Fi", 2010, 8.3);

		ResultSet rs = connection.createStatement()
				.executeQuery("SELECT COUNT(1) FROM movies WHERE title = 'Inception' AND director = 'Christopher Nolan' AND genre = 'Sci-Fi' AND year = 2010 AND rating_average = 8.3");
		rs.next();
		Integer movies_count = rs.getInt(1);

		assertTrue(movies.size() == movies_count);
		assertFalse(movies.isEmpty());
	}
	
	@Test
	public void givenFindInsert_whenMovieAlreadyExists_thenReturnFailedMessage() throws SQLException {
		MovieDAO dao = new MovieDAO(connection);
		String msg = dao.insert("Inception", "Christopher Nolan", "Sci-Fi", 2010);

		assertEquals("Falha ao cadastrar o filme Inception", msg);
	}
	
	@Test
	public void givenFindInsert_whenMovieNotExist_thenReturnSuccessMessage() throws SQLException {
		MovieDAO dao = new MovieDAO(connection);
		String msg = dao.insert("Divertidamente 2", "Com um salto temporal, Riley se encontra mais velha, passando pela tão temida adolescência. "
				+ "Junto com o amadurecimento, a sala de controle também está passando por uma adaptação para dar lugar a algo totalmente inesperado: novas emoções.",
				"Kelsey Mann", "Animação", 2024);
		System.err.println(msg);
		
		ResultSet rs = connection.createStatement()
				.executeQuery("SELECT COUNT(1) FROM movies");
		rs.next();
		Integer movies_count = rs.getInt(1);

		
		assertEquals("Sucesso ao cadastrar o filme de Divertidamente 2: " + movies_count, msg);
	}
}
