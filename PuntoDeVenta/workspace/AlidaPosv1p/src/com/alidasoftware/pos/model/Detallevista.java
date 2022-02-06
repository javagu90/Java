package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the detallevista database table.
 * 
 */
@Entity
@Table(name = "detallevista")
@NamedQueries({
    @NamedQuery(name = "Detallevista.findAll", query = "SELECT d FROM Detallevista d"),
    @NamedQuery(name = "Detallevista.findByIddetallevista", query = "SELECT d FROM Detallevista d WHERE d.iddetallevista = :iddetallevista"),
    @NamedQuery(name = "Detallevista.findByIdproducto", query = "SELECT d FROM Detallevista d WHERE d.producto = :idproducto"),
    @NamedQuery(name = "Detallevista.findByIdvista", query = "SELECT d FROM Detallevista d WHERE d.vista = :idvista"),
    @NamedQuery(name = "Detallevista.findByCantidad", query = "SELECT d FROM Detallevista d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detallevista.findByPreciounitario", query = "SELECT d FROM Detallevista d WHERE d.preciounitario = :preciounitario")})
public class Detallevista implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static String FIND_ALL                = "Detallevista.findAll";
	public static String FIND_BY_ID              = "Detallevista.findByIddetallevista";
	public static String FIND_BY_ID_PRODUCTO     = "Detallevista.findByIdproducto";
	public static String FIND_BY_ID_VISTA        = "Detallevista.findByIdvista";
	public static String FIND_BY_CANTIDAD        = "Detallevista.findByCantidad";
	public static String FIND_BY_PRECIO_UNITARIO = "Detallevista.findByPreciounitario";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer iddetallevista;

	private float cantidad;

	private float preciounitario;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="idproducto")
	private Producto producto;

	//bi-directional many-to-one association to Vista
	@ManyToOne
	@JoinColumn(name="idvista")
	private Vista vista;

	public Detallevista() {
	}

	public Integer getIddetallevista() {
		return this.iddetallevista;
	}

	public void setIddetallevista(Integer iddetallevista) {
		this.iddetallevista = iddetallevista;
	}

	public float getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public float getPreciounitario() {
		return this.preciounitario;
	}

	public void setPreciounitario(float preciounitario) {
		this.preciounitario = preciounitario;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Vista getVista() {
		return this.vista;
	}

	public void setVista(Vista vista) {
		this.vista = vista;
	}

}