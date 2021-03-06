package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the promocion database table.
 * 
 */
@Entity
@Table(name = "promocion")
@NamedQueries({
    @NamedQuery(name = "Promocion.findAll", query = "SELECT p FROM Promocion p"),
    @NamedQuery(name = "Promocion.findByIdpromocion", query = "SELECT p FROM Promocion p WHERE p.idpromocion = :idpromocion"),
    @NamedQuery(name = "Promocion.findByNombre", query = "SELECT p FROM Promocion p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Promocion.findByDescuento", query = "SELECT p FROM Promocion p WHERE p.descuento = :descuento"),
    @NamedQuery(name = "Promotion.findByIdProduct", query = "SELECT p FROM Promocion p WHERE p.idproducto = :idproducto"),
    @NamedQuery(name = "Promotion.findByIdProductStoreDate", 
    					query = "SELECT p FROM Promocion p WHERE p.idproducto = :idproducto AND p.tienda.idtienda = :idtienda AND p.activa = true AND p.fechainicio <= CURRENT_DATE AND p.fechafin >= CURRENT_DATE"),   					
    @NamedQuery(name = "Promocion.findByComentarios", query = "SELECT p FROM Promocion p WHERE p.comentarios = :comentarios")})
public class Promocion implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static String FIND_ALL                      = "Promocion.findAll";
	public static String FIND_BY_ID                    = "Promocion.findByIdpromocion";
	public static String FIND_BY_NOMBRE                = "Promocion.findByNombre";
	public static String FIND_BY_DESCUENTO             = "Promocion.findByDescuento";
	public static String FIND_BY_ID_PRODUCT            = "Promotion.findByIdProduct";
	public static String FIND_BY_ID_PRODUCT_STORE_DATE = "Promotion.findByIdProductStoreDate";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idpromocion;
	private String comentarios;
	private float descuento;
	@Temporal(TemporalType.DATE)
	private Date fechafin;
	@Temporal(TemporalType.DATE)
	private Date fechainicio;
	private Integer idproducto;
	private String nombre;
	private boolean activa;
	private boolean otrasPromociones;
	//bi-directional many-to-many association to Categoriaproducto
	@ManyToMany
	@JoinTable(
		name="categoriasconpromocion"
		, joinColumns={
			@JoinColumn(name="idpromocion")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idcategoriaproducto")
			}
		)
	private List<Categoriaproducto> categoriaproductos;
	
	@JoinColumn(name="idtienda")
	private Tienda tienda;

	public Promocion() {
	}

	public Integer getIdpromocion() {
		if (this.idpromocion==null) {
			this.idpromocion=0;
		}		
		return this.idpromocion;
	}

	public void setIdpromocion(Integer idpromocion) {
		this.idpromocion = idpromocion;
	}

	public String getComentarios() {
		if (this.comentarios==null) {
			this.comentarios="";
		}
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public float getDescuento() {
		return this.descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public Date getFechafin() {
		if (this.fechafin==null) {
			this.fechafin=new java.util.Date();;
		}
		return this.fechafin;
	}

	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}

	public Date getFechainicio() {
		if (this.fechainicio==null) {
			this.fechainicio=new java.util.Date();;
		}
		return this.fechainicio;
	}

	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}

	public Integer getIdproducto() {
		if (this.idproducto==null) {
			this.idproducto=0;
		}		
		return this.idproducto;
	}

	public void setIdproducto(Integer idproducto) {
		this.idproducto = idproducto;
	}

	public String getNombre() {
		if (this.nombre==null) {
			this.nombre="";
		}
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Categoriaproducto> getCategoriaproductos() {
		if (this.categoriaproductos==null) {
			this.categoriaproductos =new ArrayList<Categoriaproducto>();
		}
		return this.categoriaproductos;
	}

	public void setCategoriaproductos(List<Categoriaproducto> categoriaproductos) {
		this.categoriaproductos = categoriaproductos;
	}

	public boolean getActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	public boolean isOtrasPromociones() {
		return otrasPromociones;
	}

	public void setOtrasPromociones(boolean otrasPromociones) {
		this.otrasPromociones = otrasPromociones;
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}
	
	
}