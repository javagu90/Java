package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the empleado database table.
 * 
 */
@Entity
@Table(name = "empleado")
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e"),
    @NamedQuery(name = "Empleado.findByIdempleado", query = "SELECT e FROM Empleado e WHERE e.idempleado = :idempleado"),
    @NamedQuery(name = "Empleado.findByActivo", query = "SELECT e FROM Empleado e WHERE e.activo = :activo"),
    @NamedQuery(name = "Empleado.findByIdpersona", query = "SELECT e FROM Empleado e WHERE e.persona = :idpersona"),
    @NamedQuery(name = "Empleado.findByClaveempleado", query = "SELECT e FROM Empleado e WHERE e.claveempleado = :claveempleado"),
    @NamedQuery(name = "Empleado.findLikeClaveempleado", query = "SELECT e FROM Empleado e WHERE e.claveempleado LIKE :claveempleado")})

public class Empleado implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static String FIND_ALL                     = "Empleado.findAll";
	public static String FIND_BY_ID                   = "Empleado.findByIdempleado";
	public static String FIND_BY_ACTIVO               = "Empleado.findByActivo";
	public static String FIND_BY_ID_PERSONA           = "Empleado.findByIdpersona";
	public static String FIND_BY_CLAVE_EMPLEADO       = "Empleado.findByClaveempleado";
	public static String FIND_LIKE_CLAVE_EMPLEADO     = "Empleado.findLikeClaveempleado";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idempleado;

	private Boolean activo;

	private String claveempleado;

	private String comentarios;

	//bi-directional many-to-one association to Persona
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="idpersona")
	private Persona persona;
	
	@JoinColumn(name="idtienda")
	private Tienda tienda;

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	public Empleado() {
	}

	public Integer getIdempleado() {
		return this.idempleado;
	}

	public void setIdempleado(Integer idempleado) {
		this.idempleado = idempleado;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getClaveempleado() {
		return this.claveempleado;
	}

	public void setClaveempleado(String claveempleado) {
		this.claveempleado = claveempleado;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}