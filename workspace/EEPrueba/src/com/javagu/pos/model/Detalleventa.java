package com.javagu.pos.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the detalleventa database table.
 * 
 */
@Entity
@NamedQuery(name="Detalleventa.findAll", query="SELECT d FROM Detalleventa d")
public class Detalleventa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigo;

	private int cantidad;

	private int codProducto;

	private int codVenta;

	public Detalleventa() {
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getCodProducto() {
		return this.codProducto;
	}

	public void setCodProducto(int codProducto) {
		this.codProducto = codProducto;
	}

	public int getCodVenta() {
		return this.codVenta;
	}

	public void setCodVenta(int codVenta) {
		this.codVenta = codVenta;
	}

}