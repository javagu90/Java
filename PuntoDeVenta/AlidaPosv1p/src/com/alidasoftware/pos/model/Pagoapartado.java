package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the pagoapartado database table.
 * 
 */
@Entity
@Table(name = "pagoapartado")
@NamedQueries({
    @NamedQuery(name = "Pagoapartado.findAll", query = "SELECT p FROM Pagoapartado p"),
    @NamedQuery(name = "Pagoapartado.findByIdpago", query = "SELECT p FROM Pagoapartado p WHERE p.idpago = :idpago"),
    @NamedQuery(name = "Pagoapartado.findByIdapartado", query = "SELECT p FROM Pagoapartado p WHERE p.apartado.idapartado = :idapartado"),
    @NamedQuery(name = "Pagoapartado.findByIdformapago", query = "SELECT p FROM Pagoapartado p WHERE p.formapago = :idformapago"),
    @NamedQuery(name = "Pagoapartado.findByFecha", query = "SELECT p FROM Pagoapartado p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "Pagoapartado.findByCantidad", query = "SELECT p FROM Pagoapartado p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "Pagoapartado.findByReferenciapago", query = "SELECT p FROM Pagoapartado p WHERE p.referenciapago = :referenciapago"),
    @NamedQuery(name = "Pagoapartado.findByComentarios", query = "SELECT p FROM Pagoapartado p WHERE p.comentarios = :comentarios")})
public class Pagoapartado implements Serializable {
	private static final long serialVersionUID = 1L;

	public static String FIND_ALL                = "Pagoapartado.findAll";
	public static String FIND_BY_ID              = "Pagoapartado.findByIdpago";
	public static String FIND_BY_ID_APARTADO     = "Pagoapartado.findByIdapartado";
	public static String FIND_BY_ID_FORMA_PAGO   = "Pagoapartado.findByIdformapago";
	public static String FIND_BY_FECHA           = "Pagoapartado.findByFecha";
	public static String FIND_BY_CANTIDAD        = "Pagoapartado.findByCantidad";
	public static String FIND_BY_REFERENCIA_PAGO = "Pagoapartado.findByReferenciapago";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idpago;

	private float cantidad;

	private String comentarios;
	
	private float cambio;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private String referenciapago;

	//bi-directional many-to-one association to Apartado
	@ManyToOne
	@JoinColumn(name="idapartado")
	private Apartado apartado;

	//bi-directional many-to-one association to Formapago
	@ManyToOne
	@JoinColumn(name="idformapago")
	private Formapago formapago;

	public Pagoapartado() {
	}

	public Integer getIdpago() {
		return this.idpago;
	}

	public void setIdpago(Integer idpago) {
		this.idpago = idpago;
	}

	public float getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	
	public float getCambio() {
		return this.cambio;
	}

	public void setCambio(float cambio) {
		this.cambio = cambio;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getReferenciapago() {
		return this.referenciapago;
	}

	public void setReferenciapago(String referenciapago) {
		this.referenciapago = referenciapago;
	}

	public Apartado getApartado() {
		return this.apartado;
	}

	public void setApartado(Apartado apartado) {
		this.apartado = apartado;
	}

	public Formapago getFormapago() {
		return this.formapago;
	}

	public void setFormapago(Formapago formapago) {
		this.formapago = formapago;
	}

}