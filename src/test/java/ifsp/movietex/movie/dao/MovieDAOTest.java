package ifsp.movietex.movie.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import ifsp.movietex.base.db.PostgresTestContainer;
import ifsp.movietex.movie.entity.DTOMovie;
import ifsp.movietex.movie.entity.Movie;

import ifsp.movietex.movie.entity.DTOMovie;
import ifsp.movietex.movie.entity.Movie;

@Testcontainers
public class MovieDAOTest {

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
	public void givenFindBy_whenIdExists_thenReturnOneMovie() throws SQLException {
		MovieDAO dao = new MovieDAO(connection);
		Movie movie = dao.findBy(ID_EXISTS);

		ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(1) FROM movies WHERE id = " + ID_EXISTS);
		rs.next();
		Integer movies_count = rs.getInt(1);

		assertTrue(movies_count == 1);
		assertTrue(movie != null);
	}
	
	@Test
	public void givenFindBy_whenIdNotExist_thenReturnNull() throws SQLException {
		MovieDAO dao = new MovieDAO(connection);
		Movie movie = dao.findBy(ID_NOT_EXIST);

		ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(1) FROM movies WHERE id = " + ID_NOT_EXIST);
		rs.next();
		Integer movies_count = rs.getInt(1);

		assertTrue(movie == null);
	}
	
	@Test
	public void givenFindBy_whenAllParametersIsNull_thenAllMovies() throws SQLException {
		MovieDAO dao = new MovieDAO(connection);
		List<Movie> movies = dao.findBy(null, null, null, null, null, null);

		ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(1) FROM movies");
		rs.next();
		Integer movies_count = rs.getInt(1);

		assertTrue(movies.size() == movies_count);
		assertFalse(movies.isEmpty());
	}

	@Test
	public void givenFindBy_whenParameterTItleIsInception_thenReturnOneMovie() throws SQLException {
		MovieDAO dao = new MovieDAO(connection);
		List<Movie> movies = dao.findBy("A Origem", null, null, null, null, null);

		int ONE_MOVIE_COUNT = 1;
		assertTrue(movies.size() == ONE_MOVIE_COUNT);
		assertFalse(movies.isEmpty());
	}

	@Test
	public void givenFindBy_whenParameterDescriptionByForrestGump_thenReturnAllMoviesWithDescriptionByForrestGump() throws SQLException {
		MovieDAO dao = new MovieDAO(connection);
		List<Movie> movies = dao.findBy(null, "A história presidencial de Forrest Gump, um homem simples com um baixo QI, mas de bom coração.", 
				null, null, null, null);

		ResultSet rs = connection.createStatement()
				.executeQuery("SELECT COUNT(1) FROM movies WHERE description LIKE 'A história presidencial de Forrest Gump, um homem simples com um baixo QI, mas de bom coração.'");
		rs.next();
		Integer movies_count = rs.getInt(1);

		assertTrue(movies.size() == movies_count);
		assertFalse(movies.isEmpty());
	}
	
	@Test
	public void givenFindBy_whenParameterGenreIsThriller_thenReturnAllMoviesWithGenreThriller() throws SQLException {
		MovieDAO dao = new MovieDAO(connection);
		List<Movie> movies = dao.findBy(null,  null,"Thriller", null, null, null);

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
		List<Movie> movies = dao.findBy(null,  null, null, "Robert Zemeckis", null, null);

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
		List<Movie> movies = dao.findBy(null, null, null, null, 1999, null);

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
		List<Movie> movies = dao.findBy(null, null, null, null, null, 9.0);

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
		List<Movie> movies = dao.findBy(null, null, null, null, null, 9.0, null);

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
		List<Movie> movies = dao.findBy(null, null, null, null, null, null, 9.0);

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
		List<Movie> movies = dao.findBy(null, null, null, null, null, 8.0, 9.0);

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
		List<Movie> movies = dao.findBy("A Origem", 
				"Um ladrão profissional que rouba informações ao infiltrar-se no subconsciente de suas vítimas é oferecido a chance de ter seu passado criminal apagado como pagamento por uma tarefa aparentemente impossível: \\\"inception\\\", a implantação de outra ideia na mente de uma pessoa."
						,"Christopher Nolan", "Ficção Científica", 2010, 8.3);

		ResultSet rs = connection.createStatement()
				.executeQuery("SELECT COUNT(1) FROM movies WHERE title LIKE '%A Origem%' "
						+ "AND description LIKE 'Um ladrão profissional que rouba informações ao infiltrar-se no subconsciente de suas vítimas é oferecido a chance de ter seu passado criminal apagado como pagamento por uma tarefa aparentemente impossível: \"inception\", a implantação de outra ideia na mente de uma pessoa.'"
						+ "AND director LIKE '%Christopher Nolan%' "
						+ "AND genre LIKE '%Ficção Científica%' "
						+ "AND year = 2010 "
						+ "AND rating_average = 8.3");
		rs.next();
		Integer movies_count = rs.getInt(1);
		
		assertTrue(movies.size() == movies_count);
		assertFalse(movies.isEmpty());
	}
	
