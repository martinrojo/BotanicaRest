package ar.edu.um.ingenieria.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
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
@Table(name="estados")
public class Estado implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8971003862132413479L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@OneToMany
	@JoinColumn(name = "etapa_id")
	private List<Etapa> etapa;
	
	@JsonIgnore
	@OneToMany(mappedBy="estado",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

	public List<Etapa> getEtapa() {
		return etapa;
	}

	public void setEtapa(List<Etapa> etapa) {
		this.etapa = etapa;
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
		result = prime * result + ((etapa == null) ? 0 : etapa.hashCode());
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
		Estado other = (Estado) obj;
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
		if (etapa == null) {
			if (other.etapa != null)
				return false;
		} else if (!etapa.equals(other.etapa))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Estado [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", etapa=" + etapa
				+ ", seguimiento=" + seguimiento + "]";
	}

	public Estado() {
		super();
	}	 
}
