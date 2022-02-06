package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the municipio database table.
 * 
 */
@Entity
@Table(name = "municipio")
@NamedQueries({
    @NamedQuery(name = "Municipio.findAll", query = "SELECT m FROM Municipio m"),
    @NamedQuery(name = "Municipio.findByIdmunicipio", query = "SELECT m FROM Municipio m WHERE m.idmunicipio = :idmunicipio"),
    @NamedQuery(name = "Municipio.findByIdestado", query = "SELECT m FROM Municipio m WHERE m.estado.idestado = :idestado"),
    @NamedQuery(name = "Municipio.findByNombe", query = "SELECT m FROM Municipio m WHERE m.nombre = :nombre AND m.estado = :idestado")})
public class Municipio implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static String FIND_ALL          = "Municipio.findAll";
	public static String FIND_BY_ID        = "Municipio.findByIdmunicipio";
	public static String FIND_BY_ID_ESTADO = "Municipio.findByIdestado";
	public static String FIND_BY_NOMBRE    = "Municipio.findByNombe";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idmunicipio;
	private String clave;
	private String nombre;
	private String sigla;
	//bi-directional many-to-one association to Direccion
	@OneToMany(mappedBy="municipio")
	private List<Direccion> direcciones;
	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="idestado")
	private Estado estado;

	public Municipio() {
	}

	public Integer getIdmunicipio() {
		if (this.idmunicipio==null) {
			this.idmunicipio = 0;
		}
		return this.idmunicipio;
	}

	public void setIdmunicipio(Integer idmunicipio) {
		this.idmunicipio = idmunicipio;
	}

	public String getClave() {
		if (this.clave==null) {
			this.clave = "";
		}
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombre() {
		if (this.nombre==null) {
			this.nombre = "";
		}
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSigla() {
		if (this.sigla==null) {
			this.sigla = "";
		}
		return this.sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public List<Direccion> getDireccions() {
		if (this.direcciones == null) {
			this.direcciones = new ArrayList<Direccion>();
		}		
		return this.direcciones;
	}

	public void setDireccions(List<Direccion> direccions) {
		this.direcciones = direccions;
	}

	public Direccion addDireccion(Direccion direccion) {
		getDireccions().add(direccion);
		direccion.setMunicipio(this);

		return direccion;
	}

	public Direccion removeDireccion(Direccion direccion) {
		getDireccions().remove(direccion);
		direccion.setMunicipio(null);

		return direccion;
	}

	public Estado getEstado() {
		if (this.estado==null) {
			this.estado = new Estado();
		}
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}