	@Test
	public void givenFindWithAtLeastOneValue_whenAllParametersIsNull_thenAllMovies() throws SQLException {
		MovieDAO dao = new MovieDAO(connection);
		List<Movie> movies = dao.findWithAtLeastOneValue(null, null, null, null, null, null);

		ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(1) FROM movies WHERE 1!=1");
		rs.next();
		Integer movies_count = rs.getInt(1);

		assertTrue(movies.size() == movies_count);
		assertTrue(movies.isEmpty());
	}

	@Test
	public void givenFindWithAtLeastOneValue_whenParameterTItleIsInception_thenReturnOneMovie() throws SQLException {
		MovieDAO dao = new MovieDAO(connection);
		List<Movie> movies = dao.findBy("A Origem", null, null, null, null, null);

		int ONE_MOVIE_COUNT = 1;
		assertTrue(movies.size() == ONE_MOVIE_COUNT);
		assertFalse(movies.isEmpty());
	}

	@Test
	public void givenFindWithAtLeastOneValue_whenParameterDescriptionByForrestGump_thenReturnAllMoviesWithDescriptionByForrestGump() throws SQLException {
		MovieDAO dao = new MovieDAO(connection);
		List<Movie> movies = dao.findBy(null, "A história presidencial de Forrest Gump, um homem simples com um baixo QI, mas de bom coração.", 
				null, null, null, null);

		ResultSet rs = connection.createStatement()
				.executeQuery("SELECT COUNT(1) FROM movies WHERE description LIKE 'A história presidencial de Forrest Gump, um homem simples com um baixo QI, mas de bom coração.'");

		rs.next();
		Integer movies_count = rs.getInt(1);


		assertTrue(movies.size() == movies_count);
		assertFalse(movies.isEmpty());
	}
	
	@Test
	public void givenFindWithAtLeastOneValue_whenParameterGenreIsThriller_thenReturnAllMoviesWithGenreThriller() throws SQLException {
		MovieDAO dao = new MovieDAO(connection);
		List<Movie> movies = dao.findBy(null,  null,"Thriller", null, null, null);

		ResultSet rs = connection.createStatement()
				.executeQuery("SELECT COUNT(1) FROM movies WHERE genre = 'Thriller'");
		rs.next();
		Integer movies_count = rs.getInt(1);

		assertTrue(movies.size() == movies_count);
		assertFalse(movies.isEmpty());
	}

	@Test
	public void givenFindWithAtLeastOneValue_whenParameterDirectorIsRobertZemeckis_thenReturnAllMoviesWithDirectorRobertZemeckis()
			throws SQLException {
		MovieDAO dao = new MovieDAO(connection);
		List<Movie> movies = dao.findBy(null,  null, null, "Robert Zemeckis", null, null);

		ResultSet rs = connection.createStatement()
				.executeQuery("SELECT COUNT(1) FROM movies WHERE director = 'Robert Zemeckis'");

		rs.next();
		Integer movies_count = rs.getInt(1);

		assertTrue(movies.size() == movies_count);
		assertFalse(movies.isEmpty());
	}

	@Test
	public void givenFindWithAtLeastOneValue_whenParameterYearIs1999_thenReturnAllMoviesWithYear1999() throws SQLException {
		MovieDAO dao = new MovieDAO(connection);
		List<Movie> movies = dao.findBy(null, null, null, null, 1999, null);

		ResultSet rs = connection.createStatement()
				.executeQuery("SELECT COUNT(1) FROM movies WHERE year = 1999");

		rs.next();
		Integer movies_count = rs.getInt(1);

		assertTrue(movies.size() == movies_count);
		assertFalse(movies.isEmpty());
	}

