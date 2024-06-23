package ifsp.movietex.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ifsp.movietex.user.entity.DTOUser;
import ifsp.movietex.user.entity.User;

public class UserDAO {
	
	private Connection conn;
	private String table = "Users";
	private String fieldNames = "(name, email, password)";
	
	
	public UserDAO(Connection conn) {
		this.conn = conn;
	}

	public boolean register(DTOUser user) {
		String SQL = "insert into " + table + " " + fieldNames + " values (?,?,?)"; 

		try(PreparedStatement statement = conn.prepareStatement(SQL)) {
				statement.setString(1, user.name());
				statement.setString(2, user.email());
				statement.setString(3, user.password());
				
				statement.executeUpdate();
				
				
				return true;
				
		} catch (Exception e) {
			return false;
		}
		
	}

	public String login(DTOUser user) {
		String SQL = "SELECT * FROM " + table + " WHERE email = ? AND password = ?"; 
		try(PreparedStatement statement = conn.prepareStatement(SQL)) {
			statement.setString(1, user.email());
			statement.setString(2, user.password());
			ResultSet resultSet =statement.executeQuery();
			
			if(resultSet.next()) {
				return resultSet.getBoolean("isAdmin") ? "admin" : "user";
			}else {
				return null;
			}
			
		} catch (Exception e) {
			System.out.println("Erro no login");
			return null;
		}
		
	}
	
	public boolean updateUser(User user) {
		String SQL = "UPDATE" + table + "SET nome = ?, email = ?, password = ?, isAdmin=? where id = ?"; 

		try(PreparedStatement statement = conn.prepareStatement(SQL)) {
			statement.setString(1, user.getNome());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getSenha());
			statement.setInt(4, user.getId());
			
			statement.execute();
			
			return true;			
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public boolean deleteUser(Integer id) {
		String SQL = "DELETE FROM" + table + "WHERE id = ?"; 
		try(PreparedStatement statement = conn.prepareStatement(SQL)) {
			statement.setInt(1, id);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
}
