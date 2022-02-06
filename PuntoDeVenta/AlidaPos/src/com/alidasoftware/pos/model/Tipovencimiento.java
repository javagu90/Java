package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the tipovencimiento database table.
 * 
 */
@Entity
@Table(name = "tipovencimiento")
@NamedQueries({
    @NamedQuery(name = "Tipovencimiento.findAll", query = "SELECT t FROM Tipovencimiento t"),
    @NamedQuery(name = "Tipovencimiento.findByIdtipovencimiento", query = "SELECT t FROM Tipovencimiento t WHERE t.idtipovencimiento = :idtipovencimiento"),
    @NamedQuery(name = "Tipovencimiento.findByNombre", query = "SELECT t FROM Tipovencimiento t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tipovencimiento.findByDescripcion", query = "SELECT t FROM Tipovencimiento t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "Tipovencimiento.findByDias", query = "SELECT t FROM Tipovencimiento t WHERE t.dias = :dias"),
    @NamedQuery(name = "Tipovencimiento.findByTipodias", query = "SELECT t FROM Tipovencimiento t WHERE t.tipodias = :tipodias"),
    @NamedQuery(name = "Tipovencimiento.findByStatus", query = "SELECT v FROM Tipovencimiento v WHERE v.activo = :activo"),
    @NamedQuery(name = "Tipovencimiento.findByComentarios", query = "SELECT t FROM Tipovencimiento t WHERE t.comentarios = :comentarios")})
public class Tipovencimiento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static String FIND_ALL             = "Tipovencimiento.findAll";
	public static String FIND_BY_ID           = "Tipovencimiento.findByIdtipovencimiento";
	public static String FIND_BY_NOMBRE       = "Tipovencimiento.findByNombre";
	public static String FIND_BY_DESCRIPCION  = "Tipovencimiento.findByDescripcion";
	public static String FIND_BY_DIAS         = "Tipovencimiento.findByDias";	
	public static String FIND_BY_TIPO_DIAS    = "Tipovencimiento.findByTipodias";
	public static String FIND_BY_STATUS       = "Tipovencimiento.findByStatus";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idtipovencimiento;

	private String comentarios;

	private String descripcion;

	private Integer dias;

	private String nombre;

	private String tipodias;
	
	private boolean activo;

	//bi-directional many-to-one association to Apartado
	@OneToMany(mappedBy="tipovencimiento")
	private List<Apartado> apartados;

	//bi-directional many-to-one association to Vale
	@OneToMany(mappedBy="tipovencimiento")
	private List<Vale> vales;

	//bi-directional many-to-one association to Vista
	@OneToMany(mappedBy="tipovencimiento")
	private List<Vista> vistas;

	public Tipovencimiento() {
	}

	public Integer getIdtipovencimiento() {
		return this.idtipovencimiento;
	}

	public void setIdtipovencimiento(Integer idtipovencimiento) {
		this.idtipovencimiento = idtipovencimiento;
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

	public Integer getDias() {
		return this.dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipodias() {
		return this.tipodias;
	}

	public void setTipodias(String tipodias) {
		this.tipodias = tipodias;
	}
	
	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public List<Apartado> getApartados() {
		return this.apartados;
	}

	public void setApartados(List<Apartado> apartados) {
		this.apartados = apartados;
	}

	public Apartado addApartado(Apartado apartado) {
		getApartados().add(apartado);
		apartado.setTipovencimiento(this);

		return apartado;
	}

	public Apartado removeApartado(Apartado apartado) {
		getApartados().remove(apartado);
		apartado.setTipovencimiento(null);

		return apartado;
	}

	public List<Vale> getVales() {
		return this.vales;
	}

	public void setVales(List<Vale> vales) {
		this.vales = vales;
	}

	public Vale addVale(Vale vale) {
		getVales().add(vale);
		vale.setTipovencimiento(this);

		return vale;
	}

	public Vale removeVale(Vale vale) {
		getVales().remove(vale);
		vale.setTipovencimiento(null);

		return vale;
	}
	
	public List<Vista> getVistas() {
		return this.vistas;
	}

	public void setVistas(List<Vista> vistas) {
		this.vistas = vistas;
	}

	public Vista addVista(Vista vista) {
		getVistas().add(vista);
		vista.setTipovencimiento(this);

		return vista;
	}

	public Vista removeVista(Vista vista) {
		getVistas().remove(vista);
		vista.setTipovencimiento(null);

		return vista;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this.idtipovencimiento == ((Tipovencimiento) obj).idtipovencimiento) {
	        return true;
	    }else {
	        return false;
	    }
	}	
	
}