package ifsp.movietex.user.entity;

public class User {
	private Integer id;
	private String name;
	private String email;
	private String password;
	private boolean admin;
	
	public User( Integer id, String name, String email, String password, boolean admin) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.admin = admin;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return name;
	}

	public boolean isAdmin() {
		return admin;
	}

	public String getSenha() {
		return password;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name =" + name + ", email= " + email + "admin= " + admin + "]" ;
	}
	
}
