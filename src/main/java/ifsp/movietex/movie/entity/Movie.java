package ifsp.movietex.movie.entity;

public class Movie {
	private Integer id;
	private String title;
	private String description;
	private String director;
	private String genre;
	private Integer year;
	private Double ratingAverage;

	public Movie(Integer id, String title, String description, String director, String genre, Integer year, Double ratingAverage) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.director = director;
		this.genre = genre;
		this.year = year;
		this.ratingAverage = ratingAverage;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", director=" + director + ", genre=" + genre + ", year=" + year
				+ ", ratingAverage=" + ratingAverage + "]";
	}
}
