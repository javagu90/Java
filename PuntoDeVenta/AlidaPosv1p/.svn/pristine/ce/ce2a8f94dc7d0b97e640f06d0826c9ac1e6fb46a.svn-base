package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the vale database table.
 * 
 */
@Entity
@Table(name = "vale")
@NamedQueries({
    @NamedQuery(name = "Vale.findAll", query = "SELECT v FROM Vale v"),
    @NamedQuery(name = "Vale.findByIdvale", query = "SELECT v FROM Vale v WHERE v.idvale = :idvale"),
    @NamedQuery(name = "Vale.findByIdtipovencimiento", query = "SELECT v FROM Vale v WHERE v.tipovencimiento = :idtipovencimiento"),
    @NamedQuery(name = "Vale.findByIdcliente", query = "SELECT v FROM Vale v WHERE v.cliente = :idcliente"),
    @NamedQuery(name = "Vale.findByFecha", query = "SELECT v FROM Vale v WHERE v.fecha = :fecha"),
    @NamedQuery(name = "Vale.findByCantidad", query = "SELECT v FROM Vale v WHERE v.cantidad = :cantidad"),
    @NamedQuery(name = "Vale.findByFolio", query = "SELECT v FROM Vale v WHERE v.folio = :folio"),
    @NamedQuery(name = "Vale.findByStatus", query = "SELECT v FROM Vale v WHERE v.status = :status"),
    @NamedQuery(name = "Vale.findByComentarios", query = "SELECT v FROM Vale v WHERE v.comentarios = :comentarios")})
public class Vale implements Serializable {
	private static final long serialVersionUID = 1L;

	public static String FIND_ALL                    = "Vale.findAll";
	public static String FIND_BY_ID                  = "Vale.findByIdvale";
	public static String FIND_BY_ID_TIPO_VENCIMIENTO = "Vale.findByIdtipovencimiento";
	public static String FIND_BY_ID_CLIENTE          = "Vale.findByIdcliente";
	public static String FIND_BY_FECHA               = "Vale.findByFecha";
	public static String FIND_BY_CANTIDAD            = "Vale.findByCantidad";
	public static String FIND_BY_FOLIO               = "Vale.findByFolio";	
	public static String FIND_BY_STATUS              = "Vale.findByStatus";
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idvale;

	private float cantidad;
	
	private String folio;
	
	private Integer status;

	private String comentarios;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="idcliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Tipovencimiento
	@ManyToOne
	@JoinColumn(name="idtipovencimiento")
	private Tipovencimiento tipovencimiento;

	public Vale() {
	}

	public Integer getIdvale() {
		return this.idvale;
	}

	public void setIdvale(Integer idvale) {
		this.idvale = idvale;
	}

	public float getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getFolio() {
		return this.folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}
	
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Tipovencimiento getTipovencimiento() {
		return this.tipovencimiento;
	}

	public void setTipovencimiento(Tipovencimiento tipovencimiento) {
		this.tipovencimiento = tipovencimiento;
	}

}