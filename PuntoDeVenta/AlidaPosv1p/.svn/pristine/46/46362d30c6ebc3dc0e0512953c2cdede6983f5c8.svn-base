package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the precioventa database table.
 * 
 */
@Entity
@Table(name = "precioventa")
@NamedQueries({
    @NamedQuery(name = "Precioventa.findAll", query = "SELECT p FROM Precioventa p"),
    @NamedQuery(name = "Precioventa.findByIdprecioventa", query = "SELECT p FROM Precioventa p WHERE p.idprecioventa = :idprecioventa"),
    @NamedQuery(name = "Precioventa.findByIdproducto", query = "SELECT p FROM Precioventa p WHERE p.producto = :idproducto"),
    @NamedQuery(name = "Precioventa.finByIdProductoCantidad", query = "SELECT p FROM Precioventa p WHERE p.producto.idproducto = :idproducto AND p.cantidadproductos >= :cantidadproductos ORDER BY p.cantidadproductos asc"),
    @NamedQuery(name = "Precioventa.findByTipoprecio", query = "SELECT p FROM Precioventa p WHERE p.tipoprecio = :tipoprecio"),
    @NamedQuery(name = "Precioventa.findByModoprecio", query = "SELECT p FROM Precioventa p WHERE p.modoprecio = :modoprecio"),
    @NamedQuery(name = "Precioventa.findByComentarios", query = "SELECT p FROM Precioventa p WHERE p.comentarios = :comentarios")})
public class Precioventa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static String FIND_ALL                      = "Precioventa.findAll";
	public static String FIND_BY_ID                    = "Precioventa.findByIdprecioventa";
	public static String FIND_BY_ID_PRODUCTO           = "Precioventa.findByIdproducto";
	public static String FIND_BY_ID_PRODUCTO_CANTIDAD  = "Precioventa.finByIdProductoCantidad";
	public static String FIND_BY_TIPO_PRECIO           = "Precioventa.findByTipoprecio";
	public static String FIND_BY_MODO_PRECIO           = "Precioventa.findByModoprecio";	
	public static String FIND_BY_PRECIO_BASE           = "Precioventa.findByPreciobase";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idprecioventa;

	private float cantidadproductos;

	private String comentarios;

	private String modoprecio;

	private String tipoprecio;

	private float valor;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="idproducto")
	private Producto producto;

	public Precioventa() {
	}

	public Integer getIdprecioventa() {
		return this.idprecioventa;
	}

	public void setIdprecioventa(Integer idprecioventa) {
		this.idprecioventa = idprecioventa;
	}

	public float getCantidadproductos() {
		return this.cantidadproductos;
	}

	public void setCantidadproductos(float cantidadproductos) {
		this.cantidadproductos = cantidadproductos;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getModoprecio() {
		return this.modoprecio;
	}

	public void setModoprecio(String modoprecio) {
		this.modoprecio = modoprecio;
	}

	public String getTipoprecio() {
		return this.tipoprecio;
	}

	public void setTipoprecio(String tipoprecio) {
		this.tipoprecio = tipoprecio;
	}

	public float getValor() {
		return this.valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}