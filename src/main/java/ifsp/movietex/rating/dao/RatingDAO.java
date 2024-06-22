package ifsp.movietex.rating.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ifsp.movietex.movie.dao.MovieDAO;
import ifsp.movietex.rating.entity.Rating;

public class RatingDAO {
	private static final Logger logger = LoggerFactory.getLogger(MovieDAO.class);

	private Connection conn;

	public RatingDAO(Connection conn) {
		this.conn = conn;
	}

	public String insert(Integer idFilme, Integer idUsuario, Double nota, String comentario) {
		try (PreparedStatement ps = conn.prepareStatement(
				"INSERT INTO Assessments (id_movie, id_user, rating, comment) VALUES (?, ?, ?, ?)",
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, idFilme);
			ps.setInt(2, idUsuario);
			ps.setDouble(3, nota);
			ps.setString(4, comentario);

			int updatedRows = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (!rs.next() || updatedRows != 1)
				throw new SQLException("Falha na inserção do filme");

			return "Avaliação enviada com sucesso!";
		} catch (Exception e) {
			logger.error(e.getMessage());
			return String.format("Falha ao enviar a avaliação!");
		}
	}

	public List<Rating> findBy(Integer idFilme) {
		List<Rating> ratings = new LinkedList<>();
		try (PreparedStatement ps = conn.prepareStatement(
				"SELECT id_user, id_movie, comment, rating FROM Assessments WHERE id_movie = ?")) {
			ps.setInt(1, idFilme);

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Rating rating = new Rating(rs.getInt("id_user"), rs.getInt("id_movie"), rs.getDouble("rating") , rs.getString("comment"));

				ratings.add(rating);
			}

			return ratings;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return ratings;
	}

}
