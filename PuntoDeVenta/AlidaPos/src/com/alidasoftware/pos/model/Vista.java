package com.alidasoftware.pos.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the vista database table.
 * 
 */
@Entity
@Table(name = "vista")
@NamedQueries({
    @NamedQuery(name = "Vista.findAll", query = "SELECT v FROM Vista v"),
    @NamedQuery(name = "Vista.findByIdvista", query = "SELECT v FROM Vista v WHERE v.idvista = :idvista"),
    @NamedQuery(name = "Vista.findByIdcliente", query = "SELECT v FROM Vista v WHERE v.cliente = :idcliente"),
    @NamedQuery(name = "Vista.findByIdtipovencimiento", query = "SELECT v FROM Vista v WHERE v.tipovencimiento = :idtipovencimiento"),
    @NamedQuery(name = "Vista.findByFecha", query = "SELECT v FROM Vista v WHERE v.fecha = :fecha"),
    @NamedQuery(name = "Vista.findByFolio", query = "SELECT v FROM Vista v WHERE v.folio = :folio"),
    @NamedQuery(name = "Vista.findByStatus", query = "SELECT v FROM Vista v WHERE v.status = :status"),
    @NamedQuery(name = "Vista.findByComentarios", query = "SELECT v FROM Vista v WHERE v.comentarios = :comentarios")})
public class Vista implements Serializable {
	private static final long serialVersionUID = 1L;

	public static String FIND_ALL                   = "Vista.findAll";
	public static String FIND_BY_ID                 = "Vista.findByIdvista";
	public static String FIND_BY_ID_CLIENTE         = "Vista.findByIdcliente";
	public static String FIND_BY_ID_TIPOVENCIMIENTO = "Vista.findByIdtipovencimiento";
	public static String FIND_BY_FECHA              = "Vista.findByFecha";
	public static String FIND_BY_FOLIO              = "Vista.findByFolio";	
	public static String FIND_BY_STATUS             = "Vista.findByStatus";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idvista;

	private String comentarios;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private String folio;

	private Integer status;

	//bi-directional many-to-one association to Detallevista
	@OneToMany(mappedBy="vista", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	private List<Detallevista> detallevistas;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="idcliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Tipovencimiento
	@ManyToOne
	@JoinColumn(name="idtipovencimiento")
	private Tipovencimiento tipovencimiento;

	public Vista() {
	}

	public Integer getIdvista() {
		return this.idvista;
	}

	public void setIdvista(Integer idvista) {
		this.idvista = idvista;
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

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Detallevista> getDetallevistas() {
		return this.detallevistas;
	}

	public void setDetallevistas(List<Detallevista> detallevistas) {
		this.detallevistas = detallevistas;
	}

	public Detallevista addDetallevista(Detallevista detallevista) {
		getDetallevistas().add(detallevista);
		detallevista.setVista(this);

		return detallevista;
	}

	public Detallevista removeDetallevista(Detallevista detallevista) {
		getDetallevistas().remove(detallevista);
		detallevista.setVista(null);

		return detallevista;
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

}