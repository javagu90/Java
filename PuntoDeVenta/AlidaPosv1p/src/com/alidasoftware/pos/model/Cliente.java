package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@Table(name = "cliente")
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByIdcliente", query = "SELECT c FROM Cliente c WHERE c.idcliente = :idcliente"),
    @NamedQuery(name = "Cliente.findByIdcategoriacliente", query = "SELECT c FROM Cliente c WHERE c.categoriacliente = :idcategoriacliente"),
    @NamedQuery(name = "Cliente.findByIdpersona", query = "SELECT c FROM Cliente c WHERE c.persona = :idpersona"),
    @NamedQuery(name = "Cliente.findByUltimaoperacion", query = "SELECT c FROM Cliente c WHERE c.ultimaoperacion = :ultimaoperacion"),
    @NamedQuery(name = "Cliente.findByClavecliente", query = "SELECT c FROM Cliente c WHERE c.clavecliente = :clavecliente"),
    @NamedQuery(name = "Cliente.findLikeClavecliente", query = "SELECT c FROM Cliente c WHERE c.clavecliente LIKE :clavecliente"),
    @NamedQuery(name = "Cliente.findByComentarios", query = "SELECT c FROM Cliente c WHERE c.comentarios = :comentarios")})

public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static String FIND_ALL                     = "Cliente.findAll";
	public static String FIND_BY_ID                   = "Cliente.findByIdcliente";
	public static String FIND_BY_ID_CATEGORIA_CLIENTE = "Cliente.findByIdcategoriacliente";
	public static String FIND_BY_ID_PERSONA           = "Cliente.findByIdpersona";
	public static String FIND_BY_ULTIMA_OPERACION     = "Cliente.findByUltimaoperacion";
	public static String FIND_BY_CLAVE_CLIENTE        = "Cliente.findByClavecliente";
	public static String FIND_LIKE_CLAVE_CLIENTE      = "Cliente.findLikeClavecliente";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idcliente;
	private String clavecliente;
	private String comentarios;
	@Temporal(TemporalType.DATE)
	private Date ultimaoperacion;
	private Boolean credito;
	private float limitecredito;
	private float creditodisponible;
	private boolean activo;
	
	//bi-directional many-to-one association to Apartado
	@OneToMany(mappedBy="cliente")
	private List<Apartado> apartados;
	//bi-directional many-to-one association to Categoriacliente
	@ManyToOne
	@JoinColumn(name="idcategoriacliente")
	private Categoriacliente categoriacliente;
	//bi-directional many-to-one association to Persona
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="idpersona")
	private Persona persona;
	//bi-directional many-to-one association to Vale
	@OneToMany(mappedBy="cliente")
	private List<Vale> vales;
	//bi-directional many-to-one association to Venta
	@OneToMany(mappedBy="cliente")
	private List<Venta> ventas;
	//bi-directional many-to-one association to Vista
	@OneToMany(mappedBy="cliente")
	private List<Vista> vistas;

	public Cliente() {
	}

	
	public Boolean getCredito() {
		return credito;
	}

	public void setCredito(Boolean credito) {
		this.credito = credito;
	}


	public float getLimitecredito() {
		return limitecredito;
	}


	public void setLimitecredito(float limitecredito) {
		this.limitecredito = limitecredito;
	}


	public float getCreditodisponible() {
		return creditodisponible;
	}


	public void setCreditodisponible(float creditodisponible) {
		this.creditodisponible = creditodisponible;
	}


	public Integer getIdcliente() {
		return this.idcliente;
	}

	public void setIdcliente(Integer idcliente) {
		this.idcliente = idcliente;
	}

	public String getClavecliente() {
		return this.clavecliente;
	}

	public void setClavecliente(String clavecliente) {
		this.clavecliente = clavecliente;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Date getUltimaoperacion() {
		return this.ultimaoperacion;
	}

	public void setUltimaoperacion(Date ultimaoperacion) {
		this.ultimaoperacion = ultimaoperacion;
	}

	public List<Apartado> getApartados() {
		return this.apartados;
	}

	public void setApartados(List<Apartado> apartados) {
		this.apartados = apartados;
	}

	public Apartado addApartado(Apartado apartado) {
		getApartados().add(apartado);
		apartado.setCliente(this);

		return apartado;
	}

	public Apartado removeApartado(Apartado apartado) {
		getApartados().remove(apartado);
		apartado.setCliente(null);

		return apartado;
	}

	public Categoriacliente getCategoriacliente() {
		return this.categoriacliente;
	}

	public void setCategoriacliente(Categoriacliente categoriacliente) {
		this.categoriacliente = categoriacliente;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Vale> getVales() {
		return this.vales;
	}

	public void setVales(List<Vale> vales) {
		this.vales = vales;
	}

	public Vale addVale(Vale vale) {
		getVales().add(vale);
		vale.setCliente(this);

		return vale;
	}

	public Vale removeVale(Vale vale) {
		getVales().remove(vale);
		vale.setCliente(null);

		return vale;
	}

	public List<Venta> getVentas() {
		return this.ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	public Venta addVenta(Venta venta) {
		getVentas().add(venta);
		venta.setCliente(this);

		return venta;
	}

	public Venta removeVenta(Venta venta) {
		getVentas().remove(venta);
		venta.setCliente(null);

		return venta;
	}

	public List<Vista> getVistas() {
		return this.vistas;
	}

	public void setVistas(List<Vista> vistas) {
		this.vistas = vistas;
	}

	public Vista addVista(Vista vista) {
		getVistas().add(vista);
		vista.setCliente(this);

		return vista;
	}

	public Vista removeVista(Vista vista) {
		getVistas().remove(vista);
		vista.setCliente(null);

		return vista;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this.idcliente == ((Cliente) obj).idcliente) {
	        return true;
	    }else {
	        return false;
	    }
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}