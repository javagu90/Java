package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the devolucion database table.
 * 
 */
@Entity
@Table(name = "devolucion")
@NamedQueries({
    @NamedQuery(name = "Devolucion.findAll", query = "SELECT d FROM Devolucion d"),
    @NamedQuery(name = "Devolucion.findByIddevolucion", query = "SELECT d FROM Devolucion d WHERE d.iddevolucion = :iddevolucion"),
    @NamedQuery(name = "Devolucion.findByIdoperacion", query = "SELECT d FROM Devolucion d WHERE d.idoperacion = :idoperacion"),
    @NamedQuery(name = "Devolucion.findByOperacion", query = "SELECT d FROM Devolucion d WHERE d.operacion = :operacion"),
    @NamedQuery(name = "Devolucion.findByMotivo", query = "SELECT d FROM Devolucion d WHERE d.motivo = :motivo"),
    @NamedQuery(name = "Devolucion.findByFecha", query = "SELECT d FROM Devolucion d WHERE d.fecha = :fecha"),
    @NamedQuery(name = "Devolucion.findByComentarios", query = "SELECT d FROM Devolucion d WHERE d.comentarios = :comentarios")})
public class Devolucion implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static String FIND_ALL             = "Devolucion.findAll";
	public static String FIND_BY_ID           = "Devolucion.findByIddevolucion";
	public static String FIND_BY_ID_OPERACION = "Devolucion.findByIdoperacion";
	public static String FIND_BY_OPERACION    = "Devolucion.findByOperacion";
	public static String FIND_BY_MOTIVO       = "Devolucion.findByMotivo";
	public static String FIND_BY_FECHA        = "Devolucion.findByFecha";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer iddevolucion;

	private String comentarios;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private Integer idoperacion;

	private String motivo;

	private String operacion;

	//bi-directional many-to-one association to Detalledevolucion
	@OneToMany(mappedBy="devolucion", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	private List<Detalledevolucion> detalledevoluciones;

	public Devolucion() {
	}

	public Integer getIddevolucion() {
		return this.iddevolucion;
	}

	public void setIddevolucion(Integer iddevolucion) {
		this.iddevolucion = iddevolucion;
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

	public Integer getIdoperacion() {
		return this.idoperacion;
	}

	public void setIdoperacion(Integer idoperacion) {
		this.idoperacion = idoperacion;
	}

	public String getMotivo() {
		return this.motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getOperacion() {
		return this.operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public List<Detalledevolucion> getDetalledevoluciones() {
		return this.detalledevoluciones;
	}

	public void setDetalledevoluciones(List<Detalledevolucion> detalledevolucions) {
		this.detalledevoluciones = detalledevolucions;
	}

	public Detalledevolucion addDetalledevolucion(Detalledevolucion detalledevolucion) {
		getDetalledevoluciones().add(detalledevolucion);
		detalledevolucion.setDevolucion(this);

		return detalledevolucion;
	}

	public Detalledevolucion removeDetalledevolucion(Detalledevolucion detalledevolucion) {
		getDetalledevoluciones().remove(detalledevolucion);
		detalledevolucion.setDevolucion(null);

		return detalledevolucion;
	}

}