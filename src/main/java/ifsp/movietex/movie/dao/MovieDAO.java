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

	public void insert() {

	}

	public Movie findBy(Integer id) {
		return null;
	}

	public List<Movie> findBy(String title, String genre, String director, Integer year, Double ratingAverage) {
		List<Movie> movies = new LinkedList<>();
		String sql = generateSelectQuery(title, genre, director, year, ratingAverage);

		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			prepareStatement(pstmt, title, genre, director, year, ratingAverage);
	
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Movie movie = new Movie(rs.getInt("id"), rs.getString("title"), rs.getString("director"), rs.getString("genre"), rs.getInt("year"), rs.getDouble("ratingAverage"));
				movies.add(movie);
			}
		} catch(SQLException e) {
			logger.error("Falha ao buscar movie", e);
		}
		
		return movies;
	}

	private void prepareStatement(PreparedStatement pstmt, String title, String genre, String director, Integer year,
			Double ratingAverage) throws SQLException {
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
		
		if (ratingAverage != null) {
			pstmt.setDouble(parameterIndex++, ratingAverage);
		}
	}

	private String generateSelectQuery(String title, String genre, String director, Integer year,
			Double ratingAverage) {
		StringBuilder builder = new StringBuilder("SELECT title, director, genre, year, rating FROM Movies WHERE 1=1");

		if (title != null)
			builder.append(" AND title = ?");
		if (genre != null)
			builder.append(" AND genre = ?");
		if (director != null)
			builder.append(" AND director = ?");
		if (year != null)
			builder.append(" AND year = ?");
		if (ratingAverage != null)
			builder.append(" AND rating = ?");

		return builder.toString();
	}

}
