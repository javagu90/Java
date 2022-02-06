package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the detalleinventario database table.
 * 
 */
@Entity
@Table(name = "detalleinventario")
@NamedQueries({
    @NamedQuery(name = "Detalleinventario.findAll", query = "SELECT d FROM Detalleinventario d"),
    @NamedQuery(name = "Detalleinventario.findByIddetalleinventario", query = "SELECT d FROM Detalleinventario d WHERE d.iddetalleinventario = :iddetalleinventario"),
    @NamedQuery(name = "Detalleinventario.findByIdinventario", query = "SELECT d FROM Detalleinventario d WHERE d.inventario = :idinventario"),
    @NamedQuery(name = "Detalleinventario.findByIdproducto", query = "SELECT d FROM Detalleinventario d WHERE d.producto.idproducto = :idproducto"),
    @NamedQuery(name = "Detalleinventario.findByExistenciaventa", query = "SELECT d FROM Detalleinventario d WHERE d.existenciaventa = :existenciaventa"),
    @NamedQuery(name = "Detalleinventario.findByExistenciaapvi", query = "SELECT d FROM Detalleinventario d WHERE d.existenciaapvi = :existenciaapvi"),
    @NamedQuery(name = "Detalleinventario.findByPreciocompra", query = "SELECT d FROM Detalleinventario d WHERE d.preciocompra = :preciocompra"),
    @NamedQuery(name = "Detalleinventario.findByPrecioventa", query = "SELECT d FROM Detalleinventario d WHERE d.precioventa = :precioventa"),
    @NamedQuery(name = "Detalleinventario.findByCaducidad", query = "SELECT d FROM Detalleinventario d WHERE d.caducidad = :caducidad"),
    @NamedQuery(name = "Detalleinventario.findByLote", query = "SELECT d FROM Detalleinventario d WHERE d.lote = :lote"),
    @NamedQuery(name = "Detalleinventario.findByCantidadmin", query = "SELECT d FROM Detalleinventario d WHERE d.cantidadmin = :cantidadmin"),
    @NamedQuery(name = "Detalleinventario.findByCantidadmax", query = "SELECT d FROM Detalleinventario d WHERE d.cantidadmax = :cantidadmax"),
    @NamedQuery(name = "Detalleinventario.findByIdproductoInventario", query = "SELECT d FROM Detalleinventario d WHERE d.producto.idproducto = :idproducto AND d.inventario = :idinventario")})

public class Detalleinventario implements Serializable {
	private static final long serialVersionUID = 1L;
		
	public static String FIND_ALL                 = "Detalleinventario.findAll";
	public static String FIND_BY_ID               = "Detalleinventario.findByIddetalleinventario";
	public static String FIND_BY_ID_INVENTARIO    = "Detalleinventario.findByIdinventario";
	public static String FIND_BY_ID_PRODUCTO      = "Detalleinventario.findByIdproducto";
	public static String FIND_BY_EXISTENCIA_VENTA = "Detalleinventario.findByExistenciaventa";
	public static String FIND_BY_EXISTENCIA_APVI  = "Detalleinventario.findByExistenciaapvi";
	public static String FIND_BY_PRECIO_COMPRA    = "Detalleinventario.findByPreciocompra";
	public static String FIND_BY_PRECIO_VENTA    = "Detalleinventario.findByPrecioventa";
	public static String FIND_BY_CADUCIDAD        = "Detalleinventario.findByCaducidad";
	public static String FIND_BY_LOTE             = "Detalleinventario.findByLote";
	public static String FIND_BY_CANTIDAD_MIN     = "Detalleinventario.findByCantidadmin";
	public static String FIND_BY_CANTIDAD_MAX     = "Detalleinventario.findByCantidadmax";
	public static String FIND_BY_ID_PRODUCTO_INVENTARIO = "Detalleinventario.findByIdproductoInventario";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer iddetalleinventario;

	@Temporal(TemporalType.DATE)
	private Date caducidad;
	
	@Temporal(TemporalType.DATE)
	private Date ultimaoperacion;

	private float cantidadmax;

	private float cantidadmin;

	private float existenciaapvi;

	private float existenciaventa;

	private Integer lote;

	private float preciocompra;

	private float precioventa;

	//private Boolean promocional;	

	//bi-directional many-to-one association to Inventario
	@ManyToOne
	@JoinColumn(name="idinventario")
	private Inventario inventario;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="idproducto")
	private Producto producto;

	public Detalleinventario() {
	}

	public Integer getIddetalleinventario() {
		return this.iddetalleinventario;
	}

	public void setIddetalleinventario(Integer iddetalleinventario) {
		this.iddetalleinventario = iddetalleinventario;
	}

	public Date getCaducidad() {
		return this.caducidad;
	}

	public void setCaducidad(Date caducidad) {
		this.caducidad = caducidad;
	}
	
	public Date getUltimaoperacion() {
		return this.ultimaoperacion;
	}

	public void setUltimaoperacion(Date ultimaoperacion) {
		this.ultimaoperacion = ultimaoperacion;
	}

	public float getCantidadmax() {
		return this.cantidadmax;
	}

	public void setCantidadmax(float cantidadmax) {
		this.cantidadmax = cantidadmax;
	}

	public float getCantidadmin() {
		return this.cantidadmin;
	}

	public void setCantidadmin(float cantidadmin) {
		this.cantidadmin = cantidadmin;
	}

	public float getExistenciaapvi() {
		return this.existenciaapvi;
	}

	public void setExistenciaapvi(float existenciaapvi) {
		this.existenciaapvi = existenciaapvi;
	}

	public float getExistenciaventa() {
		return this.existenciaventa;
	}

	public void setExistenciaventa(float existenciaventa) {
		this.existenciaventa = existenciaventa;
	}

	public Integer getLote() {
		return this.lote;
	}

	public void setLote(Integer lote) {
		this.lote = lote;
	}

	public float getPreciocompra() {
		return this.preciocompra;
	}

	public void setPreciocompra(float preciocompra) {
		this.preciocompra = preciocompra;
	}

	public float getPrecioventa() {
		return this.precioventa;
	}

	public void setPrecioventa(float precioventa) {
		this.precioventa = precioventa;
	}
	/*
	public Boolean getPromocional() {
		return this.promocional;
	}

	public void setPromocional(Boolean promocional) {
		this.promocional = promocional;
	}
	*/
	public Inventario getInventario() {
		return this.inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}