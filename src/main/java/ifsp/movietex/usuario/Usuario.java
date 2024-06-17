package ifsp.movietex.usuario;

public class Usuario {
	private Long id;
	private String nome;
	private String email;
	private String senha;
	private boolean admin;
	
	public Usuario(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.admin = false;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public boolean isAdmin() {
		return admin;
	}

	public String getSenha() {
		return senha;
	}

	public Long getId() {
		return id;
	}

}
