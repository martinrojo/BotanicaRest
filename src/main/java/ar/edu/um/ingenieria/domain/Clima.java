package ar.edu.um.ingenieria.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="climas")
public class Clima implements Serializable{
	private static final long serialVersionUID = 972818578376470882L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="descriocion")
	private String descriocion;
	
	@OneToOne(mappedBy = "climas", fetch = FetchType.EAGER)
	private List<Planta> plantas;

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

	public String getDescriocion() {
		return descriocion;
	}

	public void setDescriocion(String descriocion) {
		this.descriocion = descriocion;
	}

	public List<Planta> getPlantas() {
		return plantas;
	}

	public void setPlantas(List<Planta> plantas) {
		this.plantas = plantas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void add(Planta planta) {
		plantas.add(planta);
	}
	
	public void remove(Planta planta) {
		plantas.remove(planta);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descriocion == null) ? 0 : descriocion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((plantas == null) ? 0 : plantas.hashCode());
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
		Clima other = (Clima) obj;
		if (descriocion == null) {
			if (other.descriocion != null)
				return false;
		} else if (!descriocion.equals(other.descriocion))
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
		if (plantas == null) {
			if (other.plantas != null)
				return false;
		} else if (!plantas.equals(other.plantas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Clima [id=" + id + ", nombre=" + nombre + ", descriocion=" + descriocion + ", plantas=" + plantas + "]";
	}

	public Clima() {
		super();
	}
}
