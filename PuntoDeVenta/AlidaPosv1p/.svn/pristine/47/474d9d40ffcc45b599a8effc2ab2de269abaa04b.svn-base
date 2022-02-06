package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the unidad database table.
 * 
 */
@Entity
@Table(name = "unidad")
@NamedQueries({
    @NamedQuery(name = "Unidad.findAll", query = "SELECT u FROM Unidad u"),
    @NamedQuery(name = "Unidad.findByIdunidad", query = "SELECT u FROM Unidad u WHERE u.idunidad = :idunidad"),
    @NamedQuery(name = "Unidad.findByNombre", query = "SELECT u FROM Unidad u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Unidad.findByDescripcion", query = "SELECT u FROM Unidad u WHERE u.descripcion = :descripcion")})
public class Unidad implements Serializable {
	private static final long serialVersionUID = 1L;

	public static String FIND_ALL            = "Unidad.findAll";
	public static String FIND_BY_ID          = "Unidad.findByIdunidad";
	public static String FIND_BY_NOMBRE      = "Unidad.findByNombre";
	public static String FIND_BY_DESCRIPCION = "Unidad.findByDescripcion";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idunidad;
	private String descripcion;
	private String nombre;
	private Boolean aceptafracciones;
	private boolean activo;
	

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="unidad")
	private List<Producto> productos;

	public Unidad() {
	}

	public Integer getIdunidad() {
		return this.idunidad;
	}

	public void setIdunidad(Integer idunidad) {
		this.idunidad = idunidad;
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
	
	public String getPermitirFracciones() {
		if (this.aceptafracciones == true) {
			return "Si";
		}else{
			return "No";
		}		
	}	

	public Boolean getAceptafracciones() {
		return this.aceptafracciones;
	}

	public void setAceptafracciones(Boolean aceptafracciones) {
		this.aceptafracciones = aceptafracciones;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setUnidad(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setUnidad(null);

		return producto;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}