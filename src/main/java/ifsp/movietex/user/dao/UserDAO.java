package ifsp.movietex.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ifsp.movietex.movie.dao.MovieDAO;
import ifsp.movietex.user.entity.DTOUser;
import ifsp.movietex.user.entity.User;

public class UserDAO {
	private static final Logger logger = LoggerFactory.getLogger(MovieDAO.class);

	private Connection conn;
	private String table = "Users";
	private String fieldNames = "(name, email, password)";

	public UserDAO(Connection conn) {
		this.conn = conn;
	}

	public boolean register(DTOUser user) {
		String SQL = "insert into " + table + " " + fieldNames + " values (?,?,?)";

		try (PreparedStatement statement = conn.prepareStatement(SQL)) {
			statement.setString(1, user.name());
			statement.setString(2, user.email());
			statement.setString(3, user.password());

			statement.executeUpdate();

			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}

	}

	public User login(DTOUser user) {
		String SQL = "SELECT * FROM " + table + " WHERE email = ? AND password = ?";
		try (PreparedStatement statement = conn.prepareStatement(SQL)) {
			statement.setString(1, user.email());
			statement.setString(2, user.password());
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				System.out.println(rs.getBoolean("isAdmin"));
				return new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"),
						rs.getBoolean("isAdmin"));
			} else {
				throw new RuntimeException("Login invalido");
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}

	}

	public boolean updateUser(User user) {
		String SQL = "UPDATE " + table + " SET name = ?, email = ?, password = ?, isAdmin=? where id = ?";

		try (PreparedStatement statement = conn.prepareStatement(SQL)) {
			statement.setString(1, user.getNome());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getSenha());
			statement.setBoolean(4, user.isAdmin());
			statement.setInt(5, user.getId());

			Integer modifyRolls = statement.executeUpdate();
			Boolean updateSuccess = modifyRolls == 1;
			return updateSuccess;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}

	}

	public boolean deleteUser(Integer id) {
		String SQL = "DELETE FROM " + table + " WHERE id = ?";
		try (PreparedStatement statement = conn.prepareStatement(SQL)) {
			statement.setInt(1, id);
			Integer modifyRolls = statement.executeUpdate();
			Boolean deleteSuccess = modifyRolls == 1;
			return deleteSuccess;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}

	}
}
