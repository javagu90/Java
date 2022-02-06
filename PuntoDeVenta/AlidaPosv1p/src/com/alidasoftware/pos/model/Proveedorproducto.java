package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the proveedorproducto database table.
 * 
 */
@Entity
@Table(name = "proveedorproducto")
@NamedQueries({
    @NamedQuery(name = "Proveedorproducto.findAll", query = "SELECT p FROM Proveedorproducto p"),
    @NamedQuery(name = "Proveedorproducto.findByIdproveedorproducto", query = "SELECT p FROM Proveedorproducto p WHERE p.idproveedorproducto = :idproveedorproducto"),
    @NamedQuery(name = "Proveedorproducto.findByIdproducto", query = "SELECT p FROM Proveedorproducto p WHERE p.producto = :idproducto"),
    @NamedQuery(name = "Proveedorproducto.findByIdproveedor", query = "SELECT p FROM Proveedorproducto p WHERE p.proveedor = :idproveedor"),
    @NamedQuery(name = "Proveedorproducto.findByPrecio", query = "SELECT p FROM Proveedorproducto p WHERE p.precio = :precio")})
public class Proveedorproducto implements Serializable {
	private static final long serialVersionUID = 1L;

	public static String FIND_ALL             = "Proveedorproducto.findAll";
	public static String FIND_BY_ID           = "Proveedorproducto.findByIdproveedorproducto";
	public static String FIND_BY_ID_PRODUCTO  = "Proveedorproducto.findByIdproducto";
	public static String FIND_BY_ID_PROVEEDOR = "Proveedorproducto.findByIdproveedor";
	public static String FIND_BY_PRECIO       = "Proveedorproducto.findByPrecio";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idproveedorproducto;

	private float precio;

	//bi-directional many-to-one association to Producto
	@ManyToOne//(cascade = CascadeType.PERSIST)
	@JoinColumn(name="idproducto")
	private Producto producto;

	//bi-directional many-to-one association to Proveedor
	@ManyToOne
	@JoinColumn(name="idproveedor")
	private Proveedor proveedor;

	public Proveedorproducto() {
	}

	public Integer getIdproveedorproducto() {
		return this.idproveedorproducto;
	}

	public void setIdproveedorproducto(Integer idproveedorproducto) {
		this.idproveedorproducto = idproveedorproducto;
	}

	public float getPrecio() {
		return this.precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
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