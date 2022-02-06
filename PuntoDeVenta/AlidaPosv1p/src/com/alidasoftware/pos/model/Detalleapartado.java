package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the detalleapartado database table.
 * 
 */
@Entity
@Table(name = "detalleapartado")
@NamedQueries({
    @NamedQuery(name = "Detalleapartado.findAll", query = "SELECT d FROM Detalleapartado d"),
    @NamedQuery(name = "Detalleapartado.findByIddetalleapartado", query = "SELECT d FROM Detalleapartado d WHERE d.iddetalleapartado = :iddetalleapartado"),
    @NamedQuery(name = "Detalleapartado.findByIdapartado", query = "SELECT d FROM Detalleapartado d WHERE d.apartado = :idapartado"),
    @NamedQuery(name = "Detalleapartado.findByIdproducto", query = "SELECT d FROM Detalleapartado d WHERE d.producto = :idproducto"),
    @NamedQuery(name = "Detalleapartado.findByCantidad", query = "SELECT d FROM Detalleapartado d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detalleapartado.findByPreciounitario", query = "SELECT d FROM Detalleapartado d WHERE d.preciounitario = :preciounitario")})
public class Detalleapartado implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static String FIND_ALL                = "Detalleapartado.findAll";
	public static String FIND_BY_ID              = "Detalleapartado.findByIddetalleapartado";
	public static String FIND_BY_ID_APARTADO     = "Detalleapartado.findByIdapartado";
	public static String FIND_BY_ID_PRODUCTO     = "Detalleapartado.findByIdproducto";
	public static String FIND_BY_CANTIDAD        = "Detalleapartado.findByCantidad";
	public static String FIND_BY_PRECIO_UNITARIO = "Detalleapartado.findByPreciounitario";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer iddetalleapartado;

	private float cantidad;

	private float preciounitario;

	//bi-directional many-to-one association to Apartado
	@ManyToOne
	@JoinColumn(name="idapartado")
	private Apartado apartado;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="idproducto")
	private Producto producto;

	public Detalleapartado() {
	}

	public Integer getIddetalleapartado() {
		return this.iddetalleapartado;
	}

	public void setIddetalleapartado(Integer iddetalleapartado) {
		this.iddetalleapartado = iddetalleapartado;
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

	public Apartado getApartado() {
		return this.apartado;
	}

	public void setApartado(Apartado apartado) {
		this.apartado = apartado;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}