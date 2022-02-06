package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the detallecorteparcial database table.
 * 
 */
@Entity
@Table(name = "detallecorteparcial")
@NamedQueries({
    @NamedQuery(name = "Detallecorteparcial.findAll", query = "SELECT d FROM Detallecorteparcial d"),
    @NamedQuery(name = "Detallecorteparcial.findByIddetallecorteparcial", query = "SELECT d FROM Detallecorteparcial d WHERE d.iddetallecorteparcial = :iddetallecorteparcial"),
    @NamedQuery(name = "Detallecorteparcial.findByIdcorteparcial", query = "SELECT d FROM Detallecorteparcial d WHERE d.corteparcial = :idcorteparcial"),
    @NamedQuery(name = "Detallecorteparcial.findByIdformapago", query = "SELECT d FROM Detallecorteparcial d WHERE d.formapago = :idformapago"),
    @NamedQuery(name = "Detallecorteparcial.findByCantidad", query = "SELECT d FROM Detallecorteparcial d WHERE d.cantidad = :cantidad")})
public class Detallecorteparcial implements Serializable {
	private static final long serialVersionUID = 1L;
		
	public static String FIND_ALL                 = "Detallecorteparcial.findAll";
	public static String FIND_BY_ID               = "Detallecorteparcial.findByIddetallecorteparcial";
	public static String FIND_BY_ID_CORTE_PARCIAL = "Detallecorteparcial.findByIdcorteparcial";
	public static String FIND_BY_ID_FORMA_PAGO    = "Detallecorteparcial.findByIdformapago";
	public static String FIND_BY_CANTIDAD         = "Detallecorteparcial.findByCantidad";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer iddetallecorteparcial;

	private float cantidad;

	//bi-directional many-to-one association to Corteparcial
	@ManyToOne
	@JoinColumn(name="idcorteparcial")
	private Corteparcial corteparcial;

	//bi-directional many-to-one association to Formapago
	@ManyToOne
	@JoinColumn(name="idformapago")
	private Formapago formapago;

	public Detallecorteparcial() {
	}

	public Integer getIddetallecorteparcial() {
		return this.iddetallecorteparcial;
	}

	public void setIddetallecorteparcial(Integer iddetallecorteparcial) {
		this.iddetallecorteparcial = iddetallecorteparcial;
	}

	public float getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public Corteparcial getCorteparcial() {
		return this.corteparcial;
	}

	public void setCorteparcial(Corteparcial corteparcial) {
		this.corteparcial = corteparcial;
	}

	public Formapago getFormapago() {
		return this.formapago;
	}

	public void setFormapago(Formapago formapago) {
		this.formapago = formapago;
	}
}
