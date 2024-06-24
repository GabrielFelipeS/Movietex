package ifsp.movietex.rating.entity;

public class Rating {
	private Integer idUsuario;
	private Integer idFilme;
	private Double nota;
	private String comentario;

	public Rating(Integer idUsuario, Integer idFilme, Double nota, String comentario) {
		this.idUsuario = idUsuario;
		this.idFilme = idFilme;
		this.nota = nota;
		this.comentario = comentario;
	}

}
