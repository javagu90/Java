package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the detallecortecaja database table.
 * 
 */
@Entity
@Table(name = "detallecortecaja")
@NamedQueries({
    @NamedQuery(name = "Detallecortecaja.findAll", query = "SELECT d FROM Detallecortecaja d"),
    @NamedQuery(name = "Detallecortecaja.findByIddetallecortecaja", query = "SELECT d FROM Detallecortecaja d WHERE d.iddetallecortecaja = :iddetallecortecaja"),
    @NamedQuery(name = "Detallecortecaja.findByIdcortecaja", query = "SELECT d FROM Detallecortecaja d WHERE d.cortecaja = :idcortecaja"),
    @NamedQuery(name = "Detallecortecaja.findByIdformapago", query = "SELECT d FROM Detallecortecaja d WHERE d.formapago = :idformapago"),
    @NamedQuery(name = "Detallecortecaja.findByCantidad", query = "SELECT d FROM Detallecortecaja d WHERE d.cantidad = :cantidad")})
public class Detallecortecaja implements Serializable {
	private static final long serialVersionUID = 1L;
		
	public static String FIND_ALL              = "Detallecortecaja.findAll";
	public static String FIND_BY_ID            = "Detallecortecaja.findByIddetallecortecaja";
	public static String FIND_BY_ID_CORTE_CAJA = "Detallecortecaja.findByIdcortecaja";
	public static String FIND_BY_ID_FORMA_PAGO = "Detallecortecaja.findByIdformapago";
	public static String FIND_BY_CANTIDAD      = "Detallecortecaja.findByCantidad";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer iddetallecortecaja;

	private float cantidad;

	//bi-directional many-to-one association to Cortecaja
	@ManyToOne
	@JoinColumn(name="idcortecaja")
	private Cortecaja cortecaja;

	//bi-directional many-to-one association to Formapago
	@ManyToOne
	@JoinColumn(name="idformapago")
	private Formapago formapago;

	public Detallecortecaja() {
	}

	public Integer getIddetallecortecaja() {
		return this.iddetallecortecaja;
	}

	public void setIddetallecortecaja(Integer iddetallecortecaja) {
		this.iddetallecortecaja = iddetallecortecaja;
	}

	public float getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public Cortecaja getCortecaja() {
		return this.cortecaja;
	}

	public void setCortecaja(Cortecaja cortecaja) {
		this.cortecaja = cortecaja;
	}

	public Formapago getFormapago() {
		return this.formapago;
	}

	public void setFormapago(Formapago formapago) {
		this.formapago = formapago;
	}

}