package ifsp.movietex.movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ifsp.movietex.movie.entity.DTOMovie;
import ifsp.movietex.movie.entity.Movie;

public class MovieDAO {
	private static final Logger logger = LoggerFactory.getLogger(MovieDAO.class);
	 
	private Connection conn;

	public MovieDAO(Connection conn) {
		this.conn = conn;
	}
	
	public String insert(DTOMovie dto) {
		try(PreparedStatement ps = conn.prepareStatement("INSERT INTO movies (title, description, director, genre, year) VALUES (?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, dto.title());
			ps.setString(2, dto.description());
			ps.setString(3, dto.genre());
			ps.setString(4, dto.director());
			ps.setInt(5, dto.year());
			
			int updatedRows = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(!rs.next() || updatedRows != 1)
				throw new Exception("Falha na inserção do filme");
			
			return String.format("Sucesso ao cadastrar o filme %s de id: %d", dto.title(), rs.getInt("id"));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return String.format("Falha ao cadastrar o filme %s", dto.title());
		}
	}

	public Movie findBy(Integer id) {
		try(PreparedStatement pstmt = conn.prepareStatement("SELECT id, title, description, director, genre, year, rating_average FROM Movies WHERE id = ?", PreparedStatement.RETURN_GENERATED_KEYS)){
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Movie movie = new Movie(rs.getInt("id"), rs.getString("title"), rs.getString("description"), rs.getString("director"), rs.getString("genre"), rs.getInt("year"), rs.getDouble("rating_average"));
				return movie;
			}
		} catch(SQLException e) {
			logger.error("Falha ao buscar movie", e);
		}
		
		return null;
	}
	
	public Boolean deleteBy(Integer id) {
		return null;
	}

	public String update(DTOMovie dto) {
		try (PreparedStatement ps = conn
				.prepareStatement("UPDATE movies SET title = ?, description = ?, director = ?, genre = ?, year = ? WHERE id = ? ")) {
			ps.setString(1, dto.title());
			ps.setString(2, dto.description());
			ps.setString(3, dto.genre());
			ps.setString(4, dto.director());
			ps.setInt(5, dto.year());
			ps.setInt(6, dto.id());

			int affectedRows = ps.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Falha na atualização da conta, nenhuma linha afetada.");
			}
			
			return String.format("%s atualizado com sucesso", dto.title());
		} catch (SQLException e) {
			logger.error(e.getMessage());
			return String.format("Falha na atualização do filme: %s", dto.title());
		}

	}
	
	public List<Movie> findBy(String title, String description, String genre, String director, Integer year, Double ratingAverage) {
		return findBy(title, description, genre, director, year, ratingAverage, ratingAverage);
	}

	public List<Movie> findBy(String title, String description, String genre, String director, Integer year, Double minRatingAverage, Double maxRatingAverage) {
		List<Movie> movies = new LinkedList<>();
		String sql = generateSelectQuery(title, description, genre, director, year, minRatingAverage, maxRatingAverage);
		try(PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
			prepareStatementSelect(pstmt, title, description, genre, director, year, minRatingAverage, maxRatingAverage);

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

	private void prepareStatementSelect(PreparedStatement pstmt, String title, String description, String genre, String director, Integer year,
			Double minRatingAverage, Double maxRatingAverage) throws SQLException {
		int parameterIndex = 1;
		if (title != null) {
			pstmt.setString(parameterIndex++, title);
		}
		
		if (description != null) {
			pstmt.setString(parameterIndex++, description);
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

	private String generateSelectQuery(String title, String description, String genre, String director, Integer year,
			Double minRatingAverage, Double maxRatingAverage) {
		StringBuilder builder = new StringBuilder("SELECT id, title, description, director, genre, year, rating_average FROM Movies WHERE 1=1");

		if (title != null)
			builder.append(" AND title LIKE ?");
		if (description != null)
			builder.append(" AND description LIKE ?");
		if (genre != null)
			builder.append(" AND genre = ?");
		if (director != null)
			builder.append(" AND director LIKE ?");
		if (year != null)
			builder.append(" AND year = ?");
		if (minRatingAverage != null)
			builder.append(" AND rating_average >= ?");
		if (maxRatingAverage != null) 
			builder.append(" AND rating_average <= ?");

		return builder.toString();
	}



}
