package ifsp.movietex.movie.entity;

public record DTOMovie(Integer id, String title, String description, String genre, String director,Integer year,  Integer duration,  String poster) {
	public DTOMovie(String title, String description, String genre,  String director,  Integer year, Integer duration, String poster) {
		this(null, title, description, genre, director,  year, duration, poster);
	}
}
