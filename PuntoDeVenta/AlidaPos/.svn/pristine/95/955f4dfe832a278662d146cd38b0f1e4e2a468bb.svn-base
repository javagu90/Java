package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the caja database table.
 * 
 */
@Entity
@Table(name = "caja")
@NamedQueries({
    @NamedQuery(name = "Caja.findAll", query = "SELECT c FROM Caja c"),
    @NamedQuery(name = "Caja.findByIdcaja", query = "SELECT c FROM Caja c WHERE c.idcaja = :idcaja"),
    @NamedQuery(name = "Caja.findByClave", query = "SELECT c FROM Caja c WHERE UPPER(c.clave) = UPPER(:clave)"),
    @NamedQuery(name = "Caja.findByClaveIdTienda", query = "SELECT c FROM Caja c WHERE UPPER(c.clave) = UPPER(:clave) and c.tienda.idtienda = :idtienda"),
    @NamedQuery(name = "Caja.findByNombre", query = "SELECT c FROM Caja c WHERE UPPER(c.nombre) = UPPER(:nombre)"),
    @NamedQuery(name = "Caja.listByTienda", query = "SELECT c FROM Caja c WHERE c.tienda.idtienda = :idtienda and c.activo = true"),
    @NamedQuery(name = "Caja.listByClave", query = "SELECT c FROM Caja c WHERE UPPER(c.clave) LIKE UPPER(:clave)"),
    @NamedQuery(name = "Caja.listByNombre", query = "SELECT c FROM Caja c WHERE UPPER(c.nombre) LIKE UPPER(:nombre)"),
    @NamedQuery(name = "Caja.listByClaveNombre", query = "SELECT c FROM Caja c WHERE UPPER(c.clave) LIKE UPPER(:clave) and c.nombre like UPPER(:nombre)"),
    @NamedQuery(name = "Caja.listByClaveTienda", query = "SELECT c FROM Caja c WHERE UPPER(c.clave) LIKE UPPER(:clave) and c.tienda.idtienda = :idtienda"),
    @NamedQuery(name = "Caja.listByNombreTienda", query = "SELECT c FROM Caja c WHERE UPPER(c.nombre) LIKE UPPER(:nombre) and c.tienda.idtienda = :idtienda"),
    @NamedQuery(name = "Caja.listByClaveNombreTienda", query = "SELECT c FROM Caja c WHERE UPPER(c.clave) LIKE UPPER(:clave) and UPPER(c.nombre) like UPPER(:nombre) and c.tienda.idtienda = :idtienda"),
    @NamedQuery(name = "Caja.findByComentarios", query = "SELECT c FROM Caja c WHERE c.comentarios = :comentarios")})

public class Caja implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static String FIND_ALL       = "Caja.findAll";
	public static String FIND_BY_ID     = "Caja.findByIdcaja";
	public static String FIND_BY_CLAVE  = "Caja.findByClave";
	public static String FIND_BY_CLAVE_IDTIENDA = "Caja.findByClaveIdTienda";
	public static String FIND_BY_NOMBRE = "Caja.findByNombre";
	public static String LIST_BY_TIENDA = "Caja.listByTienda";
	public static String LIST_BY_CLAVE  = "Caja.listByClave";
	public static String LIST_BY_CLAVE_NOMBRE  = "Caja.listByClaveNombre";
	public static String LIST_BY_CLAVE_TIENDA  = "Caja.listByClaveTienda";
	public static String LIST_BY_NOMBRE_TIENDA  = "Caja.listByNombreTienda";
	public static String LIST_BY_CLAVE_NOMBRE_TIENDA  = "Caja.listByClaveNombreTienda";
	public static String LIST_BY_NOMBRE = "Caja.listByNombre";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idcaja;

	private String clave;

	private String comentarios;

	private String nombre;
	
	private boolean activo;

	//bi-directional many-to-one association to Cortecaja
	@OneToMany(mappedBy="caja")
	private List<Cortecaja> cortecajas;

	/*
	//bi-directional many-to-one association to Venta
	@OneToMany(mappedBy="caja")
	private List<Venta> ventas;
	
	//bi-directional many-to-one association to Apartado
	@OneToMany(mappedBy="caja")
	private List<Apartado> apartados;
	*/
	
	//bi-directional many-to-one association to Cajaefectivo
	@OneToMany(mappedBy="caja")
	private List<Cajaefectivo> cajaefectivo;
	
	//bi-directional many-to-one association to Tienda
	@ManyToOne
	@JoinColumn(name="idtienda")
	private Tienda tienda;

	public Caja() {
	}

	public Integer getIdcaja() {
		return this.idcaja;
	}

	public void setIdcaja(Integer idcaja) {
		this.idcaja = idcaja;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Cortecaja> getCortecajas() {
		return this.cortecajas;
	}

	public void setCortecajas(List<Cortecaja> cortecajas) {
		this.cortecajas = cortecajas;
	}

	public Cortecaja addCortecaja(Cortecaja cortecaja) {
		getCortecajas().add(cortecaja);
		cortecaja.setCaja(this);

		return cortecaja;
	}

	public Cortecaja removeCortecaja(Cortecaja cortecaja) {
		getCortecajas().remove(cortecaja);
		cortecaja.setCaja(null);

		return cortecaja;
	}
	/*
	public List<Venta> getVentas() {
		return this.ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	public Venta addVenta(Venta venta) {
		getVentas().add(venta);
		venta.setCaja(this);

		return venta;
	}

	public Venta removeVenta(Venta venta) {
		getVentas().remove(venta);
		venta.setCaja(null);

		return venta;
	}
	
	public List<Apartado> getApartados() {
		return this.apartados;
	}

	public void setApartados(List<Apartado> apartados) {
		this.apartados = apartados;
	}

	public Apartado addApartado(Apartado apartado) {
		getApartados().add(apartado);
		apartado.setCaja(this);

		return apartado;
	}

	public Apartado removeApartado(Apartado apartado) {
		getApartados().remove(apartado);
		apartado.setCaja(null);

		return apartado;
	}
	*/
	
	public List<Cajaefectivo> getCajaEfectivo() {
		return this.cajaefectivo;
	}

	public void setCajaEfectivo(List<Cajaefectivo> cajaefectivo) {
		this.cajaefectivo = cajaefectivo;
	}

	public Cajaefectivo addCajaEfectivo(Cajaefectivo cajaefectivo) {
		getCajaEfectivo().add(cajaefectivo);
		cajaefectivo.setCaja(this);

		return cajaefectivo;
	}

	public Cajaefectivo removeCajaefectivo(Cajaefectivo cajaefectivo) {
		getCajaEfectivo().remove(cajaefectivo);
		cajaefectivo.setCaja(null);

		return cajaefectivo;
	}
	
	public Tienda getTienda() {
		return this.tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}