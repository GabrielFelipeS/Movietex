package ifsp.movietex.rating.entity;

public class Rating {
	private Integer idUSer;
	private String name;
	private Integer idFilme;
	private Double nota;
	private String comment;

	public Rating(Integer idUSer, String name, Integer idFilme, Double nota, String comment) {
		this.idUSer = idUSer;
		this.name = name;
		this.idFilme = idFilme;
		this.nota = nota;
		this.comment = comment;
	}

	public Integer getIdUSer() {
		return idUSer;
	}

	public String getName() {
		return name;
	}

	public Integer getIdFilme() {
		return idFilme;
	}

	public Double getNota() {
		return nota;
	}

	public String getComment() {
		return comment;
	}

}
