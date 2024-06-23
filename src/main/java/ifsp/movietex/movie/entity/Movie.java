package ifsp.movietex.movie.entity;

public class Movie {
	private Integer id;
	private String title;
	private String description;
	private String director;
	private String genre;
	private String duration;
	private Integer year;
	private Double ratingAverage;
	private String poster;

	public Movie(Integer id, String title, String description, String director, String genre, String duration, Integer year,
			Double ratingAverage, String poster) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.director = director;
		this.duration = duration;
		this.genre = genre;
		this.year = year;
		this.ratingAverage = ratingAverage;
		this.poster = poster;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", description=" + description + ", director=" + director
				+ ", genre=" + genre + ", year=" + year + ", ratingAverage=" + ratingAverage + "]";
	}
}
