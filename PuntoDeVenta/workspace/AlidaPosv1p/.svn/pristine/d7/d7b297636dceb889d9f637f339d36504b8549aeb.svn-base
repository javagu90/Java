package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the entradainventario database table.
 * 
 */
@Entity
@Table(name = "entradainventario")
@NamedQueries({
    @NamedQuery(name = "Entradainventario.findAll", query = "SELECT e FROM Entradainventario e"),
    @NamedQuery(name = "Entradainventario.findByIdentradainventario", query = "SELECT e FROM Entradainventario e WHERE e.identradainventario = :identradainventario"),
    @NamedQuery(name = "Entradainventario.findByIdproveedor", query = "SELECT e FROM Entradainventario e WHERE e.proveedor = :idproveedor"),
    @NamedQuery(name = "Entradainventario.findByIdproducto", query = "SELECT e FROM Entradainventario e WHERE e.producto = :idproducto"),
    @NamedQuery(name = "Entradainventario.findByFecha", query = "SELECT e FROM Entradainventario e WHERE e.fecha = :fecha"),
    @NamedQuery(name = "Entradainventario.findByPreciocompra", query = "SELECT e FROM Entradainventario e WHERE e.preciocompra = :preciocompra"),
    @NamedQuery(name = "Entradainventario.findByCantidad", query = "SELECT e FROM Entradainventario e WHERE e.cantidad = :cantidad")})
public class Entradainventario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static String FIND_ALL              = "Entradainventario.findAll";
	public static String FIND_BY_ID            = "Entradainventario.findByIdentradainventario";
	public static String FIND_BY_ID_PROVEEDOR  = "Entradainventario.findByIdproveedor";
	public static String FIND_BY_ID_PRODUCTO   = "Entradainventario.findByIdproducto";
	public static String FIND_BY_FECHA         = "Entradainventario.findByFecha";
	public static String FIND_BY_PRECIO_COMPRA = "Entradainventario.findByPreciocompra";
	public static String FIND_BY_CANTIDAD      = "Entradainventario.findByCantidad";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer identradainventario;

	private float cantidad;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private float preciocompra;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="idproducto")
	private Producto producto;

	//bi-directional many-to-one association to Proveedor
	@ManyToOne
	@JoinColumn(name="idproveedor")
	private Proveedor proveedor;

	public Entradainventario() {
	}

	public Integer getIdentradainventario() {
		return this.identradainventario;
	}

	public void setIdentradainventario(Integer identradainventario) {
		this.identradainventario = identradainventario;
	}

	public float getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getPreciocompra() {
		return this.preciocompra;
	}

	public void setPreciocompra(float preciocompra) {
		this.preciocompra = preciocompra;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

}