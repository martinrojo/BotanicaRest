package ar.edu.um.ingenieria.domain;

import java.io.Serializable;
import java.sql.Date;

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
@Table(name = "plantas")
public class Planta implements Serializable {

	private static final long serialVersionUID = 7302378891606112641L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "descripcion")
	private String descripcion;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_id")
	private Tipo tipo;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "temporada_id")
	private Temporada temporada;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "suelo_id")
	private Suelo suelo;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clima_id")
	private Clima clima;

	@Column(name = "tiempoRiego")
	private Date tiempoRiego;

	@Override
	public String toString() {
		return "Planta [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", tipo=" + tipo
				+ ", temporada=" + temporada + ", suelo=" + suelo + ", clima=" + clima + ", tiempoRiego=" + tiempoRiego
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clima == null) ? 0 : clima.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((suelo == null) ? 0 : suelo.hashCode());
		result = prime * result + ((temporada == null) ? 0 : temporada.hashCode());
		result = prime * result + ((tiempoRiego == null) ? 0 : tiempoRiego.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Planta other = (Planta) obj;
		if (clima == null) {
			if (other.clima != null)
				return false;
		} else if (!clima.equals(other.clima))
			return false;
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
		if (suelo == null) {
			if (other.suelo != null)
				return false;
		} else if (!suelo.equals(other.suelo))
			return false;
		if (temporada == null) {
			if (other.temporada != null)
				return false;
		} else if (!temporada.equals(other.temporada))
			return false;
		if (tiempoRiego == null) {
			if (other.tiempoRiego != null)
				return false;
		} else if (!tiempoRiego.equals(other.tiempoRiego))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

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

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Temporada getTemporada() {
		return temporada;
	}

	public void setTemporada(Temporada temporada) {
		this.temporada = temporada;
	}

	public Suelo getSuelo() {
		return suelo;
	}

	public void setSuelo(Suelo suelo) {
		this.suelo = suelo;
	}

	public Clima getClima() {
		return clima;
	}

	public void setClima(Clima clima) {
		this.clima = clima;
	}

	public Date getTiempoRiego() {
		return tiempoRiego;
	}

	public void setTiempoRiego(Date tiempoRiego) {
		this.tiempoRiego = tiempoRiego;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Planta() {
		super();
	}
}
