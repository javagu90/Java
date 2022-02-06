package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the detalledevolucion database table.
 * 
 */
@Entity
@Table(name = "detalledevolucion")
@NamedQueries({
    @NamedQuery(name = "Detalledevolucion.findAll", query = "SELECT d FROM Detalledevolucion d"),
    @NamedQuery(name = "Detalledevolucion.findByIddetalledevolucion", query = "SELECT d FROM Detalledevolucion d WHERE d.iddetalledevolucion = :iddetalledevolucion"),
    @NamedQuery(name = "Detalledevolucion.findByIddevolucion", query = "SELECT d FROM Detalledevolucion d WHERE d.devolucion = :iddevolucion"),
    @NamedQuery(name = "Detalledevolucion.findByIdproducto", query = "SELECT d FROM Detalledevolucion d WHERE d.producto = :idproducto"),
    @NamedQuery(name = "Detalledevolucion.findByCantidad", query = "SELECT d FROM Detalledevolucion d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detalledevolucion.findByPreciocompra", query = "SELECT d FROM Detalledevolucion d WHERE d.preciocompra = :preciocompra")})
public class Detalledevolucion implements Serializable {
	private static final long serialVersionUID = 1L;

	public static String FIND_ALL              = "Detalledevolucion.findAll";
	public static String FIND_BY_ID            = "Detalledevolucion.findByIddetalledevolucion";
	public static String FIND_BY_ID_DEVOLUCION = "Detalledevolucion.findByIddevolucion";
	public static String FIND_BY_ID_PRODUCTO   = "Detalledevolucion.findByIdproducto";
	public static String FIND_BY_CANTIDAD      = "Detalledevolucion.findByCantidad";
	public static String FIND_BY_PRECIO_COMPRA = "Detalledevolucion.findByPreciocompra";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer iddetalledevolucion;

	private float cantidad;

	private float preciocompra;

	//bi-directional many-to-one association to Devolucion
	@ManyToOne
	@JoinColumn(name="iddevolucion")
	private Devolucion devolucion;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="idproducto")
	private Producto producto;

	public Detalledevolucion() {
	}

	public Integer getIddetalledevolucion() {
		return this.iddetalledevolucion;
	}

	public void setIddetalledevolucion(Integer iddetalledevolucion) {
		this.iddetalledevolucion = iddetalledevolucion;
	}

	public float getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public float getPreciocompra() {
		return this.preciocompra;
	}

	public void setPreciocompra(float preciocompra) {
		this.preciocompra = preciocompra;
	}

	public Devolucion getDevolucion() {
		return this.devolucion;
	}

	public void setDevolucion(Devolucion devolucion) {
		this.devolucion = devolucion;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}