package com.alidasoftware.pos.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the adeudoventa database table.
 * 
 */
@Entity
@Table(name = "adeudoventa")
@NamedQueries({
	@NamedQuery(name="Adeudoventa.findAll", query="SELECT a FROM AdeudoVenta a"),
	@NamedQuery(name="Adeudoventa.findFolioIdOperacion", query="SELECT a FROM AdeudoVenta a "
			       + "WHERE a.foliooperacion = :foliooperacion AND "
			       + "a.idoperacion = :idoperacion AND "
			       + "a.module = :module")})

public class AdeudoVenta implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static String FIND_ALL                    = "Adeudoventa.findAll";
	public static String FIND_BY_FOLIO_ID_OPERACION  = "Adeudoventa.findFolioIdOperacion";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idadeudoventa;

	private float adeudo;
	
	private String foliooperacion;
	
	private int idoperacion;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	private String module;

	public AdeudoVenta() {
	}

	public Integer getIdadeudoventa() {
		return this.idadeudoventa;
	}

	public void setIdadeudoventa(Integer idadeudoventa) {
		this.idadeudoventa = idadeudoventa;
	}

	public float getAdeudo() {
		return this.adeudo;
	}

	public void setAdeudo(float adeudo) {
		this.adeudo = adeudo;
	}
	
	public String getFoliooperacion() {
		return this.foliooperacion;
	}

	public void setFoliooperacion(String foliooperacion) {
		this.foliooperacion = foliooperacion;
	}
	
	public int getIdoperacion() {
		return this.idoperacion;
	}

	public void setIdoperacion(int idoperacion) {
		this.idoperacion = idoperacion;
	}
	
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public String getModule() {
		return this.module;
	}

	public void setModule(String module) {
		this.module = module;
	}

}