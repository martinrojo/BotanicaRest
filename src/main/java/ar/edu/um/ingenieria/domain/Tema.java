package ar.edu.um.ingenieria.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="temas")
public class Tema implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -145486320173528095L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@OneToMany(mappedBy = "tema", fetch = FetchType.EAGER)
	private List<Pregunta> preguntas;
	
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

	public List<Pregunta> getPregunta() {
		return preguntas;
	}

	public void setPregunta(List<Pregunta> pregunta) {
		this.preguntas = pregunta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void add(Pregunta pregunta) {
		this.preguntas.add(pregunta);
	}
	
	public void remove(Pregunta pregunta) {
		this.preguntas.remove(pregunta);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((preguntas == null) ? 0 : preguntas.hashCode());
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
		Tema other = (Tema) obj;
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
		if (preguntas == null) {
			if (other.preguntas != null)
				return false;
		} else if (!preguntas.equals(other.preguntas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tema [id=" + id + ", nombre=" + nombre + ", pregunta=" + preguntas + "]";
	}

	public Tema() {
		super();
	}
}
