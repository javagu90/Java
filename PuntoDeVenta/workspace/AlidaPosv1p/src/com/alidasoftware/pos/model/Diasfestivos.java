package com.alidasoftware.pos.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the diasfestivos database table.
 * 
 */
@Entity
@Table(name = "diasfestivos")
@NamedQueries({
    @NamedQuery(name = "Diasfestivos.findAll", query = "SELECT d FROM Diasfestivos d"),
    @NamedQuery(name = "Diasfestivos.findByIddiafestivo", query = "SELECT d FROM Diasfestivos d WHERE d.iddiafestivo = :iddiafestivo"),
    @NamedQuery(name = "Diasfestivos.findByFecha", query = "SELECT d FROM Diasfestivos d WHERE d.fecha = :fecha")})
public class Diasfestivos implements Serializable{

	private static final long serialVersionUID = -1152410368969181934L;
	/**
	 * 
	 */
	public static String FIND_ALL       = "Diasfestivos.findAll";
	public static String FIND_BY_ID     = "Diasfestivos.findByIddiafestivo";
	public static String FIND_BY_FECHA  = "Diasfestivos.findByFecha";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer iddiafestivo;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;


	public Diasfestivos() {
	}

	//Getters / Setters
	public Integer getIddiafestivo() {
		return iddiafestivo;
	}
	public void setIddiafestivo(Integer iddiafestivo) {
		this.iddiafestivo = iddiafestivo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
