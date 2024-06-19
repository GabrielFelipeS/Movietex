package ifsp.movietex.movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ifsp.movietex.movie.entity.Movie;

public class MovieDAO {
	private static final Logger logger = LoggerFactory.getLogger(MovieDAO.class);
	 
	private Connection conn;

	public MovieDAO(Connection conn) {
		this.conn = conn;
	}

	public String insert(String title, String description, String genre, String director, Integer year) {
		try(PreparedStatement ps = conn.prepareStatement("INSERT INTO movies (title, description, director, genre, year) VALUES (?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, title);
			ps.setString(2, description);
			ps.setString(3, genre);
			ps.setString(4, director);
			ps.setInt(5, year);
			
			int updatedRows = ps.executeUpdate();
			System.out.println(ps.getGeneratedKeys());
			
			return String.format("Sucesso ao cadastrar o filme %s de id: %d", title, ps.getGeneratedKeys());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return String.format("Falha ao cadastrar o filme %s", title);
		}
	}

	public Movie findBy(Integer id) {
		return null;
	}
	
	public List<Movie> findBy(String title, String description, String genre, String director, Integer year, Double ratingAverage) {
		return findBy(title, description, genre, director, year, ratingAverage, ratingAverage);
	}

	public List<Movie> findBy(String title, String description, String genre, String director, Integer year, Double minRatingAverage, Double maxRatingAverage) {
		List<Movie> movies = new LinkedList<>();
		String sql = generateSelectQuery(title, genre, director, year, minRatingAverage, maxRatingAverage);
		try(PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
			prepareStatementSelect(pstmt, title, genre, director, year, minRatingAverage, maxRatingAverage);
	
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Movie movie = new Movie(rs.getInt("id"), rs.getString("title"), rs.getString("description"), rs.getString("director"), rs.getString("genre"), rs.getInt("year"), rs.getDouble("rating_average"));
				movies.add(movie);
			}
		} catch(SQLException e) {
			logger.error("Falha ao buscar movie", e);
		}
		
		return movies;
	}

	private void prepareStatementSelect(PreparedStatement pstmt, String title, String genre, String director, Integer year,
			Double minRatingAverage, Double maxRatingAverage) throws SQLException {
		int parameterIndex = 1;
		if (title != null) {
			pstmt.setString(parameterIndex++, title);
		}
		
		if (director != null) {
			pstmt.setString(parameterIndex++, director);
		}
		
		if (genre != null) {
			pstmt.setString(parameterIndex++, genre);
		}
		
		if (year != null) {
			pstmt.setInt(parameterIndex++, year);
		}
		
		if (minRatingAverage != null) {
			pstmt.setDouble(parameterIndex++, minRatingAverage);
		}
		
		if (maxRatingAverage != null) {
			pstmt.setDouble(parameterIndex++, maxRatingAverage);
		}
	}

	private String generateSelectQuery(String title, String genre, String director, Integer year,
			Double minRatingAverage, Double maxRatingAverage) {
		StringBuilder builder = new StringBuilder("SELECT id, title, director, genre, year, rating_average FROM Movies WHERE 1=1");

		if (title != null)
			builder.append(" AND title = ?");
		if (genre != null)
			builder.append(" AND genre = ?");
		if (director != null)
			builder.append(" AND director = ?");
		if (year != null)
			builder.append(" AND year = ?");
		if (minRatingAverage != null)
			builder.append(" AND rating_average >= ?");
		if (maxRatingAverage != null)
			builder.append(" AND rating_average <= ?");

		return builder.toString();
	}

}
