package ar.edu.um.ingenieria.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="seguimiento")
public class Seguimiento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7153249297528005760L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column (name ="id_usuario")
	private Usuario usuario;
	@Column (name ="id_planta")
	private Planta planta;
	@OneToOne (mappedBy = "seguimiento", fetch = FetchType.EAGER)
	List<Etapa> etapas;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_estado")
	private Estado estado;
	private Date ultimoRiego;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Planta getPlanta() {
		return planta;
	}
	public void setPlanta(Planta planta) {
		this.planta = planta;
	}
	
	public List<Etapa> getEtapas() {
		return etapas;
	}
	public void setEtapas(List<Etapa> etapas) {
		this.etapas = etapas;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Date getultimoRiego() {
		return ultimoRiego;
	}
	public void setultimoRiego(Date ultimoRiego) {
		this.ultimoRiego = ultimoRiego;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void add (Etapa o) {
		this.etapas.add(o);
	}
	public void remove (Etapa o) {
		this.etapas.remove(o);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((etapas == null) ? 0 : etapas.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((planta == null) ? 0 : planta.hashCode());
		result = prime * result + ((ultimoRiego == null) ? 0 : ultimoRiego.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Seguimiento other = (Seguimiento) obj;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (etapas == null) {
			if (other.etapas != null)
				return false;
		} else if (!etapas.equals(other.etapas))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (planta == null) {
			if (other.planta != null)
				return false;
		} else if (!planta.equals(other.planta))
			return false;
		if (ultimoRiego == null) {
			if (other.ultimoRiego != null)
				return false;
		} else if (!ultimoRiego.equals(other.ultimoRiego))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Seguimiento [id=" + id + ", usuario=" + usuario + ", planta=" + planta + ", etapa=" + etapas
				+ ", estado=" + estado + ", ultimoRiego=" + ultimoRiego + "]";
	}
}
