package ifsp.movietex.movie.dao;

import java.sql.Connection;

import ifsp.movietex.movie.entity.Movie;

public class MovieDAO {
	private Connection connection;

	public MovieDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void insert() {
		
	}
	
	public Movie findBy(Integer id) {
		return null;
	}
}
