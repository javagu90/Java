package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the formapago database table.
 * 
 */
@Entity
@Table(name = "formapago")
@NamedQueries({
    @NamedQuery(name = "Formapago.findAll", query = "SELECT f FROM Formapago f"),
    @NamedQuery(name = "Formapago.findByIdformapago", query = "SELECT f FROM Formapago f WHERE f.idformapago = :idformapago"),
    @NamedQuery(name = "Formapago.findByNombre", query = "SELECT f FROM Formapago f WHERE f.nombre = :nombre"),
    @NamedQuery(name = "Formapago.findByDescripcion", query = "SELECT f FROM Formapago f WHERE f.descripcion = :descripcion"),
    @NamedQuery(name = "Formapago.findByComentarios", query = "SELECT f FROM Formapago f WHERE f.comentarios = :comentarios")})
public class Formapago implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static String FIND_ALL            = "Formapago.findAll";
	public static String FIND_BY_ID          = "Formapago.findByIdformapago";
	public static String FIND_BY_NOMBRE      = "Formapago.findByNombre";
	public static String FIND_BY_DESCRIPCION = "Formapago.findByDescripcion";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idformapago;

	private String comentarios;

	private String descripcion;

	private String nombre;

	//bi-directional many-to-one association to Detallecortecaja
	@OneToMany(mappedBy="formapago")
	private List<Detallecortecaja> detallecortecajas;

	//bi-directional many-to-one association to Pagoapartado
	@OneToMany(mappedBy="formapago")
	private List<Pagoapartado> pagoapartados;

	//bi-directional many-to-one association to Pagoventa
	@OneToMany(mappedBy="formapago")
	private List<Pagoventa> pagoventas;

	public Formapago() {
	}

	public Integer getIdformapago() {
		return this.idformapago;
	}

	public void setIdformapago(Integer idformapago) {
		this.idformapago = idformapago;
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

	public List<Detallecortecaja> getDetallecortecajas() {
		return this.detallecortecajas;
	}

	public void setDetallecortecajas(List<Detallecortecaja> detallecortecajas) {
		this.detallecortecajas = detallecortecajas;
	}

	public Detallecortecaja addDetallecortecaja(Detallecortecaja detallecortecaja) {
		getDetallecortecajas().add(detallecortecaja);
		detallecortecaja.setFormapago(this);

		return detallecortecaja;
	}

	public Detallecortecaja removeDetallecortecaja(Detallecortecaja detallecortecaja) {
		getDetallecortecajas().remove(detallecortecaja);
		detallecortecaja.setFormapago(null);

		return detallecortecaja;
	}

	public List<Pagoapartado> getPagoapartados() {
		return this.pagoapartados;
	}

	public void setPagoapartados(List<Pagoapartado> pagoapartados) {
		this.pagoapartados = pagoapartados;
	}

	public Pagoapartado addPagoapartado(Pagoapartado pagoapartado) {
		getPagoapartados().add(pagoapartado);
		pagoapartado.setFormapago(this);

		return pagoapartado;
	}

	public Pagoapartado removePagoapartado(Pagoapartado pagoapartado) {
		getPagoapartados().remove(pagoapartado);
		pagoapartado.setFormapago(null);

		return pagoapartado;
	}

	public List<Pagoventa> getPagoventas() {
		return this.pagoventas;
	}

	public void setPagoventas(List<Pagoventa> pagoventas) {
		this.pagoventas = pagoventas;
	}

	public Pagoventa addPagoventa(Pagoventa pagoventa) {
		getPagoventas().add(pagoventa);
		pagoventa.setFormapago(this);

		return pagoventa;
	}

	public Pagoventa removePagoventa(Pagoventa pagoventa) {
		getPagoventas().remove(pagoventa);
		pagoventa.setFormapago(null);

		return pagoventa;
	}

}