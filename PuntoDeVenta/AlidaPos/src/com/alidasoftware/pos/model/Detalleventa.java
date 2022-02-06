package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the detalleventa database table.
 * 
 */
@Entity
@Table(name = "detalleventa")
@NamedQueries({
    @NamedQuery(name = "Detalleventa.findAll", query = "SELECT d FROM Detalleventa d"),
    @NamedQuery(name = "Detalleventa.findByIddetalleventa", query = "SELECT d FROM Detalleventa d WHERE d.iddetalleventa = :iddetalleventa"),
    @NamedQuery(name = "Detalleventa.findByIdproducto", query = "SELECT d FROM Detalleventa d WHERE d.producto = :idproducto"),
    @NamedQuery(name = "Detalleventa.findByIdventa", query = "SELECT d FROM Detalleventa d WHERE d.venta = :idventa"),
    @NamedQuery(name = "Detalleventa.findByCantidad", query = "SELECT d FROM Detalleventa d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detalleventa.findByPreciounitario", query = "SELECT d FROM Detalleventa d WHERE d.preciounitario = :preciounitario")})
public class Detalleventa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static String FIND_ALL                = "Detalleventa.findAll";
	public static String FIND_BY_ID              = "Detalleventa.findByIddetalleventa";
	public static String FIND_BY_ID_PRODUCTO     = "Detalleventa.findByIdproducto";
	public static String FIND_BY_ID_VENTA        = "Detalleventa.findByIdventa";
	public static String FIND_BY_CANTIDAD        = "Detalleventa.findByCantidad";
	public static String FIND_BY_PRECIO_UNITARIO = "Detalleventa.findByPreciounitario";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer iddetalleventa;

	private float cantidad;

	private float preciounitario;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="idproducto")
	private Producto producto;

	//bi-directional many-to-one association to Venta
	@ManyToOne
	@JoinColumn(name="idventa")
	private Venta venta;

	public Detalleventa() {
	}

	public Integer getIddetalleventa() {
		return this.iddetalleventa;
	}

	public void setIddetalleventa(Integer iddetalleventa) {
		this.iddetalleventa = iddetalleventa;
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

	public Venta getVenta() {
		return this.venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

}