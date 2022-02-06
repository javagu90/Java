package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the factura database table.
 * 
 */
@Entity
@Table(name = "factura")
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f"),
    @NamedQuery(name = "Factura.findByIdfactura", query = "SELECT f FROM Factura f WHERE f.idfactura = :idfactura"),
    @NamedQuery(name = "Factura.findByIdstatus", query = "SELECT f FROM Factura f WHERE f.status = :idstatus"),
    @NamedQuery(name = "Factura.findByFecha", query = "SELECT f FROM Factura f WHERE f.fecha = :fecha"),
    @NamedQuery(name = "Factura.findBySello", query = "SELECT f FROM Factura f WHERE f.sello = :sello"),
    @NamedQuery(name = "Factura.findByCadenaoriginal", query = "SELECT f FROM Factura f WHERE f.cadenaoriginal = :cadenaoriginal"),
    @NamedQuery(name = "Factura.findByAddenda", query = "SELECT f FROM Factura f WHERE f.addenda = :addenda"),
    @NamedQuery(name = "Factura.findByXml", query = "SELECT f FROM Factura f WHERE f.xml = :xml"),
    @NamedQuery(name = "Factura.findByComentarios", query = "SELECT f FROM Factura f WHERE f.comentarios = :comentarios")})

public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static String FIND_ALL                = "Factura.findAll";
	public static String FIND_BY_ID              = "Factura.findByIdfactura";
	public static String FIND_BY_ID_STATUS       = "Factura.findByIdstatus";
	public static String FIND_BY_FECHA           = "Factura.findByFecha";
	public static String FIND_BY_SELLO           = "Factura.findBySello";
	public static String FIND_BY_CADENA_ORIGINAL = "Factura.findByCadenaoriginal";
	public static String FIND_BY_ADDENDA         = "Factura.findByAddenda";
	public static String FIND_BY_XML             = "Factura.findByXml";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idfactura;

	private String addenda;

	private String cadenaoriginal;

	private String comentarios;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private String sello;

	private String status;

	private String xml;

	//bi-directional many-to-one association to Venta
	@ManyToOne
	@JoinColumn(name="idventa")
	private Venta venta;

	public Factura() {
	}

	public Integer getIdfactura() {
		return this.idfactura;
	}

	public void setIdfactura(Integer idfactura) {
		this.idfactura = idfactura;
	}

	public String getAddenda() {
		return this.addenda;
	}

	public void setAddenda(String addenda) {
		this.addenda = addenda;
	}

	public String getCadenaoriginal() {
		return this.cadenaoriginal;
	}

	public void setCadenaoriginal(String cadenaoriginal) {
		this.cadenaoriginal = cadenaoriginal;
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

	public String getSello() {
		return this.sello;
	}

	public void setSello(String sello) {
		this.sello = sello;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getXml() {
		return this.xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public Venta getVenta() {
		return this.venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

}