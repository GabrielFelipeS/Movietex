package ifsp.movietex.movie.dao;

import java.sql.Connection;

import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import ifsp.movietex.base.db.PostgresTestContainer;

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
}
