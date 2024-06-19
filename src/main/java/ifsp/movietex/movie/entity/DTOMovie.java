package ifsp.movietex.movie.entity;

public record DTOMovie(Integer id, String title, String description, String genre, String director, Integer year) {
	public DTOMovie(String title, String description, String genre, String director, Integer year) {
		this(null, title, description, genre, director, year);
	}
}
