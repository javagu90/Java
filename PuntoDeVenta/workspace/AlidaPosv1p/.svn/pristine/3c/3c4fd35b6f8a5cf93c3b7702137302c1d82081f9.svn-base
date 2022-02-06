package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cortecaja database table.
 * 
 */
@Entity
@Table(name = "cortecaja")
@NamedQueries({
    @NamedQuery(name = "Cortecaja.findAll", query = "SELECT c FROM Cortecaja c"),
    @NamedQuery(name = "Cortecaja.findByIdcortecaja", query = "SELECT c FROM Cortecaja c WHERE c.idcortecaja = :idcortecaja"),
    @NamedQuery(name = "Cortecaja.findByIdcaja", query = "SELECT c FROM Cortecaja c WHERE c.caja = :idcaja"),
    @NamedQuery(name = "Cortecaja.findByIdsupervisor", query = "SELECT c FROM Cortecaja c WHERE c.usuario1 = :idsupervisor"),
    @NamedQuery(name = "Cortecaja.findByIdusuario", query = "SELECT c FROM Cortecaja c WHERE c.usuario2 = :idusuario"),
    @NamedQuery(name = "Cortecaja.findByFecha", query = "SELECT c FROM Cortecaja c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Cortecaja.findByHorainicio", query = "SELECT c FROM Cortecaja c WHERE c.horainicio = :horainicio"),
    @NamedQuery(name = "Cortecaja.findByHorafin", query = "SELECT c FROM Cortecaja c WHERE c.horafin = :horafin"),
    @NamedQuery(name = "Cortecaja.findByCantidadinicio", query = "SELECT c FROM Cortecaja c WHERE c.cantidadinicio = :cantidadinicio"),
    @NamedQuery(name = "Cortecaja.findByCantidadfincaja", query = "SELECT c FROM Cortecaja c WHERE c.cantidadfincaja = :cantidadfincaja"),
    @NamedQuery(name = "Cortecaja.findByComentarios", query = "SELECT c FROM Cortecaja c WHERE c.comentarios = :comentarios")})
public class Cortecaja implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static String FIND_ALL                  = "Cortecaja.findAll";
	public static String FIND_BY_ID                = "Cortecaja.findByIdcortecaja";
	public static String FIND_BY_ID_CAJA           = "Cortecaja.findByIdcaja";
	public static String FIND_BY_ID_SUPERVISOR     = "Cortecaja.findByIdsupervisor";
	public static String FIND_BY_ID_USUARIO        = "Cortecaja.findByIdusuario";
	public static String FIND_BY_FECHA             = "Cortecaja.findByFecha";
	public static String FIND_BY_HORA_INICIO       = "Cortecaja.findByHorainicio";
	public static String FIND_BY_HORA_FIN          = "Cortecaja.findByHorafin";
	public static String FIND_BY_CANTIDAD_INICIO   = "Cortecaja.findByCantidadinicio";
	public static String FIND_BY_CANTIDAD_FIN_CAJA = "Cortecaja.findByCantidadfincaja";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idcortecaja;

	private float cantidadfincaja;

	private float cantidadinicio;

	private String comentarios;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private Time horafin;

	private Time horainicio;

	//bi-directional many-to-one association to Caja
	@ManyToOne
	@JoinColumn(name="idcaja")
	private Caja caja;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idusuario")
	private Usuario usuario1;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idsupervisor")
	private Usuario usuario2;

	//bi-directional many-to-one association to Corteparcial
	@OneToMany(mappedBy="cortecaja")
	private List<Corteparcial> corteparcials;

	//bi-directional many-to-one association to Detallecortecaja
	@OneToMany(mappedBy="cortecaja")
	private List<Detallecortecaja> detallecortecajas;

	public Cortecaja() {
	}

	public Integer getIdcortecaja() {
		return this.idcortecaja;
	}

	public void setIdcortecaja(Integer idcortecaja) {
		this.idcortecaja = idcortecaja;
	}

	public float getCantidadfincaja() {
		return this.cantidadfincaja;
	}

	public void setCantidadfincaja(float cantidadfincaja) {
		this.cantidadfincaja = cantidadfincaja;
	}

	public float getCantidadinicio() {
		return this.cantidadinicio;
	}

	public void setCantidadinicio(float cantidadinicio) {
		this.cantidadinicio = cantidadinicio;
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

	public Caja getCaja() {
		return this.caja;
	}

	public void setCaja(Caja caja) {
		this.caja = caja;
	}

	public Usuario getUsuario1() {
		return this.usuario1;
	}

	public void setUsuario1(Usuario usuario1) {
		this.usuario1 = usuario1;
	}

	public Usuario getUsuario2() {
		return this.usuario2;
	}

	public void setUsuario2(Usuario usuario2) {
		this.usuario2 = usuario2;
	}

	public List<Corteparcial> getCorteparcials() {
		return this.corteparcials;
	}

	public void setCorteparcials(List<Corteparcial> corteparcials) {
		this.corteparcials = corteparcials;
	}

	public Corteparcial addCorteparcial(Corteparcial corteparcial) {
		getCorteparcials().add(corteparcial);
		corteparcial.setCortecaja(this);

		return corteparcial;
	}

	public Corteparcial removeCorteparcial(Corteparcial corteparcial) {
		getCorteparcials().remove(corteparcial);
		corteparcial.setCortecaja(null);

		return corteparcial;
	}

	public List<Detallecortecaja> getDetallecortecajas() {
		return this.detallecortecajas;
	}

	public void setDetallecortecajas(List<Detallecortecaja> detallecortecajas) {
		this.detallecortecajas = detallecortecajas;
	}

	public Detallecortecaja addDetallecortecaja(Detallecortecaja detallecortecaja) {
		getDetallecortecajas().add(detallecortecaja);
		detallecortecaja.setCortecaja(this);

		return detallecortecaja;
	}

	public Detallecortecaja removeDetallecortecaja(Detallecortecaja detallecortecaja) {
		getDetallecortecajas().remove(detallecortecaja);
		detallecortecaja.setCortecaja(null);

		return detallecortecaja;
	}

}