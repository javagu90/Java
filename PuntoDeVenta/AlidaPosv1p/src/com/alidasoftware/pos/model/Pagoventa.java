package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the pagoventa database table.
 * 
 */
@Entity
@Table(name = "pagoventa")
@NamedQueries({
    @NamedQuery(name = "Pagoventa.findAll", query = "SELECT p FROM Pagoventa p"),
    @NamedQuery(name = "Pagoventa.findByIdpagoventa", query = "SELECT p FROM Pagoventa p WHERE p.idpago = :idpago"),
    @NamedQuery(name = "Pagoventa.findByIdventa", query = "SELECT p FROM Pagoventa p WHERE p.venta.idventa = :idventa"),
    @NamedQuery(name = "Pagoventa.findByIdformapago", query = "SELECT p FROM Pagoventa p WHERE p.formapago = :idformapago"),
    @NamedQuery(name = "Pagoventa.findByFecha", query = "SELECT p FROM Pagoventa p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "Pagoventa.findByCantidad", query = "SELECT p FROM Pagoventa p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "Pagoventa.findByReferenciapago", query = "SELECT p FROM Pagoventa p WHERE p.referenciapago = :referenciapago"),
    @NamedQuery(name = "Pagoventa.findByComentarios", query = "SELECT p FROM Pagoventa p WHERE p.comentarios = :comentarios")})
public class Pagoventa implements Serializable {
	private static final long serialVersionUID = 1L;

	public static String FIND_ALL                = "Pagoventa.findAll";
	public static String FIND_BY_ID              = "Pagoventa.findByIdpagoventa";
	public static String FIND_BY_ID_VENTA        = "Pagoventa.findByIdventa";
	public static String FIND_BY_ID_FORMA_PAGO   = "Pagoventa.findByIdformapago";
	public static String FIND_BY_FECHA           = "Pagoventa.findByFecha";
	public static String FIND_BY_CANTIDAD        = "Pagoventa.findByCantidad";
	public static String FIND_BY_REFERENCIA_PAGO = "Pagoventa.findByReferenciapago";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idpago;

	private float cantidad;

	private String comentarios;
	
	private float cambio;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private String referenciapago;

	//bi-directional many-to-one association to Formapago
	@ManyToOne
	@JoinColumn(name="idformapago")
	private Formapago formapago;

	//bi-directional many-to-one association to Venta
	@ManyToOne
	@JoinColumn(name="idventa")
	private Venta venta;

	public Pagoventa() {
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

	public Formapago getFormapago() {
		return this.formapago;
	}

	public void setFormapago(Formapago formapago) {
		this.formapago = formapago;
	}

	public Venta getVenta() {
		return this.venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

}