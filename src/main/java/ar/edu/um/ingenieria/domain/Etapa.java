package ar.edu.um.ingenieria.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="etapas")
public class Etapa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2106894238610793513L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@JsonIgnore
	@OneToMany
	@JoinColumn(name="estado_id")
	private List<Estado> estado;
	
	@JsonIgnore
	@OneToMany(mappedBy = "etapa", fetch = FetchType.LAZY)
	private List<Seguimiento> seguimiento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Estado> getEstado() {
		return estado;
	}

	public void setEstado(List<Estado> estado) {
		this.estado = estado;
	}
	
	public List<Seguimiento> getSeguimiento() {
		return seguimiento;
	}

	public void setSeguimiento(List<Seguimiento> seguimiento) {
		this.seguimiento = seguimiento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((seguimiento == null) ? 0 : seguimiento.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Etapa other = (Etapa) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (seguimiento == null) {
			if (other.seguimiento != null)
				return false;
		} else if (!seguimiento.equals(other.seguimiento))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Etapa [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", tareas=" + estado
				+ ", seguimiento=" + seguimiento + "]";
	}

	public Etapa() {
		super();
	}
}
