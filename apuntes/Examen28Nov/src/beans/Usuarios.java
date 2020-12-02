package beans;
// Generated 30-nov-2020 13:41:25 by Hibernate Tools 5.2.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Usuarios generated by hbm2java
 */
@Entity
@Table(name = "usuarios", catalog = "examencorchero")
public class Usuarios implements java.io.Serializable {

	private String dni;
	private String login;
	private String clave;

	public Usuarios() {
	}

	public Usuarios(String dni) {
		this.dni = dni;
	}

	public Usuarios(String dni, String login, String clave) {
		this.dni = dni;
		this.login = login;
		this.clave = clave;
	}

	@Id

	@Column(name = "DNI", unique = true, nullable = false, length = 10)
	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Column(name = "LOGIN", length = 20)
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "CLAVE", length = 20)
	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

}