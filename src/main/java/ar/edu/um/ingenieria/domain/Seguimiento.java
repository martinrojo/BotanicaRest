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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="seguimientos")
public class Seguimiento implements Serializable{
	private static final long serialVersionUID = -7153249297528005760L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="planta_id")
	private Planta planta;
	
	@JsonIgnore
	@OneToMany (mappedBy = "seguimiento", fetch = FetchType.EAGER)
	private List<Etapa> etapas;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="estado_id")
	private Estado estado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss")
	@Column ( name = "ultimo_riego")
	private Date utlimo_riego;
	
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

	public Date getUltimoRiego() {
		return utlimo_riego;
	}

	public void setUltimoRiego(Date ultimoRiego) {
		this.utlimo_riego = ultimoRiego;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((planta == null) ? 0 : planta.hashCode());
		result = prime * result + ((utlimo_riego == null) ? 0 : utlimo_riego.hashCode());
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
		if (utlimo_riego == null) {
			if (other.utlimo_riego != null)
				return false;
		} else if (!utlimo_riego.equals(other.utlimo_riego))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Seguimiento [id=" + id + ", planta=" + planta + ", estado=" + estado + ", ultimoRiego=" + utlimo_riego
				+ "]";
	}

	public Seguimiento() {
		super();
	}
}