	@Test
	public void givenFindWithAtLeastOneValue_whenParameterRatingAverageIs9_thenReturnAllMoviesWithratingAverage9() throws SQLException {
		MovieDAO dao = new MovieDAO(connection);
		List<Movie> movies = dao.findBy(null, null, null, null, null, 9.0);

		ResultSet rs = connection.createStatement()
				.executeQuery("SELECT COUNT(1) FROM movies WHERE rating_average = 9.0");
		rs.next();
		Integer movies_count = rs.getInt(1);

		assertTrue(movies.size() == movies_count);
		assertFalse(movies.isEmpty());
	}

	

	@Test
	public void givenFindWithAtLeastOneValue_whenAllParameterWithInformation_thenReturnAllWithThisInformations() throws SQLException {
		MovieDAO dao = new MovieDAO(connection);
		List<Movie> movies = dao.findWithAtLeastOneValue("O Poderoso Chefão", 
				"As vidas de dois assassinos de aluguel, um boxeador e um casal de bandidos se entrelaçam em quatro contos de violência e redenção."
						,"David Fincher", "Drama", 1980, 9.3);

		ResultSet rs = connection.createStatement()
				.executeQuery("SELECT COUNT(1) FROM movies WHERE title LIKE '%O Poderoso Chefão%' "
						+ "OR description LIKE 'As vidas de dois assassinos de aluguel, um boxeador e um casal de bandidos se entrelaçam em quatro contos de violência e redenção.%'"
						+ "OR director LIKE '%David Fincher%' "
						+ "OR genre LIKE '%Drama%' "
						+ "OR year = 1980 "
						+ "OR rating_average = 9.3");
		rs.next();
		Integer movies_count = rs.getInt(1);

		assertTrue(movies.size() == movies_count);
		assertFalse(movies.isEmpty());
	}
	
	
	@Test
	public void givenInsert_whenMovieAlreadyExists_thenReturnFailedMessage() throws SQLException {
		MovieDAO dao = new MovieDAO(connection);
		String title = "A Origem";
		String msg = dao.insert(new DTOMovie(title, "Um ladrão profissional que rouba informações ao infiltrar-se no subconsciente de suas "
				+ "vítimas é oferecido a chance de ter seu passado criminal apagado como pagamento por uma tarefa aparentemente impossível: "
				+ "'inception', a implantação de outra ideia na mente de uma pessoa.", "Christopher Nolan", "Ficção Científica", 2010));

		assertEquals("Falha ao cadastrar o filme " + title, msg);
	}
	
	@Test
	public void givenInsert_whenMovieNotExist_thenReturnSuccessMessage() throws SQLException {
		MovieDAO dao = new MovieDAO(connection);
		
		String msg = dao.insert(new DTOMovie("Divertidamente 2", "Com um salto temporal, Riley se encontra mais velha, passando pela tão temida adolescência. "
				+ "Junto com o amadurecimento, a sala de controle também está passando por uma adaptação para dar lugar a algo totalmente inesperado: novas emoções.",
				"Kelsey Mann", "Animação", 2024));
		
		ResultSet rs = connection.createStatement()
				.executeQuery("SELECT COUNT(1) FROM movies");
		rs.next();
		Integer movies_count = rs.getInt(1);

		
		assertEquals("Sucesso ao cadastrar o filme Divertidamente 2 de id: " + movies_count, msg);
	}
	
	@Test
	public void givenUpdate_whenMovieExists_thenReturnSuccessMessage() throws SQLException {
		MovieDAO dao = new MovieDAO(connection);
		String title = "The Godfather";
		String msg = dao.update(new DTOMovie(2, title, "The saga of the Corleone family and the rise of Michael Corleone as the patriarch.",
				"Francis Ford Coppola", "Crime", 1972));
		
		assertEquals(String.format("%s atualizado com sucesso", title), msg);
	}
	
	@Test
	public void givenUpdate_whenMovieNotExist_thenReturnFailedMessage() throws SQLException {
		MovieDAO dao = new MovieDAO(connection);
		String title = "The Godfather";
		String msg = dao.update(new DTOMovie(ID_NOT_EXIST, title, "The saga of the Corleone family and the rise of Michael Corleone as the patriarch.",
				"Francis Ford Coppola", "Crime", 1972));
		
		assertEquals(String.format("Falha na atualização do filme: %s", title), msg);
	}
}
