package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the inventario database table.
 * 
 */
@Entity
@Table(name = "inventario")
@NamedQueries({
    @NamedQuery(name = "Inventario.findAll", query = "SELECT i FROM Inventario i"),
    @NamedQuery(name = "Inventario.findByIdinventario", query = "SELECT i FROM Inventario i WHERE i.idinventario = :idinventario"),
    @NamedQuery(name = "Inventario.findByNombre", query = "SELECT i FROM Inventario i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "Inventario.findByDescripcion", query = "SELECT i FROM Inventario i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "Inventario.findByComentarios", query = "SELECT i FROM Inventario i WHERE i.comentarios = :comentarios")})
public class Inventario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static String FIND_ALL            = "Inventario.findAll";
	public static String FIND_BY_ID          = "Inventario.findByIdinventario";
	public static String FIND_BY_NOMBRE      = "Inventario.findByNombre";
	public static String FIND_BY_DESCRIPCION = "Inventario.findByDescripcion";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idinventario;

	private String comentarios;

	private String descripcion;

	private String nombre;
	
	@JoinColumn(name="idtienda")
	private Tienda tienda;

	//bi-directional many-to-one association to Detalleinventario
	@OneToMany(mappedBy="inventario")
	private List<Detalleinventario> detalleinventarios;

	public Inventario() {
	}

	public Integer getIdinventario() {
		return this.idinventario;
	}

	public void setIdinventario(Integer idinventario) {
		this.idinventario = idinventario;
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

	public List<Detalleinventario> getDetalleinventarios() {
		return this.detalleinventarios;
	}

	public void setDetalleinventarios(List<Detalleinventario> detalleinventarios) {
		this.detalleinventarios = detalleinventarios;
	}

	public Detalleinventario addDetalleinventario(Detalleinventario detalleinventario) {
		getDetalleinventarios().add(detalleinventario);
		detalleinventario.setInventario(this);

		return detalleinventario;
	}

	public Detalleinventario removeDetalleinventario(Detalleinventario detalleinventario) {
		getDetalleinventarios().remove(detalleinventario);
		detalleinventario.setInventario(null);

		return detalleinventario;
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

}