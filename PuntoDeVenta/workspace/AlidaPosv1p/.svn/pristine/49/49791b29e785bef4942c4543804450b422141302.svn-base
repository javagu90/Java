package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the contacto database table.
 * 
 */
@Entity
@Table(name = "contacto")
@NamedQueries({
    @NamedQuery(name = "Contacto.findAll", query = "SELECT c FROM Contacto c"),
    @NamedQuery(name = "Contacto.findByIdcontacto", query = "SELECT c FROM Contacto c WHERE c.idcontacto = :idcontacto"),
    @NamedQuery(name = "Contacto.findByFechaalta", query = "SELECT c FROM Contacto c WHERE c.fechaalta = :fechaalta"),
    @NamedQuery(name = "Contacto.findByTelefono1", query = "SELECT c FROM Contacto c WHERE c.telefono1 = :telefono1"),
    @NamedQuery(name = "Contacto.findByTelefono2", query = "SELECT c FROM Contacto c WHERE c.telefono2 = :telefono2"),
    @NamedQuery(name = "Contacto.findByCorreoelectronico", query = "SELECT c FROM Contacto c WHERE c.correoelectronico = :correoelectronico"),
    @NamedQuery(name = "Contacto.findByComentarios", query = "SELECT c FROM Contacto c WHERE c.comentarios = :comentarios")})
public class Contacto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static String FIND_ALL           = "Contacto.findAll";
	public static String FIND_BY_ID         = "Contacto.findByIdcontacto";
	public static String FIND_BY_FECHA_ALTA = "Contacto.findByFechaalta";
	public static String FIND_BY_TELEFONO1  = "Contacto.findByTelefono1";
	public static String FIND_BY_TELEFONO2  = "Contacto.findByTelefono2";
	public static String FIND_BY_CORREO     = "Contacto.findByCorreoelectronico";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idcontacto;
	
	private String comentarios;
	
	private String correoelectronico;
	
	@Temporal(TemporalType.DATE)
	private Date fechaalta;
	
	private String telefono1;
	
	private String telefono2;
	
	//bi-directional many-to-many association to Direccion
	@ManyToMany(mappedBy="contactos", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Direccion> direcciones;
	
	//bi-directional many-to-one association to Persona
	@OneToMany(mappedBy="contacto")
	private List<Persona> personas;
	
	//bi-directional many-to-one association to Tienda
	@OneToMany(mappedBy="contacto")
	private List<Tienda> tiendas;

	public Contacto() {
	}
	
	public Integer getIdcontacto() {
		if (this.idcontacto==null) {
			this.idcontacto=0;
		}
		return this.idcontacto;
	}

	public void setIdcontacto(Integer idcontacto) {
		this.idcontacto = idcontacto;
	}

	public String getComentarios() {
		if (this.comentarios == null) {
			this.comentarios = "";
		}
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getCorreoelectronico() {
		if (this.correoelectronico == null) {
			this.correoelectronico = "";
		}
		return this.correoelectronico;
	}

	public void setCorreoelectronico(String correoelectronico) {
		this.correoelectronico = correoelectronico;
	}

	public Date getFechaalta() {
		if (this.fechaalta == null) {
			this.fechaalta = new java.util.Date();
		}
		return this.fechaalta;
	}

	public void setFechaalta(Date fechaalta) {
		this.fechaalta = fechaalta;
	}

	public String getTelefono1() {
		if (this.telefono1 == null) {
			this.telefono1 = "";
		}
		return this.telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		if (this.telefono2 == null) {
			this.telefono2 = "";
		}
		return this.telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public List<Direccion> getDirecciones() {
		if (this.direcciones == null) {
			this.direcciones = new ArrayList<Direccion>();
		}
		return this.direcciones;
	}

	public void setDirecciones(List<Direccion> direcciones) {
		this.direcciones = direcciones;
	}

	public List<Persona> getPersonas() {
		if (this.personas == null) {
			this.personas = new ArrayList<Persona>();
		}
		return this.personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	public Persona addPersona(Persona persona) {
		getPersonas().add(persona);
		persona.setContacto(this);

		return persona;
	}

	public Persona removePersona(Persona persona) {
		getPersonas().remove(persona);
		persona.setContacto(null);

		return persona;
	}
	
	public List<Tienda> getTiendas() {
		if (this.tiendas == null) {
			this.tiendas = new ArrayList<Tienda>();
		}
		return this.tiendas;
	}

	public void setTiendas(List<Tienda> tiendas) {
		this.tiendas = tiendas;
	}

	public Tienda addTienda(Tienda tienda) {
		getTiendas().add(tienda);
		tienda.setContacto(this);

		return tienda;
	}

	public Tienda removeTienda(Tienda tienda) {
		getTiendas().remove(tienda);
		tienda.setContacto(null);

		return tienda;
	}

}