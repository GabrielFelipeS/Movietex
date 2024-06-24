package ifsp.movietex.rating.entity;

public class Rating {
	private String usuario;
	private Integer idFilme;
	private Double nota;
	private String comentario;

	public Rating(String usuario, Integer idFilme, Double nota, String comentario) {
		this.usuario = usuario;
		this.idFilme = idFilme;
		this.nota = nota;
		this.comentario = comentario;
	}

	public String getUsuario() {
		return usuario;
	}

	public Integer getIdFilme() {
		return idFilme;
	}

	public Double getNota() {
		return nota;
	}

	public String getComentario() {
		return comentario;
	}

}
