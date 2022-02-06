package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the inventariofisico database table.
 * 
 */
@Entity
@Table(name = "inventariofisico")
@NamedQueries({
    @NamedQuery(name = "Inventariofisico.findAll", query = "SELECT i FROM Inventariofisico i"),
    @NamedQuery(name = "Inventariofisico.findByIdinventariofisico", query = "SELECT i FROM Inventariofisico i WHERE i.idinventariofisico = :idinventariofisico"),
    @NamedQuery(name = "Inventariofisico.findByIdusuario", query = "SELECT i FROM Inventariofisico i WHERE i.usuario = :idusuario"),
    @NamedQuery(name = "Inventariofisico.findByFechaInicio", query = "SELECT i FROM Inventariofisico i WHERE i.fechaInicio = :fecha"),
    @NamedQuery(name = "Inventariofisico.findByFechaFin", query = "SELECT i FROM Inventariofisico i WHERE i.fechaFin = :fecha"),
    @NamedQuery(name = "Inventariofisico.findByHorainicio", query = "SELECT i FROM Inventariofisico i WHERE i.horainicio = :horainicio"),
    @NamedQuery(name = "Inventariofisico.findByHorafin", query = "SELECT i FROM Inventariofisico i WHERE i.horafin = :horafin"),
    @NamedQuery(name = "Inventariofisico.findByCostototal", query = "SELECT i FROM Inventariofisico i WHERE i.costototal = :costototal"),
    @NamedQuery(name = "Inventariofisico.findByComentarios", query = "SELECT i FROM Inventariofisico i WHERE i.comentarios = :comentarios")})

public class Inventariofisico implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static String FIND_ALL            = "Inventariofisico.findAll";
	public static String FIND_BY_ID          = "Inventariofisico.findByIdinventariofisico";
	public static String FIND_BY_ID_USUARIO  = "Inventariofisico.findByIdusuario";
	public static String FIND_BY_FECHA_INICIO   = "Inventariofisico.findByFechaInicio";
	public static String FIND_BY_FECHA_FIN		= "Inventariofisico.findByFechaFin";
	public static String FIND_BY_HORA_INICIO = "Inventariofisico.findByHorainicio";
	public static String FIND_BY_HORA_FIN    = "Inventariofisico.findByHorafin";
	public static String FIND_BY_COSTO_TOTAL = "Inventariofisico.findByCostototal";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idinventariofisico;

	private String comentarios;

	private float costototal;

	@Temporal(TemporalType.DATE)
	private Date fechaInicio;
	
	@Temporal(TemporalType.DATE)
	private Date fechaFin;

	private Time horafin;

	private Time horainicio;

	//bi-directional many-to-one association to Detalleinventariofisico
	@OneToMany(mappedBy="inventariofisico", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER )
	private List<Detalleinventariofisico> detalleinventariofisicos;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idusuario")
	private Usuario usuario;

	public Inventariofisico() {
	}

	public Integer getIdinventariofisico() {
		return this.idinventariofisico;
	}

	public void setIdinventariofisico(Integer idinventariofisico) {
		this.idinventariofisico = idinventariofisico;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public float getCostototal() {
		return this.costototal;
	}

	public void setCostototal(float costototal) {
		this.costototal = costototal;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Time getHorafin() {
		return this.horafin;
	}

	public void setHorafin(Time horafin) {
		this.horafin = horafin;
	}

	public Time getHorainicio() {
		return this.horainicio;
	}

	public void setHorainicio(Time horainicio) {
		this.horainicio = horainicio;
	}

	public List<Detalleinventariofisico> getDetalleinventariofisicos() {
		return this.detalleinventariofisicos;
	}

	public void setDetalleinventariofisicos(List<Detalleinventariofisico> detalleinventariofisicos) {
		this.detalleinventariofisicos = detalleinventariofisicos;
	}

	public Detalleinventariofisico addDetalleinventariofisico(Detalleinventariofisico detalleinventariofisico) {
		getDetalleinventariofisicos().add(detalleinventariofisico);
		detalleinventariofisico.setInventariofisico(this);

		return detalleinventariofisico;
	}

	public Detalleinventariofisico removeDetalleinventariofisico(Detalleinventariofisico detalleinventariofisico) {
		getDetalleinventariofisicos().remove(detalleinventariofisico);
		detalleinventariofisico.setInventariofisico(null);

		return detalleinventariofisico;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

}