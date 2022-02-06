package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the apartado database table.
 * 
 */
@Entity
@Table(name = "apartado")
@NamedQueries({
    @NamedQuery(name = "Apartado.findAll", query = "SELECT a FROM Apartado a"),
    @NamedQuery(name = "Apartado.findByIdapartado", query = "SELECT a FROM Apartado a WHERE a.idapartado = :idapartado"),
    @NamedQuery(name = "Apartado.findByIdcliente", query = "SELECT a FROM Apartado a WHERE a.cliente.idcliente = :idcliente"),
    @NamedQuery(name = "Apartado.findByIdtipovencimiento", query = "SELECT a FROM Apartado a WHERE a.tipovencimiento = :idtipovencimiento"),
    @NamedQuery(name = "Apartado.findByIdstatus", query = "SELECT a FROM Apartado a WHERE a.status = :idstatus"),
    @NamedQuery(name = "Apartado.findByFolio", query = "SELECT a FROM Apartado a WHERE a.folio LIKE :folio"),
    @NamedQuery(name = "Apartado.findByFechaapartado", query = "SELECT a FROM Apartado a WHERE a.fechaapartado = :fechaapartado"),
    @NamedQuery(name = "Apartado.findBySubtotal", query = "SELECT a FROM Apartado a WHERE a.subtotal = :subtotal"),
    @NamedQuery(name = "Apartado.findByIva", query = "SELECT a FROM Apartado a WHERE a.iva = :iva"),
    @NamedQuery(name = "Apartado.findByTotal", query = "SELECT a FROM Apartado a WHERE a.total = :total"),
    @NamedQuery(name = "Apartado.findByStatus", query = "SELECT a FROM Apartado a WHERE a.status = :status"),
    @NamedQuery(name = "Apartado.findByComentarios", query = "SELECT a FROM Apartado a WHERE a.comentarios = :comentarios")})
public class Apartado implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static String FIND_ALL                    = "Apartado.findAll";
	public static String FIND_BY_ID                  = "Apartado.findByIdapartado";
	public static String FIND_BY_ID_CLIENTE          = "Apartado.findByIdcliente";
	public static String FIND_BY_ID_TIPO_VENCIMIENTO = "Apartado.findByIdtipovencimiento";
	public static String FIND_BY_ID_STATUS           = "Apartado.findByIdstatus";
	public static String FIND_BY_FOLIO               = "Apartado.findByFolio";
	public static String FIND_BY_FECHA_APARTADO      = "Apartado.findByFechaapartado";
	public static String FIND_BY_SUBTOTAL            = "Apartado.findBySubtotal";
	public static String FIND_BY_IVA                 = "Apartado.findByIva";
	public static String FIND_BY_TOTAL               = "Apartado.findByTotal";
	public static String FIND_BY_STATUS              = "Apartado.findByStatus";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idapartado;

	private String comentarios;

	@Temporal(TemporalType.DATE)
	private Date fechaapartado;

	private String folio;

	private float iva;

	private int status;

	private float subtotal;

	private float total;
	
	//bi-directional many-to-one association to Tienda
	@ManyToOne
	@JoinColumn(name="idtienda")
	private Tienda tienda;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="idcliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Tipovencimiento
	@ManyToOne
	@JoinColumn(name="idtipovencimiento")
	private Tipovencimiento tipovencimiento;

	//bi-directional many-to-one association to Detalleapartado
	@OneToMany(mappedBy="apartado", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	private List<Detalleapartado> detalleapartados;

	//bi-directional many-to-one association to Pagoapartado
	@OneToMany(mappedBy="apartado", cascade=CascadeType.REFRESH)
	private List<Pagoapartado> pagoapartados;

	public Apartado() {
	}

	public Integer getIdapartado() {
		return this.idapartado;
	}

	public void setIdapartado(Integer idapartado) {
		this.idapartado = idapartado;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Date getFechaapartado() {
		return this.fechaapartado;
	}

	public void setFechaapartado(Date fechaapartado) {
		this.fechaapartado = fechaapartado;
	}

	public String getFolio() {
		return this.folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public float getIva() {
		return this.iva;
	}

	public void setIva(float iva) {
		this.iva = iva;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public float getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}

	public float getTotal() {
		return this.total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Tipovencimiento getTipovencimiento() {
		return this.tipovencimiento;
	}

	public void setTipovencimiento(Tipovencimiento tipovencimiento) {
		this.tipovencimiento = tipovencimiento;
	}

	public List<Detalleapartado> getDetalleapartados() {
		return this.detalleapartados;
	}

	public void setDetalleapartados(List<Detalleapartado> detalleapartados) {
		this.detalleapartados = detalleapartados;
	}

	public Detalleapartado addDetalleapartado(Detalleapartado detalleapartado) {
		getDetalleapartados().add(detalleapartado);
		detalleapartado.setApartado(this);

		return detalleapartado;
	}

	public Detalleapartado removeDetalleapartado(Detalleapartado detalleapartado) {
		getDetalleapartados().remove(detalleapartado);
		detalleapartado.setApartado(null);

		return detalleapartado;
	}

	public List<Pagoapartado> getPagoapartados() {
		return this.pagoapartados;
	}

	public void setPagoapartados(List<Pagoapartado> pagoapartados) {
		this.pagoapartados = pagoapartados;
	}

	public Pagoapartado addPagoapartado(Pagoapartado pagoapartado) {
		getPagoapartados().add(pagoapartado);
		pagoapartado.setApartado(this);

		return pagoapartado;
	}

	public Pagoapartado removePagoapartado(Pagoapartado pagoapartado) {
		getPagoapartados().remove(pagoapartado);
		pagoapartado.setApartado(null);

		return pagoapartado;
	}
	
	public Tienda getTienda() {
		return this.tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

}