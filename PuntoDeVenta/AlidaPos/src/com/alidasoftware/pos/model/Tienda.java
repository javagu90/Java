package com.alidasoftware.pos.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the tienda database table.
 * 
 */
@Entity
@Table(name = "tienda")
@NamedQueries({
    @NamedQuery(name = "Tienda.findAll", query = "SELECT t FROM Tienda t"),
    @NamedQuery(name = "Tienda.findAllActive", query = "SELECT t FROM Tienda t where t.activo = true"),
    @NamedQuery(name = "Tienda.findByIdtienda", query = "SELECT t FROM Tienda t WHERE t.idtienda = :idtienda"),
    @NamedQuery(name = "Tienda.findByNombre", query = "SELECT t FROM Tienda t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tienda.findByDescripcion", query = "SELECT t FROM Tienda t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "Tienda.findByComentarios", query = "SELECT t FROM Tienda t WHERE t.comentarios = :comentarios")})

public class Tienda implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static String FIND_ALL            = "Tienda.findAll";
	public static String FIND_BY_ID          = "Tienda.findByIdtienda";
	public static String FIND_BY_NOMBRE      = "Tienda.findByNombre";
	public static String FIND_BY_DESCRIPCION = "Tienda.findByDescripcion";
	public static String FIND_ALL_ACTIVE	= "Tienda.findAllActive";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idtienda;

	private String comentarios;

	private String descripcion;

	private String nombre;
	
	private boolean activo;

	//bi-directional many-to-one association to Direccion
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="contacto")
	private Contacto contacto;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="responsable")
	private Empleado responsable;

	//bi-directional many-to-one association to Traspaso
	@OneToMany(mappedBy="tienda1")
	private List<Traspaso> traspasos1;

	//bi-directional many-to-one association to Traspaso
	@OneToMany(mappedBy="tienda2")
	private List<Traspaso> traspasos2;	
	
	//bi-directional many-to-one association to Apartado
	@OneToMany(mappedBy="tienda")
	private List<Apartado> apartados;


	public Tienda() {
	}

	public Integer getIdtienda() {
		return this.idtienda;
	}

	public void setIdtienda(Integer idtienda) {
		this.idtienda = idtienda;
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

	public Empleado getResponsable() {
		return this.responsable;
	}

	public void setResponsable(Empleado responsable) {
		this.responsable = responsable;
	}
	
	public Contacto getContacto() {
		return this.contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}
	
	public List<Traspaso> getTraspasos1() {
		return this.traspasos1;
	}

	public void setTraspasos1(List<Traspaso> traspasos1) {
		this.traspasos1 = traspasos1;
	}

	public Traspaso addTraspasos1(Traspaso traspasos1) {
		getTraspasos1().add(traspasos1);
		traspasos1.setTienda1(this);

		return traspasos1;
	}

	public Traspaso removeTraspasos1(Traspaso traspasos1) {
		getTraspasos1().remove(traspasos1);
		traspasos1.setTienda1(null);

		return traspasos1;
	}

	public List<Traspaso> getTraspasos2() {
		return this.traspasos2;
	}

	public void setTraspasos2(List<Traspaso> traspasos2) {
		this.traspasos2 = traspasos2;
	}

	public Traspaso addTraspasos2(Traspaso traspasos2) {
		getTraspasos2().add(traspasos2);
		traspasos2.setTienda2(this);

		return traspasos2;
	}

	public Traspaso removeTraspasos2(Traspaso traspasos2) {
		getTraspasos2().remove(traspasos2);
		traspasos2.setTienda2(null);

		return traspasos2;
	}
	
	public List<Apartado> getApartados() {
		return this.apartados;
	}

	public void setApartados(List<Apartado> apartados) {
		this.apartados = apartados;
	}

	public Apartado addApartados(Apartado apartados) {
		getApartados().add(apartados);
		apartados.setTienda(this);

		return apartados;
	}

	public Apartado removeApartados(Apartado apartados) {
		getApartados().remove(apartados);
		apartados.setTienda(null);

		return apartados;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}