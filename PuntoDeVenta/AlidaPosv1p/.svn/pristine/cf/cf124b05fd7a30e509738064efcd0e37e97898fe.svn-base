package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the proveedor database table.
 * 
 */
@Entity
@Table(name = "proveedor")
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p"),
    @NamedQuery(name = "Proveedor.findByIdproveedor", query = "SELECT p FROM Proveedor p WHERE p.idproveedor = :idproveedor"),
    @NamedQuery(name = "Proveedor.findByIdpersona", query = "SELECT p FROM Proveedor p WHERE p.persona = :idpersona"),
    @NamedQuery(name = "Proveedor.findByRepresentante", query = "SELECT p FROM Proveedor p WHERE p.representante = :representante"),
    @NamedQuery(name = "Proveedor.findByUltimaoperacion", query = "SELECT p FROM Proveedor p WHERE p.ultimaoperacion = :ultimaoperacion"),
    @NamedQuery(name = "Proveedor.findByClaveproveedor", query = "SELECT p FROM Proveedor p WHERE p.claveproveedor = :claveproveedor"),
    @NamedQuery(name = "Proveedor.findByComentarios", query = "SELECT p FROM Proveedor p WHERE p.comentarios = :comentarios")})
public class Proveedor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static String FIND_ALL                 = "Proveedor.findAll";
	public static String FIND_BY_ID               = "Proveedor.findByIdproveedor";
	public static String FIND_BY_ID_PERSONA       = "Proveedor.findByIdpersona";
	public static String FIND_BY_REPRESENTANTE    = "Proveedor.findByRepresentante";
	public static String FIND_BY_ULTIMA_OPERACION = "Proveedor.findByUltimaoperacion";
	public static String FIND_BY_CLAVE_PROVEEDOR  = "Proveedor.findByClaveproveedor";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idproveedor;

	private String claveproveedor;

	private String comentarios;

	private String representante;
	
	private boolean activo;

	@Temporal(TemporalType.DATE)
	private Date ultimaoperacion;

	//bi-directional many-to-one association to Entradainventario
	@OneToMany(mappedBy="proveedor")
	private List<Entradainventario> entradainventarios;

	//bi-directional many-to-one association to Persona
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="idpersona")
	private Persona persona;

	public Proveedor() {
	}

	public Integer getIdproveedor() {
		return this.idproveedor;
	}

	public void setIdproveedor(Integer idproveedor) {
		this.idproveedor = idproveedor;
	}

	public String getClaveproveedor() {
		return this.claveproveedor;
	}

	public void setClaveproveedor(String claveproveedor) {
		this.claveproveedor = claveproveedor;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getRepresentante() {
		return this.representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
	}

	public Date getUltimaoperacion() {
		return this.ultimaoperacion;
	}

	public void setUltimaoperacion(Date ultimaoperacion) {
		this.ultimaoperacion = ultimaoperacion;
	}

	public List<Entradainventario> getEntradainventarios() {
		return this.entradainventarios;
	}

	public void setEntradainventarios(List<Entradainventario> entradainventarios) {
		this.entradainventarios = entradainventarios;
	}

	public Entradainventario addEntradainventario(Entradainventario entradainventario) {
		getEntradainventarios().add(entradainventario);
		entradainventario.setProveedor(this);

		return entradainventario;
	}

	public Entradainventario removeEntradainventario(Entradainventario entradainventario) {
		getEntradainventarios().remove(entradainventario);
		entradainventario.setProveedor(null);

		return entradainventario;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}