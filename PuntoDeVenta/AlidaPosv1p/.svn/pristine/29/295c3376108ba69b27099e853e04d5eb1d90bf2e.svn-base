package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the direccion database table.
 * 
 */
@Entity
@Table(name = "direccion")
@NamedQueries({
    @NamedQuery(name = "Direccion.findAll", query = "SELECT d FROM Direccion d"),
    @NamedQuery(name = "Direccion.findByIddireccion", query = "SELECT d FROM Direccion d WHERE d.iddireccion = :iddireccion"),
    @NamedQuery(name = "Direccion.findByIdmunicipio", query = "SELECT d FROM Direccion d WHERE d.municipio = :idmunicipio"),
    @NamedQuery(name = "Direccion.findByCalle", query = "SELECT d FROM Direccion d WHERE d.calle = :calle"),
    @NamedQuery(name = "Direccion.findByNumext", query = "SELECT d FROM Direccion d WHERE d.numext = :numext"),
    @NamedQuery(name = "Direccion.findByColonia", query = "SELECT d FROM Direccion d WHERE d.colonia = :colonia"),
    @NamedQuery(name = "Direccion.findByCodigopostal", query = "SELECT d FROM Direccion d WHERE d.codigopostal = :codigopostal"),
    @NamedQuery(name = "Direccion.findByNumint", query = "SELECT d FROM Direccion d WHERE d.numint = :numint")})

public class Direccion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static String FIND_ALL              = "Direccion.findAll";
	public static String FIND_BY_ID            = "Direccion.findByIddireccion";
	public static String FIND_BY_ID_MUNICIPIO  = "Direccion.findByIdmunicipio";
	public static String FIND_BY_CALLE         = "Direccion.findByCalle";
	public static String FIND_BY_NUMEXT        = "Direccion.findByNumext";
	public static String FIND_BY_COLONIA       = "Direccion.findByColonia";
	public static String FIND_BY_CODIGO_POSTAL = "Direccion.findByCodigopostal";
	public static String FIND_BY_NUMINT        = "Direccion.findByNumint";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer iddireccion;
	
	private String calle;
	
	private String codigopostal;
	
	private String colonia;
	
	private String numext;
	
	private String numint;
	
	//bi-directional many-to-many association to Contacto
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(
		name="direccioncontacto"
		, joinColumns={
			@JoinColumn(name="iddireccion", referencedColumnName="iddireccion")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idcontacto", referencedColumnName="idcontacto")
			}
		)
	private List<Contacto> contactos;
	
	//bi-directional many-to-one association to Municipio
	@ManyToOne
	@JoinColumn(name="idmunicipio")
	private Municipio municipio;

	public Direccion() {
	}
	
	/**
	 * Obtiene la dirección completa, al concatenar sus componentes
	 * @return
	 */
	public String getDireccionCompleta(){
		return this.calle + " #" + this.numext + " Int. "  +this.numint + " col. " + this.colonia + " cp. " + this.codigopostal;
	}
	
	public Integer getIddireccion() {
		if (this.iddireccion==null) {
			this.iddireccion =0;
		}
		return this.iddireccion;
	}

	public void setIddireccion(Integer iddireccion) {
		this.iddireccion = iddireccion;
	}

	public String getCalle() {
		if (this.calle==null) {
			this.calle ="";
		}
		return this.calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCodigopostal() {
		if (this.codigopostal==null) {
			this.codigopostal ="";
		}
		return this.codigopostal;
	}

	public void setCodigopostal(String codigopostal) {
		this.codigopostal = codigopostal;
	}

	public String getColonia() {
		if (this.colonia==null) {
			this.colonia ="";
		}
		return this.colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getNumext() {
		if (this.numext==null) {
			this.numext ="";
		}
		return this.numext;
	}

	public void setNumext(String numext) {
		this.numext = numext;
	}

	public String getNumint() {
		if (this.numint==null) {
			this.numint ="";
		}
		return this.numint;
	}

	public void setNumint(String numint) {
		this.numint = numint;
	}

	public List<Contacto> getContactos() {
		if (this.contactos==null) {
			this.contactos =new ArrayList<Contacto>();
		}
		return this.contactos;
	}

	public void setContactos(List<Contacto> contactos) {
		this.contactos = contactos;
	}

	public Municipio getMunicipio() {
		if (this.municipio==null) {
			this.municipio = new Municipio();
		}
		return this.municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

}