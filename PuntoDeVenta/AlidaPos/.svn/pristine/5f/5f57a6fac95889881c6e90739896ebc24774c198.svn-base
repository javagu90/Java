package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the categoriaproducto database table.
 * 
 */
@Entity
@Table(name = "categoriaproducto")
@NamedQueries({
    @NamedQuery(name = "Categoriaproducto.findAll", query = "SELECT c FROM Categoriaproducto c"),
    @NamedQuery(name = "Categoriaproducto.findByIdcategoriaproducto", query = "SELECT c FROM Categoriaproducto c WHERE c.idcategoriaproducto = :idcategoriaproducto"),
    @NamedQuery(name = "Categoriaproducto.findByNombre", query = "SELECT c FROM Categoriaproducto c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Categoriaproducto.findByDescripcion", query = "SELECT c FROM Categoriaproducto c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Categoriaproducto.findByComentarios", query = "SELECT c FROM Categoriaproducto c WHERE c.comentarios = :comentarios")})
public class Categoriaproducto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static String FIND_ALL            = "Categoriaproducto.findAll";
	public static String FIND_BY_ID          = "Categoriaproducto.findByIdcategoriaproducto";
	public static String FIND_BY_NOMBRE      = "Categoriaproducto.findByNombre";
	public static String FIND_BY_DESCRIPCION = "Categoriaproducto.findByDescripcion";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idcategoriaproducto;

	private String comentarios;

	private String descripcion;

	private String nombre;
	
	private boolean activo;

	//bi-directional many-to-many association to Promocion
	@ManyToMany(mappedBy="categoriaproductos")
	private List<Promocion> promocions;

	//bi-directional many-to-many association to Producto
	@ManyToMany(mappedBy="categoriaproductos")
	private List<Producto> productos;

	public Categoriaproducto() {
	}

	public Integer getIdcategoriaproducto() {
		return this.idcategoriaproducto;
	}

	public void setIdcategoriaproducto(Integer idcategoriaproducto) {
		this.idcategoriaproducto = idcategoriaproducto;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Promocion> getPromocions() {
		return this.promocions;
	}

	public void setPromocions(List<Promocion> promocions) {
		this.promocions = promocions;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}