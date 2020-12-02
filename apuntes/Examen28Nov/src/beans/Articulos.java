package beans;
// Generated 30-nov-2020 13:41:25 by Hibernate Tools 5.2.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Articulos generated by hbm2java
 */
@Entity
@Table(name = "articulos", catalog = "examencorchero")
public class Articulos implements java.io.Serializable {

	private Integer codigo;
	private String dni;
	private String concepto;
	private Integer precio;

	public Articulos() {
	}

	public Articulos(String dni, String concepto, Integer precio) {
		this.dni = dni;
		this.concepto = concepto;
		this.precio = precio;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "CODIGO", unique = true, nullable = false)
	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	@Column(name = "DNI", length = 10)
	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Column(name = "CONCEPTO", length = 20)
	public String getConcepto() {
		return this.concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	@Column(name = "PRECIO")
	public Integer getPrecio() {
		return this.precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

}
