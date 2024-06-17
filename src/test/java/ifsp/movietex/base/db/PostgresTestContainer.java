package ifsp.movietex.base.db;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.MountableFile;

//@Disabled
public class PostgresTestContainer {

	private static Connection connection;
	@Container
	public static PostgreSQLContainer<?> postgresContainer = PostgresTestContainer.getContainer();

	@BeforeAll
	public static void setUp() {
		postgresContainer.start();
		connection = PostgresTestContainer.createConnection(postgresContainer);
	}

	public static Connection connectInContainer(IDBConnector iDbConnector) {
		PostgresTestContainer.postgresContainer.start();

		return iDbConnector.getConnection(
				String.format("jdbc:postgresql://localhost:%d/movietex",
						PostgresTestContainer.postgresContainer.getMappedPort(5432)),
				PostgresTestContainer.postgresContainer.getUsername(),
				PostgresTestContainer.postgresContainer.getPassword());
	}

	@SuppressWarnings("resource")
	public static PostgreSQLContainer<?> getContainer() {
		return new PostgreSQLContainer<>("postgres:16")
				.withUsername("postgres")
				.withPassword("admin")
				.withDatabaseName("movietex")
				.withExposedPorts(5432).withCopyFileToContainer(
						MountableFile.forHostPath(Paths.get("scripts", "init.sql").toAbsolutePath().toString()),
						"/docker-entrypoint-initdb.d/init.sql");
	}

	public static Connection createConnection(PostgreSQLContainer<?> postgresContainer2) {
		return new ConnectionPostgress().getConnection(
				String.format("jdbc:postgresql://localhost:%d/movietex",
						postgresContainer.getMappedPort(5432)),
				postgresContainer.getUsername(), postgresContainer.getPassword());

	}
	
	@Test
	public void testInitialData() throws Exception {
		try (Statement stmt = connection.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM movies");
			assertTrue(rs.next());
			assertTrue(rs.next());
		}
	}

	@Test
	void connectionEstablished() {
		assertTrue(postgresContainer.isCreated());
		assertTrue(postgresContainer.isRunning());
	}


}
