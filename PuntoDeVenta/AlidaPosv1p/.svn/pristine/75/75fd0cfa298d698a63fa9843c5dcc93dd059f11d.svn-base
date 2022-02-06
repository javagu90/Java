package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the estado database table.
 * 
 */
@Entity
@Table(name = "estado")
@NamedQueries({
	@NamedQuery(name = "Estado.findAll", query="SELECT e FROM Estado e"),
	@NamedQuery(name = "Estado.findByIdestado", query = "SELECT e FROM Estado e WHERE e.idestado = :idestado"),
	@NamedQuery(name = "Estado.findByNombre", query = "SELECT e FROM Estado e WHERE e.nombre = :nombre")})
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static String FIND_ALL       = "Estado.findAll";
	public static String FIND_BY_ID     = "Estado.findByIdestado";
	public static String FIND_BY_NOMBRE = "Estado.findByNombre";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idestado;
	private String abreviatura;
	private String clave;
	private String nombre;

	//bi-directional many-to-one association to Municipio
	@OneToMany(mappedBy="estado")
	private List<Municipio> municipios;

	public Estado() {
	}

	public Integer getIdestado() {
		if (this.idestado == null) {
			this.idestado = 0;
		}
		return this.idestado;
	}

	public void setIdestado(Integer idestado) {
		this.idestado = idestado;
	}

	public String getAbreviatura() {
		if (this.abreviatura == null) {
			this.abreviatura = "";
		}
		return this.abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getClave() {
		if (this.clave == null) {
			this.clave = "";
		}
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombre() {
		if (this.nombre == null) {
			this.nombre = "";
		}
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Municipio> getMunicipios() {
		if (this.municipios == null) {
			this.municipios = new ArrayList<Municipio>();
		}
		return this.municipios;
	}

	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}

	public Municipio addMunicipio(Municipio municipio) {
		getMunicipios().add(municipio);
		municipio.setEstado(this);

		return municipio;
	}

	public Municipio removeMunicipio(Municipio municipio) {
		getMunicipios().remove(municipio);
		municipio.setEstado(null);

		return municipio;
	}

}