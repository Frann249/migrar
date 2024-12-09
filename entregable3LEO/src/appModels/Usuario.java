package appModels;

public class Usuario {
	private int id;
	private Persona persona;
	private String email;
	private String password;
	public Usuario(int id, Persona persona, String email, String password) {
		this.id = id;
		this.persona= persona;
		this.email = email;
		this.password = password;
	}
	public Usuario(Persona persona, String email, String password) {
		this.persona= persona;
		this.email = email;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String eMail) {
		this.email = eMail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
}
