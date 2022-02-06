package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the venta database table.
 * 
 */
@Entity
@Table(name = "venta")
@NamedQueries({
    @NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v"),
    @NamedQuery(name = "Venta.findByIdventa", query = "SELECT v FROM Venta v WHERE v.idventa = :idventa"),
    @NamedQuery(name = "Venta.findByIdstatus", query = "SELECT v FROM Venta v WHERE v.status = :idstatus"),
    @NamedQuery(name = "Venta.findByIdcliente", query = "SELECT v FROM Venta v WHERE v.cliente = :idcliente"),
    @NamedQuery(name = "Venta.findByIdtienda", query = "SELECT v FROM Venta v WHERE v.tienda.idtienda = :idtienda"),
    @NamedQuery(name = "Venta.findByIdvendedor", query = "SELECT v FROM Venta v WHERE v.usuario = :idvendedor"),
    @NamedQuery(name = "Venta.findByFecha", query = "SELECT v FROM Venta v WHERE v.fecha = :fecha"),
    @NamedQuery(name = "Venta.findByFolio", query = "SELECT v FROM Venta v WHERE v.folio = :folio"),
    @NamedQuery(name = "Venta.findBySubtotal", query = "SELECT v FROM Venta v WHERE v.subtotal = :subtotal"),
    @NamedQuery(name = "Venta.findByIva", query = "SELECT v FROM Venta v WHERE v.iva = :iva"),
    @NamedQuery(name = "Venta.findByTotal", query = "SELECT v FROM Venta v WHERE v.total = :total"),
    @NamedQuery(name = "Venta.findByStatus", query = "SELECT v FROM Venta v WHERE v.status = :status"),
    @NamedQuery(name = "Venta.findByComentarios", query = "SELECT v FROM Venta v WHERE v.comentarios = :comentarios")})
@NamedQuery(name="Venta.findAll", query="SELECT v FROM Venta v")
public class Venta implements Serializable {
	private static final long serialVersionUID = 1L;

	public static String FIND_ALL                    = "Venta.findAll";
	public static String FIND_BY_ID                  = "Venta.findByIdventa";
	public static String FIND_BY_ID_FACTURA          = "Venta.findByIdfactura";
	public static String FIND_BY_ID_STATUS           = "Venta.findByIdstatus";
	public static String FIND_BY_ID_CLIENTE          = "Venta.findByIdcliente";
	public static String FIND_BY_ID_TIENDA           = "Venta.findByIdtienda";
	public static String FIND_BY_ID_VENDEDOR         = "Venta.findByIdvendedor";
	public static String FIND_BY_FECHA               = "Venta.findByFecha";
	public static String FIND_BY_FOLIO               = "Venta.findByFolio";
	public static String FIND_BY_SUBTOTAL            = "Venta.findBySubtotal";
	public static String FIND_BY_IVA                 = "Venta.findByIva";
	public static String FIND_BY_TOTAL               = "Venta.findByTotal";
	public static String FIND_BY_STATUS              = "Venta.findByStatus";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idventa;

	private String comentarios;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private String folio;

	private float iva;

	private int status;

	private float subtotal;

	private float total;
	
	private boolean facturado;
	
	private float tasaiva;

	//bi-directional many-to-one association to Detalleventa
	@OneToMany(mappedBy="venta", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	private List<Detalleventa> detalleventas;

	//bi-directional many-to-one association to Factura
	@OneToMany(mappedBy="venta", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	private List<Factura> facturas;

	//bi-directional many-to-one association to Tienda
	@ManyToOne
	@JoinColumn(name="idtienda")
	private Tienda tienda;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="idcliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idvendedor")
	private Usuario usuario;

	//bi-directional many-to-one association to Pagoventa
	@OneToMany(mappedBy="venta", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	private List<Pagoventa> pagoventas;
	
	/*
	//bi-directional many-to-one association to Adeudoventa
	@OneToMany(mappedBy="venta", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	private List<Adeudoventa> adeudoventas;
	*/

	public Venta() {
	}

	public Integer getIdventa() {
		return this.idventa;
	}

	public void setIdventa(Integer idventa) {
		this.idventa = idventa;
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

	public List<Detalleventa> getDetalleventas() {
		return this.detalleventas;
	}

	public void setDetalleventas(List<Detalleventa> detalleventas) {
		this.detalleventas = detalleventas;
	}

	public Detalleventa addDetalleventa(Detalleventa detalleventa) {
		getDetalleventas().add(detalleventa);
		detalleventa.setVenta(this);

		return detalleventa;
	}

	public Detalleventa removeDetalleventa(Detalleventa detalleventa) {
		getDetalleventas().remove(detalleventa);
		detalleventa.setVenta(null);

		return detalleventa;
	}

	public List<Factura> getFacturas() {
		return this.facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public Factura addFactura(Factura factura) {
		getFacturas().add(factura);
		factura.setVenta(this);

		return factura;
	}

	public Factura removeFactura(Factura factura) {
		getFacturas().remove(factura);
		factura.setVenta(null);

		return factura;
	}

	public Tienda getTienda() {
		return this.tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Pagoventa> getPagoventas() {
		return this.pagoventas;
	}

	public void setPagoventas(List<Pagoventa> pagoventas) {
		this.pagoventas = pagoventas;
	}
	
	/*
	public List<Adeudoventa> getAdeudoventas() {
		return this.adeudoventas;
	}

	public void setAdeudoventas(List<Adeudoventa> adeudoventas) {
		this.adeudoventas = adeudoventas;
	}
	*/	

	public Pagoventa addPagoventa(Pagoventa pagoventa) {
		getPagoventas().add(pagoventa);
		pagoventa.setVenta(this);

		return pagoventa;
	}

	public Pagoventa removePagoventa(Pagoventa pagoventa) {
		getPagoventas().remove(pagoventa);
		pagoventa.setVenta(null);

		return pagoventa;
	}

	/*
	public Adeudoventa addAdeudoventa(Adeudoventa adeudoventa) {
		getAdeudoventas().add(adeudoventa);
		adeudoventa.setVenta(this);

		return adeudoventa;
	}

	public Adeudoventa removeAdeudoventa(Adeudoventa adeudoventa) {
		getAdeudoventas().remove(adeudoventa);
		adeudoventa.setVenta(null);

		return adeudoventa;
	}
	*/

	public boolean isFacturado() {
		return facturado;
	}

	public void setFacturado(boolean facturado) {
		this.facturado = facturado;
	}
	
	public float getTasaIva() {
		return this.tasaiva;
	}

	public void setTasaIva(float tasaIva) {
		this.tasaiva = tasaIva;
	}
}