package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the corteparcial database table.
 * 
 */
@Entity
@Table(name = "corteparcial")
@NamedQueries({
    @NamedQuery(name = "Corteparcial.findAll", query = "SELECT c FROM Corteparcial c"),
    @NamedQuery(name = "Corteparcial.findByIdcorteparcial", query = "SELECT c FROM Corteparcial c WHERE c.idcorteparcial = :idcorteparcial"),
    @NamedQuery(name = "Corteparcial.findByIdusuario", query = "SELECT c FROM Corteparcial c WHERE c.usuario1 = :idusuario"),
    @NamedQuery(name = "Corteparcial.findByIdsupervisor", query = "SELECT c FROM Corteparcial c WHERE c.usuario2 = :idsupervisor"),
    @NamedQuery(name = "Corteparcial.findByIdcortecaja", query = "SELECT c FROM Corteparcial c WHERE c.cortecaja = :idcortecaja"),
    @NamedQuery(name = "Corteparcial.findByFecha", query = "SELECT c FROM Corteparcial c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Corteparcial.findByHora", query = "SELECT c FROM Corteparcial c WHERE c.hora = :hora"),
    @NamedQuery(name = "Corteparcial.findByCantidadretirada", query = "SELECT c FROM Corteparcial c WHERE c.cantidadretirada = :cantidadretirada"),
    @NamedQuery(name = "Corteparcial.findByComentarios", query = "SELECT c FROM Corteparcial c WHERE c.comentarios = :comentarios")})
public class Corteparcial implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static String FIND_ALL                  = "Corteparcial.findAll";
	public static String FIND_BY_ID                = "Corteparcial.findByIdcorteparcial";
	public static String FIND_BY_ID_USUARIO        = "Corteparcial.findByIdusuario";
	public static String FIND_BY_ID_SUPERVIDOR     = "Corteparcial.findByIdsupervisor";
	public static String FIND_BY_ID_CORTE_CAJA     = "Corteparcial.findByIdcortecaja";
	public static String FIND_BY_FECHA             = "Corteparcial.findByFecha";
	public static String FIND_BY_HORA              = "Corteparcial.findByHora";
	public static String FIND_BY_CANTIDAD_RETIRADA = "Corteparcial.findByCantidadretirada";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idcorteparcial;

	private float cantidadretirada;

	private String comentarios;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private Time hora;

	//bi-directional many-to-one association to Cortecaja
	@ManyToOne
	@JoinColumn(name="idcortecaja")
	private Cortecaja cortecaja;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idusuario")
	private Usuario usuario1;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idsupervisor")
	private Usuario usuario2;

	public Corteparcial() {
	}

	public Integer getIdcorteparcial() {
		return this.idcorteparcial;
	}

	public void setIdcorteparcial(Integer idcorteparcial) {
		this.idcorteparcial = idcorteparcial;
	}

	public float getCantidadretirada() {
		return this.cantidadretirada;
	}

	public void setCantidadretirada(float cantidadretirada) {
		this.cantidadretirada = cantidadretirada;
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

	public Time getHora() {
		return this.hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public Cortecaja getCortecaja() {
		return this.cortecaja;
	}

	public void setCortecaja(Cortecaja cortecaja) {
		this.cortecaja = cortecaja;
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

}