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

	public Movie(Integer id, String title, String description, String director, String genre, String duration,
				 Integer year, Double ratingAverage, String poster) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.director = director;
		this.genre = genre;
		this.duration = duration;
		this.year = year;
		this.ratingAverage = ratingAverage;
		this.poster = poster;
	}

	public String getPoster() {
		return this.poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getDuration() {
		return this.duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDirector() {
		return this.director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Double getRatingAverage() {
		return this.ratingAverage;
	}

	public void setRatingAverage(Double ratingAverage) {
		this.ratingAverage = ratingAverage;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", description=" + description + ", director=" + director
				+ ", genre=" + genre + ", year=" + year + ", ratingAverage=" + ratingAverage + "]";
	}
}
