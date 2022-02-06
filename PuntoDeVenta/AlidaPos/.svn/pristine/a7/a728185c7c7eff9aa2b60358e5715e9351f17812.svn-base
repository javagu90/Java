package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the cajaefectivo database table.
 * 
 */
@Entity
@Table(name = "cajaefectivo")
@NamedQueries({
	@NamedQuery(name = "Cajaefectivo.findAll", query="SELECT c FROM Cajaefectivo c"),
	@NamedQuery(name = "Cajaefectivo.findByIdusuario", query = "SELECT c FROM Cajaefectivo c WHERE c.usuario = :idusuario"),
	@NamedQuery(name = "Cajaefectivo.findByIdcaja", query = "SELECT c FROM Cajaefectivo c WHERE c.caja.idcaja = :idcaja")})
public class Cajaefectivo implements Serializable {
	private static final long serialVersionUID = 1L;

	public static String FIND_BY_ID_CAJA           = "Cajaefectivo.findByIdcaja";
	public static String FIND_BY_ID_USUARIO        = "Cajaefectivo.findByIdusuario";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idcajaefectivo;

	private float efectivo;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	//bi-directional many-to-one association to Caja
	@ManyToOne
	@JoinColumn(name="idcaja")
	private Caja caja;
	
	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idusuario")
	private Usuario usuario;

	public Cajaefectivo() {
	}

	public Integer getIdcajaefectivo() {
		return this.idcajaefectivo;
	}

	public void setIdcajaefectivo(Integer idcajaefectivo) {
		this.idcajaefectivo = idcajaefectivo;
	}

	public float getEfectivo() {
		return this.efectivo;
	}

	public void setEfectivo(float efectivo) {
		this.efectivo = efectivo;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Caja getCaja() {
		return this.caja;
	}

	public void setCaja(Caja caja) {
		this.caja = caja;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}