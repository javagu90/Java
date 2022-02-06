package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the detalletraspaso database table.
 * 
 */
@Entity
@Table(name = "detalletraspaso")
@NamedQueries({
    @NamedQuery(name = "Detalletraspaso.findAll", query = "SELECT d FROM Detalletraspaso d"),
    @NamedQuery(name = "Detalletraspaso.findByIddetalletraspaso", query = "SELECT d FROM Detalletraspaso d WHERE d.iddetalletraspaso = :iddetalletraspaso"),
    @NamedQuery(name = "Detalletraspaso.findByIdproducto", query = "SELECT d FROM Detalletraspaso d WHERE d.producto = :idproducto"),
    @NamedQuery(name = "Detalletraspaso.findByIdtraspaso", query = "SELECT d FROM Detalletraspaso d WHERE d.traspaso = :idtraspaso"),
    @NamedQuery(name = "Detalletraspaso.findByCantidad", query = "SELECT d FROM Detalletraspaso d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detalletraspaso.findByPreciounitario", query = "SELECT d FROM Detalletraspaso d WHERE d.preciounitario = :preciounitario")})
public class Detalletraspaso implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static String FIND_ALL                = "Detalletraspaso.findAll";
	public static String FIND_BY_ID              = "Detalletraspaso.findByIddetalletraspaso";
	public static String FIND_BY_ID_PRODUCTO     = "Detalletraspaso.findByIdproducto";
	public static String FIND_BY_ID_TRASPASO     = "Detalletraspaso.findByIdtraspaso";
	public static String FIND_BY_CANTIDAD        = "Detalletraspaso.findByCantidad";
	public static String FIND_BY_PRECIO_UNITARIO = "Detalletraspaso.findByPreciounitario";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer iddetalletraspaso;

	private float cantidad;

	private float preciounitario;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="idproducto")
	private Producto producto;

	//bi-directional many-to-one association to Traspaso
	@ManyToOne
	@JoinColumn(name="idtraspaso")
	private Traspaso traspaso;

	public Detalletraspaso() {
	}

	public Integer getIddetalletraspaso() {
		return this.iddetalletraspaso;
	}

	public void setIddetalletraspaso(Integer iddetalletraspaso) {
		this.iddetalletraspaso = iddetalletraspaso;
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

	public Traspaso getTraspaso() {
		return this.traspaso;
	}

	public void setTraspaso(Traspaso traspaso) {
		this.traspaso = traspaso;
	}

}