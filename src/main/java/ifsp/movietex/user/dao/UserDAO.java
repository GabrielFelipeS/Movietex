package ifsp.movietex.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ifsp.movietex.user.entity.DTOUser;

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

	public boolean login(DTOUser user) {
		String SQL = "SELECT * FROM " + table + " WHERE email = ? AND password = ?"; 
		try(PreparedStatement statement = conn.prepareStatement(SQL)) {
			statement.setString(1, user.email());
			statement.setString(2, user.password());
			ResultSet resultSet =statement.executeQuery();
			
			return resultSet.next();
			
		} catch (Exception e) {
			System.out.println("Erro no login");
			return false;
		}
		
	}
	
	public boolean updateUser(DTOUser user) {
		String SQL = "UPDATE" + table + "SET nome = ?, email = ?, password = ?, isAdmin=? where id = ?"; 

		try(PreparedStatement statement = conn.prepareStatement(SQL)) {
			statement.setString(1, user.name());
			statement.setString(2, user.email());
			statement.setString(3, user.password());
			statement.setInt(4, user.id());
			
			statement.execute();
			
			return true;			
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public boolean deleteUser(DTOUser user) {
		String SQL = "DELETE FROM" + table + "WHERE id = ?"; 
		try(PreparedStatement statement = conn.prepareStatement(SQL)) {
			statement.setInt(1, user.id());
			return true;
		} catch (Exception e) {
			return false;
		}

	}
}
