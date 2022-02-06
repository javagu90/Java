package com.alidasoftware.pos.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the detallepromocion database table.
 * 
 */
@Entity
@NamedQuery(name="Detallepromocion.findAll", query="SELECT d FROM Detallepromocion d")
public class Detallepromocion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer iddetallepromocion;

	private float cantidadadescontar;

	private Integer iddetalleventa;

	private Integer idpromocion;

	public Detallepromocion() {
	}

	public Integer getIddetallepromocion() {
		return this.iddetallepromocion;
	}

	public void setIddetallepromocion(Integer iddetallepromocion) {
		this.iddetallepromocion = iddetallepromocion;
	}

	public float getCantidadadescontar() {
		return this.cantidadadescontar;
	}

	public void setCantidadadescontar(float cantidadadescontar) {
		this.cantidadadescontar = cantidadadescontar;
	}

	public Integer getIddetalleventa() {
		return this.iddetalleventa;
	}

	public void setIddetalleventa(Integer iddetalleventa) {
		this.iddetalleventa = iddetalleventa;
	}

	public Integer getIdpromocion() {
		return this.idpromocion;
	}

	public void setIdpromocion(Integer idpromocion) {
		this.idpromocion = idpromocion;
	}

}