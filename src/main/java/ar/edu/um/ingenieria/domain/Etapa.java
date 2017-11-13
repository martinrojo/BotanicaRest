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
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	
	@OneToMany (mappedBy = "etapa", fetch = FetchType.LAZY)
	private List<Tarea> tareas;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="seguimiento_id")
	private Seguimiento seguimiento;

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

	public List<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}

	public void addTareas(Tarea tarea)
	{
		this.tareas.add(tarea);
	}
	
	public void removeTareas(Tarea tarea)
	{
		this.tareas.remove(tarea);
	}
	public void add (Etapa o) {
		this.seguimiento.add(o);
	}
	public void remove (Etapa o) {
		this.seguimiento.remove(o);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void add (Tarea o) {
		this.tareas.add(o);
	}
	public void remove (Tarea o) {
		this.tareas.remove(o);
	}
	

	@Override
	public String toString() {
		return "Etapa [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", tareas=" + tareas
				+ ", seguimiento=" + seguimiento + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((seguimiento == null) ? 0 : seguimiento.hashCode());
		result = prime * result + ((tareas == null) ? 0 : tareas.hashCode());
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
		if (tareas == null) {
			if (other.tareas != null)
				return false;
		} else if (!tareas.equals(other.tareas))
			return false;
		return true;
	}

	public Etapa() {
		super();
	}
	
	
}
