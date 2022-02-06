package com.alidasoftware.pos.helper;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PagoVentaHelper implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 353532615016134467L;
	@Id
	private int idFormaPago;
	@Column
	private float cantidad;
	
	 public PagoVentaHelper() {}
	 
	 public PagoVentaHelper(int idFormaPago, float cantidad) {
	  this.idFormaPago = idFormaPago;
	  this.cantidad = cantidad;
	 }
	
	public int getIdFormaPago() {
		return idFormaPago;
	}
	
	public void setIdFormaPago(int idFormaPago) {
		this.idFormaPago = idFormaPago;
	}
	
	public float getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	
	
}
